package com.ecit.dubbo.spi.impl;

import com.ecit.dubbo.spi.interfaces.ICar;
import com.ecit.dubbo.spi.interfaces.IDriver;
import org.apache.dubbo.common.URL;

public class Trucker implements IDriver {
    private ICar car;

    public void setCar(ICar car) {
        this.car = car;
    }

    @Override
    public void driverCar(URL url) {
        car.print(url);
    }
}
