package com.ecit;

import org.springframework.core.task.support.ExecutorServiceAdapter;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    static ReentrantLock lock = new ReentrantLock(true);
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " ");
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " lock");
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " unlock");
                lock.unlock();
            }
        });

        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " ");
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " lock");
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " unlock");
                lock.unlock();
            }
        });
        executorService.shutdown();
    }


}
