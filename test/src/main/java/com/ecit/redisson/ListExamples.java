package com.ecit.redisson;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

public class ListExamples {
    public static void main(String[] args) {
        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
                //可以用"rediss://"来启用SSL连接
                .addNodeAddress("redis://192.168.1.57:8001", "redis://192.168.1.57:8002", "redis://192.168.1.57:8003")
        .setPassword("h123456");
        RedissonClient client = Redisson.create(config);

        // RList 继承了 java.util.List 接口
      /*  RList<String> nameList = client.getList("nameList");
        nameList.clear();
        nameList.add("bingo");
        nameList.add("yanglbme");
        nameList.add("https://github.com/yanglbme");
        nameList.remove(-1);*/

        /*RList<String> nameList = client.getList("nameList");

        boolean contains = nameList.contains("yanglbme");
        System.out.println("List size: " + nameList.size());
        System.out.println("Is list contains name 'yanglbme': " + contains);
        nameList.forEach(System.out::println);*/


        RKeys keys = client.getKeys();
        keys.getKeys().forEach(System.out::println);

        RMap rMap = client.getMap("map_hello");
        rMap.put("hello", "world");
        rMap.put("hello1", "java");
        rMap.put("hello2", "c");
        rMap.put("hello3", "python");
        rMap.put("hello", "python");

        RMap rMap2 = client.getMap("map_hello");
        rMap2.forEach((k, v) -> {
            System.out.println("key:" + k + ", value:" + v);
        });

        RBucket<String> rBucket = client.getBucket("hello");
        System.out.println(rBucket.get());
        RBucket<String> rBucket2 = client.getBucket("{user:100}.name");
        rBucket2.set("{user:100}.name");

        RBloomFilter<String> filter = client.getBloomFilter("filter");
        filter.tryInit(550000000L, 0.03);
        System.out.println(filter.getExpectedInsertions());
        System.out.println(filter.getFalseProbability());
        System.out.println(filter.getHashIterations());
        System.out.println(filter.getSize());
        System.out.println(filter.contains("hello"));
        filter.add("hello");
        System.out.println(filter.contains("hello"));
        System.out.println();



        client.shutdown();

    }
}