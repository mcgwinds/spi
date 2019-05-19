package com.mcg.spi;


/**
 * @author mcg
 */
public interface SpiLoader<T> {

    T getSpi(String name);

    T getSpi(Class<T> clazz);

}