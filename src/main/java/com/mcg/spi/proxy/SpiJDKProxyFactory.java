package com.mcg.spi.proxy;

import com.mcg.spi.SPI;
import com.mcg.spi.SpiLoader;
import com.mcg.spi.SpiUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author mcg
 */
public class SpiJDKProxyFactory {

   public <T> T newSpiProxy(Class targetClass, SpiLoader spiLoader) {
        return (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{targetClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Class[] interfaces = proxy.getClass().getInterfaces();
                if (null != interfaces) {
                    for (Class clazz : interfaces) {
                        if (SpiUtil.isSpi(clazz)) {
                            SPI spi = SpiUtil.getSpiValue(clazz);
                            return  method.invoke(spiLoader.getSpi(spi.value()), args);

                        }
                    }

                }
                return null;
            }
        });

    }
}
