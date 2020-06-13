package com.ecit.service;

import org.springframework.stereotype.Service;

@Service
public class A {
    public A(B b){

    }
    /*public A(B b, C c) {
    }*/

    public void h1(){
        System.out.println("B hello");
    }
}
