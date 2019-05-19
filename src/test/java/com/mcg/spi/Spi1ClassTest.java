package com.mcg.spi;

import org.springframework.stereotype.Component;

@Component("Spi1ClassTest")
public class Spi1ClassTest extends SpiClassTest {
    @Override
    public void doTest() {
        System.out.println("Spi1ClassTest");
    }
}
