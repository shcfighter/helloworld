package com.ecit.zk;

import org.apache.zookeeper.*;

import java.io.IOException;

public class WatcherTest implements Watcher {
    @Override
    public void process(WatchedEvent event) {
        // TODO Auto-generated method stub
        WatcherTest  w = new WatcherTest();
        System.out.println(event.getType().getIntValue());
        System.out.println(Event.KeeperState.fromInt(event.getType().getIntValue()));
        if (Event.EventType.NodeChildrenChanged.getIntValue() == event.getType().getIntValue()) {
            System.out.println(Event.KeeperState.fromInt(Event.EventType.NodeChildrenChanged.getIntValue()));
        }
        try {
            ZooKeeper zk = new ZooKeeper("localhost:2181",10000, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception {
        WatcherTest  w = new WatcherTest();
        ZooKeeper   zk = new ZooKeeper("localhost:2181", 10000, w);
        zk.create("/1", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.create("/2", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }
}