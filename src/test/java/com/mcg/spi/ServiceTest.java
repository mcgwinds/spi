package com.mcg.spi;

import com.mcg.spi.spring.SpringSpiLoader;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class ServiceTest {

    @Adaptive
    private SpiTest spiTest;

    @Resource
    private SpringSpiLoader springSpiLoader;

    @Adaptive
    private SpiClassTest spiClassTest;

    public void doService() {
        SpiTest spiTest1= (SpiTest)springSpiLoader.getSpi(SpiTest.class);
        spiTest1.doTest();
        spiTest.doTest();
        spiClassTest.doTest();
    }
}
