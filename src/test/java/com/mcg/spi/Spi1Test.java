package com.mcg.spi;

import org.springframework.stereotype.Component;

@Component("spi1")
public class Spi1Test implements SpiTest {

    @Override
    public void doTest() {

        System.out.println("hello1");

    }
}
