package com.ecit.dubbo.spi;

import com.ecit.dubbo.spi.interfaces.ICar;
import org.apache.dubbo.common.extension.ExtensionLoader;

public class SpiMain {
    public static void main(String[] args) {
        ExtensionLoader<ICar> extensionLoader = ExtensionLoader.getExtensionLoader(ICar.class);
        ICar car = extensionLoader.getExtension("red");
        car.print(null);
    }
}
