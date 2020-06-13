package com.ecit.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DistributedLock {
    static RedissonClient client = null;
    static CountDownLatch downLatch = new CountDownLatch(10);

    public static void main(String[] args) {
        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
                //可以用"rediss://"来启用SSL连接
                .addNodeAddress("redis://192.168.1.57:8001", "redis://192.168.1.57:8002", "redis://192.168.1.57:8003")
                .setPassword("h123456");
        client = Redisson.create(config);

        run();

        client.shutdown();
    }

    public static void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                RLock rLock = client.getLock("test");
                rLock.lock(10, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + " locked");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                rLock.unlock();
                downLatch.countDown();
            });
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("thread is down");
    }

}
