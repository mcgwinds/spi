package com.mcg.spi.spring;


import com.mcg.spi.Adaptive;
import com.mcg.spi.SpiLoaderFactory;
import com.mcg.spi.proxy.SpiCglibProxyFactory;
import com.mcg.spi.proxy.SpiJDKProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author
 */
@Component
public class FieldAdaptiveBeanPostProcessor implements AdaptiveBeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Field[] fields= bean.getClass().getDeclaredFields();
        if(null!=fields) {

            for (Field field:fields) {
                Adaptive adaptive=field.getAnnotation(Adaptive.class);
                if(null!=adaptive) {
                    if(!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    //产生字段代理类
                    try {
                        if(field.getType().isInterface()) {
                            field.set(bean, new SpiJDKProxyFactory().newSpiProxy(field.getType(), SpiLoaderFactory.getSpiLoader(field.getType())));
                        }
                        else {
                            field.set(bean, new SpiCglibProxyFactory().newSpiProxy(field.getType(), SpiLoaderFactory.getSpiLoader(field.getType())));
                        }
                    } catch (IllegalAccessException e) {
                        //还是
                    }

                }

            }

        }
        return bean;
    }
}
