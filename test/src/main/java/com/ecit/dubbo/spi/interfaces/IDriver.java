package com.ecit.dubbo.spi.interfaces;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.SPI;

@SPI
public interface IDriver {

    void driverCar(URL url);
}
