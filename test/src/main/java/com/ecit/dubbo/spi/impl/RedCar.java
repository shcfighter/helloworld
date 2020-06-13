package com.ecit.dubbo.spi.impl;

import com.ecit.dubbo.spi.interfaces.ICar;
import org.apache.dubbo.common.URL;

public class RedCar implements ICar {
    @Override
    public void print(URL url) {
        System.out.println("red");
    }
}
