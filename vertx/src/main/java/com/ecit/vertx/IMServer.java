package com.ecit.vertx;

import io.vertx.core.Vertx;

public class IMServer {

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new IMVerticle());
    }
}
