package com.ecit.dubbo.spi;

import com.ecit.dubbo.spi.interfaces.IDriver;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.HashMap;
import java.util.Map;

public class SpiMain2 {
    public static void main(String[] args) {
        ExtensionLoader<IDriver> extensionLoader = ExtensionLoader.getExtensionLoader(IDriver.class);
        IDriver car = extensionLoader.getExtension("trucker");
        Map<String, String> map = new HashMap<>();
        map.put("carType", "blur");
        URL url = new URL("", "", 0, map);
        car.driverCar(url);
    }
}
