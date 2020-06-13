package com.ecit.zk;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Objects;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ClusterClient implements Watcher, Runnable {
    private static String membershipRoot = "/Members";
    ZooKeeper zk;

    public ClusterClient(String hostPort, Long pid) {
        String processId = pid.toString();
        try {
            zk = new ZooKeeper(hostPort, 2000, this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (zk != null) {
            try {
                System.out.println(zk.exists(membershipRoot, false));
                if (Objects.isNull(zk.exists(membershipRoot, false))) {
                    zk.create(membershipRoot, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                }
                zk.create(membershipRoot + '/' + processId, processId.getBytes(),
                        Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public synchronized void close() {
        try {
            zk.close();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
// TODO Auto-generated method stub
        System.out.println("\nEvent Received:%s" + event.toString());
    }

    @Override
    public void run() {
// TODO Auto-generated method stub
        try {
            synchronized (this) {
                while (true) {
                    wait();
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            this.close();
        }
    }

    public static void main(String[] args) {

        String hostPort = "localhost:2181";
//Get the process id
        String name = ManagementFactory.getRuntimeMXBean().getName();
        int index = name.indexOf('@');
        Long processId = Long.parseLong(name.substring(0, index));
        new ClusterClient(hostPort, processId).run();
    }

}