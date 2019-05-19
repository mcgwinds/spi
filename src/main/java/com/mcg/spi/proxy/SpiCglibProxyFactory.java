package com.mcg.spi.proxy;

import com.mcg.spi.SPI;
import com.mcg.spi.SpiLoader;
import com.mcg.spi.SpiUtil;
import net.sf.cglib.Enhancer;
import net.sf.cglib.MethodInterceptor;
import net.sf.cglib.MethodProxy;

import java.lang.reflect.Method;


/**
 * @author wmk
 */
public class SpiCglibProxyFactory {

    public <T> T newSpiProxy(Class targetClass, SpiLoader spiLoader) {

       return (T)Enhancer.enhance(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                Class superclass = proxy.getClass().getSuperclass();
                if (null != superclass) {
                        if (SpiUtil.isSpi(superclass)) {
                            SPI spi = SpiUtil.getSpiValue(superclass);
                            return  method.invoke(spiLoader.getSpi(spi.value()), args);

                        }


                }
                return null;
            }
        });
    }
}
