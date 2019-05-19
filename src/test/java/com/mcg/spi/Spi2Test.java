package com.mcg.spi;

import org.springframework.stereotype.Component;

@Component("spi2")
public class Spi2Test implements SpiTest {

    @Override
    public void doTest() {

        System.out.println("hello2");

    }
}
