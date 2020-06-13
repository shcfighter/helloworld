package com.ecit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
public class ExchangerDemo {
    public static void main(String args[]) throws InterruptedException {
        List<String> buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();
        Exchanger<List<String>> exchanger = new Exchanger<>();
        new Thread(new Comsumer(buffer2,exchanger)).start();
        Thread.sleep(2000L);
        new Thread(new Producer(buffer1,exchanger)).start();
    }
}

class Producer implements Runnable {
    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;
    public Producer(List<String> buffer,Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }
    public void run() {
        for (int i=0;i<10;i++){
            System.out.printf("Producer thread:  %d\n",i);
            for(int j=0;j<10;j++){
                String message = "message " + (i*10+j);
                System.out.printf("Producer message: %s\n",message);
                buffer.add(message);
            }
            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
class Comsumer implements Runnable {
    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;
    public Comsumer(List<String> buffer,Exchanger<List<String>> exchanger){
        this.buffer = buffer;
        this.exchanger = exchanger;
    }
    public void run() {
        for(int i=0;i<10;i++){
            System.out.printf("Consumer thread:  %d\n",i);
            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int j=0;j<10;j++){
                String message = buffer.get(0);
                System.out.println("Comsumer message: "+message);
                buffer.remove(0);
            }
        }
    }
}
