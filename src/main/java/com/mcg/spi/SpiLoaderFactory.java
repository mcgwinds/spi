package com.mcg.spi;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wmk
 */
public class SpiLoaderFactory {

    private static Map<Class,SpiLoader> classToSpiLoaderMap=new HashMap<Class,SpiLoader>();

    public static SpiLoader getSpiLoader(Class clazz) {
        return classToSpiLoaderMap.get(clazz);
    }

    public static void putSpiLoader(Class clazz,SpiLoader spiLoader) {
        classToSpiLoaderMap.put(clazz,spiLoader);
    }
}
