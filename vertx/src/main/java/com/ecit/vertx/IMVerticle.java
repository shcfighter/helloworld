package com.ecit.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class IMVerticle extends AbstractVerticle {
    @Override
    public void start(){
        vertx.createHttpServer().websocketHandler(ws -> {
            System.out.println("==================================");
            System.out.println(ws.path());
            ws.handler(buffer -> {
                System.out.println(buffer.toString());
                ws.write(new JsonObject().put("hello", "world").toBuffer());
                ws.write(new JsonObject().put("hello", "world").toBuffer());
            });
            vertx.setPeriodic(1000, handler -> {
                ws.write(new JsonObject().put("hello", "world").toBuffer());
            });
        }).listen(8888);
    }
}
