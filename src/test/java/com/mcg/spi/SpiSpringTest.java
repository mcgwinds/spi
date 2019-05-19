package com.mcg.spi;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpiSpringTest {


    public static void main(String []  args) {
        ApplicationContext  applicationContext= new ClassPathXmlApplicationContext("classpath*:spi-spring.xml");
        SpiLoader spiLoader = (SpiLoader)applicationContext.getBean(SpiLoader.class);
        SpiTest spiTest1= (SpiTest)spiLoader.getSpi("spi1");
        spiTest1.doTest();
        SpiTest spiTest2= (SpiTest)spiLoader.getSpi("spi2");
        spiTest2.doTest();
        SpiTest spiTest= (SpiTest)spiLoader.getSpi(SpiTest.class);
        spiTest.doTest();
        ServiceTest serviceTest=applicationContext.getBean(ServiceTest.class);
        serviceTest.doService();

    }
}
