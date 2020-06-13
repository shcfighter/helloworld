package com.ecit;

public class MyAgentTest {
    public static void main( String[] args ) throws InterruptedException {
        while (true) {
            hello();
            Thread.sleep(1000);
            test();
        }
    }

    public static void hello() {
        System.out.println("Hello World!");
    }

    public static void test() {
        System.out.println("Hello test!");
    }
}