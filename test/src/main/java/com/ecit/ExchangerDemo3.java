package com.ecit;

import java.util.concurrent.Exchanger;
import java.util.concurrent.locks.LockSupport;

public class ExchangerDemo3 {
    public static void main(String[] args) throws Exception {

        Exchanger<Integer> exchanger = new Exchanger<>();
        ComsumerDemo3 comsumerDemo = null;
        ProducerDemo3 producerDemo = null;
        comsumerDemo = new ComsumerDemo3();

        comsumerDemo.setName("comsumer thread");
        producerDemo = new ProducerDemo3();
        producerDemo.setName("producer thread");
        producerDemo.setThread(comsumerDemo, exchanger);
        comsumerDemo.setThread(producerDemo, exchanger);
        producerDemo.start();
        comsumerDemo.start();
        //Thread.sleep(5000L);
    }
}

class ProducerDemo3 extends Thread {
    private Thread comsumeThread;
    private Exchanger<Integer> exchanger;

    public void setThread(Thread comsumeThread, Exchanger<Integer> exchanger){
        this.comsumeThread = comsumeThread;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        //System.out.println("ProducerDemo: " + Thread.currentThread());
        for (int i = 0; i < 10; i = i+2) {
            try {
                int value = exchanger.exchange(i);
                LockSupport.park();
                System.out.println("Producer: " + value);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ComsumerDemo3 extends Thread {
    private Thread producerThread;
    private Exchanger<Integer> exchanger;

    public void setThread(Thread producerThread, Exchanger<Integer> exchanger) {
        this.producerThread = producerThread;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        //System.out.println("ComsumerDemo: " + Thread.currentThread());
        for (int i = 1; i < 10; i = i+2) {
            try {
                int value = exchanger.exchange(i);
                System.out.println("Comsumer: " + value);
                LockSupport.unpark(producerThread);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}