package com.ecit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.exceptions.DeserializationException;
import org.apache.hadoop.hbase.filter.ColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;


public class HbaseDemo {
    Configuration conf = null;
    Admin admin = null;
    Connection conn = null;
    @Before
    public void init() throws Exception {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "shc");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conn = ConnectionFactory.createConnection(conf);
        admin = conn.getAdmin();
    }
    
    @Test
    public void createTableOldFunction() throws Exception {
        TableName tableName = TableName.valueOf("student");    
        HTableDescriptor desc = new HTableDescriptor(tableName);
        HColumnDescriptor family1 = new HColumnDescriptor("f1");
        desc.addFamily(family1);
        admin.createTable(desc);
    }
    
    @Test
    public void createTableOneFamily() throws Exception{
        TableName tableName = TableName.valueOf("goods");        
        //ColumnFamilyDescriptor family2 = ColumnFamilyDescriptorBuilder.of("info");
        //ModifyableTableDescriptor desc = new ModifyableTableDescriptor(tableName);
        //desc.setColumnFamily(family2);
        //admin.createTable(desc);
        
        boolean tableExists = admin.tableExists(tableName);
        if(!tableExists) {
            TableDescriptorBuilder tableBuilder = TableDescriptorBuilder.newBuilder(tableName);
            ColumnFamilyDescriptor family = ColumnFamilyDescriptorBuilder.newBuilder("food".getBytes()).build();
            TableDescriptor desc = tableBuilder.setColumnFamily(family).build();
            admin.createTable(desc);
            System.out.println("Create table1 success.");
        }else {
            System.out.println("tatble1 exists.");
        }
    }
    
    @Test
    public void createTableManyFamily() throws IOException {
        TableName tableName = TableName.valueOf("languages");
        boolean tableExists = admin.tableExists(tableName);
        if(!tableExists) {            
            TableDescriptorBuilder table = TableDescriptorBuilder.newBuilder(tableName);
            
            String[] familyNames = new String[] {"java","python","php","golang"};
            for(String familyName: familyNames) {
                ColumnFamilyDescriptor family = ColumnFamilyDescriptorBuilder.newBuilder(familyName.getBytes()).build();
                table.setColumnFamily(family);
            }
            TableDescriptor desc = table.build();
            admin.createTable(desc);
            System.out.println("Create table2 success.");
        }else {
            System.out.println("table2 exists.");
        }
    }
    
    @Test
    public void insertData() throws IOException {
        ArrayList<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        
        HashMap<String, String> dataDict1 = new HashMap<String, String>();
        dataDict1.put("rowKey", "ceshi3");
        dataDict1.put("columnFamily", "java");
        dataDict1.put("columnName","bookname");
        dataDict1.put("columnValue", "java book 9999");
        dataList.add(dataDict1);
        
        HashMap<String, String> dataDict2 = new HashMap<String, String>();
        dataDict2.put("rowKey", "ceshi3");
        dataDict2.put("columnFamily", "java");
        dataDict2.put("columnName","author");
        dataDict2.put("columnValue", "zhangsan");
        dataList.add(dataDict2);
        
        HashMap<String, String> dataDict3 = new HashMap<String, String>();
        dataDict3.put("rowKey", "ceshi3");
        dataDict3.put("columnFamily", "java");
        dataDict3.put("columnName","price");
        dataDict3.put("columnValue", "78.8");
        dataList.add(dataDict3);
        
        Table table = conn.getTable(TableName.valueOf("languages"));
        List<Put> puts = new ArrayList<Put>();
        for(Map<String,String> d: dataList) {
            String rowKey = d.get("rowKey");
            Put put = new Put(rowKey.getBytes());
            put.addColumn(d.get("columnFamily").getBytes(), d.get("columnName").getBytes(), d.get("columnValue").getBytes());
            puts.add(put);
        }
        table.put(puts);
        table.close();
    }
    
    @Test
    public void updateData() throws IOException {
        Table table = conn.getTable(TableName.valueOf("languages"));
        Put put = new Put("ceshi1".getBytes());
        put.addColumn("java".getBytes(), "price".getBytes(), "188.8".getBytes());
        table.put(put);
        table.close();
    }
    
    @Test
    public void deleteData() throws IOException {
        Table table = conn.getTable(TableName.valueOf("languages"));
        Delete delete = new Delete("ceshi1".getBytes()); //delete one row
        delete.addFamily("java".getBytes()); //delete one family
        //delete.addColumn("java".getBytes(), "price".getBytes()); //delete one column 
        //delete.addColumns(family, qualifier) //delete many columns
        table.delete(delete);
        table.close();
    }
    
    @Test
    public void queryData() throws Exception {
        Table table = conn.getTable(TableName.valueOf("languages"));
        
        Get get = new Get("ceshi1".getBytes());
        Result result = table.get(get);
        Cell[] rawCells = result.rawCells();
        System.out.println(rawCells.length);
        for(Cell cell: rawCells) {
            String columnName = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
            String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
            System.out.println(columnName+":"+value);
        }
        table.close();
    }
    
    @Test
    public void scanTable() throws IOException {
        Table table = conn.getTable(TableName.valueOf("languages"));
        
        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);
        for(Result res:scanner) {
            byte[] row = res.getRow();
            String rowKey = Bytes.toString(row);
            System.out.println("rowKey:"+rowKey);
            Cell[] rawCells = res.rawCells();
            for(Cell cell:rawCells) {
                String family = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
                String columnName = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                System.out.println(rowKey+"\t\t\t"+"column="+family+":"+columnName+", value="+value);
            }
        }
        table.close();
    }
    
    @Test
    public void rowKeyFilter() throws IOException {
        Table table = conn.getTable(TableName.valueOf("languages"));
        Scan scan = new Scan();
        RowFilter filter = new RowFilter(CompareOperator.EQUAL, new RegexStringComparator("shi1$"));
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);
        for(Result res:scanner) {
            byte[] row = res.getRow();
            String rowKey = Bytes.toString(row);
            System.out.println("rowKey:"+rowKey);
            Cell[] cells = res.rawCells();
            for(Cell cell:cells) {
                String family = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
                String columnName = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                System.out.println(rowKey+"\t\t\t"+"column="+family+":"+columnName+", value="+value);
            }
        }
        table.close();
    }
    
    @Test
    public void oneColumnNameFilter() throws IOException {
        Table table = conn.getTable(TableName.valueOf("languages"));
        Scan scan = new Scan();
        ColumnPrefixFilter filter = new ColumnPrefixFilter("author".getBytes());
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);
        for(Result res: scanner) {
            byte[] row = res.getRow();
            String rowKey = Bytes.toString(row);
            System.out.println("rowKey:"+rowKey);
            Cell[] cells = res.rawCells();
            for(Cell cell:cells) {
                String family = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
                String columnName = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                System.out.println(rowKey+"\t\t\t"+"column="+family+":"+columnName+",value="+value);
            }
        }
        table.close();
    }
    
    @Test
    public void manyColumnNameFilter() throws IOException {
        Table table = conn.getTable(TableName.valueOf("languages"));
        Scan scan = new Scan();
        byte[][] prefixes = new byte[][] {"author".getBytes(),"bookname".getBytes()};
        MultipleColumnPrefixFilter filter = new MultipleColumnPrefixFilter(prefixes);
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);
        for(Result res:scanner) {
            byte[] row = res.getRow();
            String rowKey = Bytes.toString(row);
            System.out.println("rowKey:"+rowKey);
            Cell[] cells = res.rawCells();
            for(Cell cell: cells) {
                String family = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
                String columnName = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                System.out.println(rowKey+"\t\t\t"+"column="+family+":"+columnName+",value="+value);
            }
        }
        table.close();
    }
    
    @Test
    public void columnValueFilter() throws IOException, DeserializationException {
        Table table = conn.getTable(TableName.valueOf("languages"));
        Scan scan = new Scan();
        SingleColumnValueFilter filter = new SingleColumnValueFilter("python".getBytes(), "bookname".getBytes(), CompareOperator.EQUAL, new RegexStringComparator("oo"));
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);
        for(Result res: scanner) {
            byte[] row = res.getRow();
            String rowKey = Bytes.toString(row);
            System.out.println("rawKey:"+rowKey);
            Cell[] cells = res.rawCells();
            for(Cell cell: cells) {
                String family = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
                String columnName = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                System.out.println(rowKey+"\t\t\t"+"column="+family+":"+columnName+", value="+value);
            }
        }
        table.close();
    }
    
    @Test
    public void filterListFilter() throws IOException {
        Table table = conn.getTable(TableName.valueOf("languages"));
        Scan scan = new Scan();
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        
        SingleColumnValueFilter filter1 = new SingleColumnValueFilter("python".getBytes(), "bookname".getBytes(), CompareOperator.EQUAL, new RegexStringComparator("oo"));
        ColumnPrefixFilter filter2 = new ColumnPrefixFilter("price".getBytes());
        filterList.addFilter(filter1);
        filterList.addFilter(filter2);
        scan.setFilter(filterList);
        
        ResultScanner scanner = table.getScanner(scan);
        for(Result res: scanner) {
            byte[] row = res.getRow();
            String rowKey = Bytes.toString(row);
            System.out.println("rawKey:"+rowKey);
            Cell[] cells = res.rawCells();
            for(Cell cell: cells) {
                String family = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
                String columnName = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                System.out.println(rowKey+"\t\t\tcolumn="+family+":"+columnName+",value="+value);
            }
        }
        table.close();
    }
}