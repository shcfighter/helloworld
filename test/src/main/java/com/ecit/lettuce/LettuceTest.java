package com.ecit.lettuce;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;

import java.util.ArrayList;

public class LettuceTest {
    public static void main(String[] args) {
        ArrayList<RedisURI> list = new ArrayList<>();
        RedisURI uri1 = RedisURI.create("redis://192.168.1.57:8001");
        uri1.setPassword("h123456".toCharArray());
        list.add(uri1);
        RedisURI uri2 = RedisURI.create("redis://192.168.1.57:8002");
        uri2.setPassword("h123456".toCharArray());
        list.add(uri2);
        RedisURI uri3 = RedisURI.create("redis://192.168.1.57:8003");
        uri3.setPassword("h123456".toCharArray());
        list.add(uri3);
        RedisURI uri4 = RedisURI.create("redis://192.168.1.57:8004");
        uri4.setPassword("h123456".toCharArray());
        list.add(uri4);
        RedisURI uri5 = RedisURI.create("redis://192.168.1.57:8005");
        uri5.setPassword("h123456".toCharArray());
        list.add(uri5);
        RedisURI uri6 = RedisURI.create("redis://192.168.1.57:8006");
        uri6.setPassword("h123456".toCharArray());
        list.add(uri6);
        RedisClusterClient client = RedisClusterClient.create(list);
        //RedisClusterClient client = RedisClusterClient.create("redis://192.168.37.128:7000");
        StatefulRedisClusterConnection<String, String> connect = client.connect();
        connect.setReadFrom(ReadFrom.SLAVE);

        /* 同步执行的命令 */
        RedisAdvancedClusterCommands<String, String> commands = connect.sync();
        String str = commands.get("test2");
        System.out.println(str);

        commands.hset("h_hash", "hello", "world");
        commands.hset("h_hash", "hello1", "java");
        commands.hset("h_hash", "hello2", "c");
        commands.hset("h_hash", "hello3", "python");

        commands.hgetall("h_hash").forEach((k, v) -> {
            System.out.println(k + " -> " + v);
        });

        /*  异步执行的命令  */
//      RedisAdvancedClusterAsyncCommands<String, String> commands= connect.async();
//      RedisFuture<String> future = commands.get("test2");
//      try {
//          String str = future.get();
//          System.out.println(str);
//      } catch (InterruptedException e) {
//          e.printStackTrace();
//      } catch (ExecutionException e) {
//          e.printStackTrace();
//      }

        connect.close();
        client.shutdown();
    }
}
