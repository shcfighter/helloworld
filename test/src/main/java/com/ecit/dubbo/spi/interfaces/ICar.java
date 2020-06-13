package com.ecit.dubbo.spi.interfaces;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

@SPI
public interface ICar {

    @Adaptive("carType")
    void print(URL url);
}
