package com.mcg.spi;

/**
 * @author mcg
 */
public class SpiUtil {

  public static Boolean isSpi(Class clazz) {

     return clazz.getAnnotation(SPI.class)!=null;

   }


    public static SPI getSpiValue(Class clazz) {
      if(isSpi(clazz)) {
          return (SPI)clazz.getAnnotation(SPI.class);
      }
      return null;
   }

}
