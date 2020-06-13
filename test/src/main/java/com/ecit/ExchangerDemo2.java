package com.ecit;

import java.util.concurrent.locks.LockSupport;

public class ExchangerDemo2 {
    public static void main(String[] args) throws Exception {
        ComsumerDemo comsumerDemo = null;
        ProducerDemo producerDemo = null;
        comsumerDemo = new ComsumerDemo();

        comsumerDemo.setName("comsumer thread");
        producerDemo = new ProducerDemo();
        producerDemo.setName("producer thread");
        producerDemo.setThread(comsumerDemo);
        comsumerDemo.setThread(producerDemo);
        comsumerDemo.start();
        Thread.sleep(5000L);
        producerDemo.start();
    }
}

class ProducerDemo extends Thread {
    private Thread comsumeThread;

    public void setThread(Thread comsumeThread){
        this.comsumeThread = comsumeThread;
    }

    @Override
    public void run() {
        //System.out.println("ProducerDemo: " + Thread.currentThread());
        for (int i = 0; i < 100; i = i+2) {
            System.out.println("Producer: " + i);
            //System.out.println("Producer unpark" + comsumeThread);
            LockSupport.unpark(comsumeThread);
            //System.out.println("Producer park" + Thread.currentThread());
            LockSupport.park(Thread.currentThread());
        }
    }
}

class ComsumerDemo extends Thread {
    private Thread producerThread;

    public void setThread(Thread producerThread) {
        this.producerThread = producerThread;
    }

    @Override
    public void run() {
        //System.out.println("ComsumerDemo: " + Thread.currentThread());
        for (int i = 1; i < 100; i = i+2) {
            //System.out.println("Comsumer park" + Thread.currentThread());
            LockSupport.park(Thread.currentThread());
            System.out.println("Comsumer: " + i);
            LockSupport.unpark(producerThread);
            //System.out.println("Comsumer unpark" + producerThread);
        }
    }
}