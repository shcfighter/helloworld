package com.ecit;

public class LockTest {
    /*public synchronized void sayGoodbye() {
        System.out.println("say good bye");
    }*/
    /*public synchronized static void sayHi() {
        System.out.println("say hi");
    }*/
    public void sayHello() {
        synchronized (LockTest.class) {
            System.out.println("say hello");
        }
    }

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        //lockTest.sayGoodbye();
        lockTest.sayHello();
        //LockTest.sayHi();
    }
}