package com.ecit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.*;

public class HbaseTest {

    static Configuration conf = null;
    static Admin admin = null;
    static Connection conn = null;

    static {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "shc");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        try {
            conn = ConnectionFactory.createConnection(conf);
            admin = conn.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TableName tableName = TableName.valueOf("logistics");
        /*String[] columnFamilies = initColumnFamilies();
        initTable(tableName, columnFamilies);
        put(tableName, "20191229", "info", "id", "1");
        put(tableName, "20191229", "info", "from", "杭州");
        put(tableName, "20191229", "info", "to", "赣州");

        put(tableName, "20191229", "record", "1", "揽件成功");
        put(tableName, "20191229", "record", "2", "杭州发往赣州");
        put(tableName, "20191229", "record", "3", "正在派件中");
        put(tableName, "20191229", "record", "4", "签收成功");*/

        Map<String, String> row = getRow(tableName, "1");
        System.out.println(row);

        List<Map<String, String>> dataList = scan(tableName, null, null);
        System.out.println(dataList);

        admin.close();
        conn.close();
    }

    /**
     * 根据row key、column 读取
     *
     * @param tableName
     * @param rowKey
     * @param columnFamily
     * @param column
     * @throws IOException
     */
    public static String getCell(TableName tableName, String rowKey, String columnFamily, String column) throws IOException {
        Table table = null;
        try {
            table = conn.getTable(tableName);
            Get get = new Get(Bytes.toBytes(rowKey));
            get.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));

            Result result = table.get(get);
            List<Cell> cells = result.listCells();

            if (CollectionUtils.isEmpty(cells)) {
                return null;
            }
            String value = new String(CellUtil.cloneValue(cells.get(0)), "UTF-8");
            return value;
        } finally {
            if (table != null) {
                table.close();
            }
        }
    }

    /**
     * 根据rowkey 获取一行
     *
     * @param tableName
     * @param rowKey
     * @return
     * @throws IOException
     */
    public static Map<String, String> getRow(TableName tableName, String rowKey) throws IOException {
        Table table = null;
        try {
            table = conn.getTable(tableName);
            Get get = new Get(Bytes.toBytes(rowKey));

            Result result = table.get(get);
            List<Cell> cells = result.listCells();

            if (CollectionUtils.isEmpty(cells)) {
                return Collections.emptyMap();
            }
            Map<String, String> objectMap = new HashMap<>();
            for (Cell cell : cells) {
                String qualifier = new String(CellUtil.cloneQualifier(cell));
                String value = new String(CellUtil.cloneValue(cell), "UTF-8");
                objectMap.put(qualifier, value);
            }
            return objectMap;
        } finally {
            if (table != null) {
                table.close();
            }
        }
    }

    /**
     * 扫描权标的内容
     *
     * @param tableName
     * @param rowkeyStart
     * @param rowkeyEnd
     * @throws IOException
     */
    public static List<Map<String, String>> scan(TableName tableName, String rowkeyStart, String rowkeyEnd) throws IOException {
        Table table = null;
        try {
            table = conn.getTable(tableName);
            ResultScanner rs = null;
            try {
                Scan scan = new Scan();
                if (!StringUtils.isEmpty(rowkeyStart)) {
                    scan.withStartRow(Bytes.toBytes(rowkeyStart));
                }
                if (!StringUtils.isEmpty(rowkeyEnd)) {
                    scan.withStopRow(Bytes.toBytes(rowkeyEnd));
                }
                rs = table.getScanner(scan);

                List<Map<String, String>> dataList = new ArrayList<>();
                for (Result r : rs) {
                    Map<String, String> objectMap = new HashMap<>();
                    for (Cell cell : r.listCells()) {
                        String qualifier = new String(CellUtil.cloneQualifier(cell));
                        String value = new String(CellUtil.cloneValue(cell), "UTF-8");
                        objectMap.put(qualifier, value);
                    }
                    dataList.add(objectMap);
                }
                return dataList;
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }
        } finally {
            if (table != null) {
                table.close();
            }
        }
    }

    private static void put(TableName tableName, String rowKey, String columFamily, String colum, String data) throws IOException {
        Table table = null;
        try {
            table = conn.getTable(tableName);
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(columFamily), Bytes.toBytes(colum), Bytes.toBytes(data));
            table.put(put);
        } finally {
            if (Objects.nonNull(table)) {
                table.close();
            }
        }
    }


    private static String[] initColumnFamilies() {
        String[] columnFamilies = {"info", "record"};
        return columnFamilies;
    }

    public static void initTable(TableName tableName, String[] columnFamilies) throws IOException {
        if (admin.tableExists(tableName)) {
            return;
        }
        TableDescriptorBuilder builder = TableDescriptorBuilder.newBuilder(tableName);
        for (String columnFamily : columnFamilies) {
            builder.setColumnFamily(ColumnFamilyDescriptorBuilder.of(columnFamily));
        }
        admin.createTable(builder.build());
    }
}
