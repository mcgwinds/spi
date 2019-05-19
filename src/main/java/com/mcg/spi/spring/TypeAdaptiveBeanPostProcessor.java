package com.mcg.spi.spring;

import com.mcg.spi.Adaptive;
import com.mcg.spi.SpiLoaderFactory;
import com.mcg.spi.proxy.SpiJDKProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

/**
 * @author wmk
 */
@Component
public class TypeAdaptiveBeanPostProcessor implements AdaptiveBeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Adaptive adaptive= bean.getClass().getAnnotation(Adaptive.class);
        if(null!=adaptive) {
            //产生代理类
            bean=new SpiJDKProxyFactory().newSpiProxy(bean.getClass(), SpiLoaderFactory.getSpiLoader(bean.getClass()));
        }
        return bean;
    }
}

