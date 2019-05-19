package com.mcg.spi.spring;

import com.mcg.spi.SPI;
import com.mcg.spi.SpiLoader;
import com.mcg.spi.SpiLoaderFactory;
import com.mcg.spi.SpiUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mcg
 */
@Component
public class SpringSpiLoader<T> implements SpiLoader, InitializingBean, ApplicationContextAware {

    private volatile boolean loaded=false;

    private Object lock=new Object();

    private ApplicationContext applicationContext;

    private  Map<String,T> spiInstanceMaps=new HashMap<String,T>();

    private  Map<Class<T>,T> defaultSpiInstanceMaps=new HashMap<Class<T>,T>();

    @Override
    public void afterPropertiesSet() throws Exception {
        if(!loaded) {
            synchronized (lock) {
            if (!loaded) {
                spiInstanceMaps= (Map<String, T>) applicationContext.getBeansWithAnnotation(SPI.class);
                spiInstanceMaps.keySet().forEach(e->{
                    Object targetSpi= spiInstanceMaps.get(e);
                    Class[] interfaces = targetSpi.getClass().getInterfaces();
                    Class superClass= targetSpi.getClass().getSuperclass();
                    if(null!=interfaces) {
                        for (Class clazz : targetSpi.getClass().getInterfaces()) {
                              if(SpiUtil.isSpi(clazz)) {
                                  SpiLoaderFactory.putSpiLoader(clazz, this);
                              }

                        }
                    }
                    if(null!=superClass) {
                        if(SpiUtil.isSpi(superClass)) {
                            SpiLoaderFactory.putSpiLoader(superClass, this);
                        }

                    }

                });

             }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @Override
    public T getSpi(String name) {
        return spiInstanceMaps.get(name);
    }

    @Override
    public Object getSpi(Class type) {
        if (type.isAnnotationPresent(SPI.class)) {
           SPI spi=(SPI)type.getAnnotation(SPI.class);
           return spiInstanceMaps.get(spi.value());
        }
        return null;
    }
}
