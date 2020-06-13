package com.ecit;

class Base {
    public static int i = 1;

    public Base() {
        System.out.println("Base this = " + this);
        System.out.println("Base i = " + this.i);
        this.display();
    }

    public void display() {
        System.out.println("display display(i) = " + this.i);
        System.out.println("display i = " + i);
    }
}