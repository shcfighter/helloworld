package com.ecit.dubbo.spi.impl;

import com.ecit.dubbo.spi.interfaces.ICar;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;

public class BlurCar implements ICar {
    @Override
    public void print(URL url) {
        System.out.println("blur");
    }
}
