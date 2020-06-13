package com.ecit.dubbo.spi.impl;

import com.ecit.dubbo.spi.interfaces.ICar;
import org.apache.dubbo.common.URL;

public class CarWrapper implements ICar{

    private ICar car;

    public CarWrapper(ICar car) {
        this.car = car;
    }

    @Override
    public void print(URL url){
        System.out.println("before");
        car.print(url);
        System.out.println("after");
    }
}
