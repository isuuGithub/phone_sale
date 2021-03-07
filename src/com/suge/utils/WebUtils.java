package com.suge.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author suu
 * @create 2020-12-07 1:25
 */
public class WebUtils {
    /**
     * 把Map中的值注入到对应的JavaBean属性中。
     * @param value Map值
     * @param bean 需要属性值注入的bean对象
     * @param <T> 泛型
     * @return 返回一个注入值的bean对象（匿名对象，需要接收）
     */
    public static <T> T copyParamToBean(Map value, T bean){

        try {
            //把所有请求的参数都注入到bean对象中(依赖JavaBean的setXxx();)
            BeanUtils.populate(bean ,value);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //回一个注入值的bean对象（匿名对象，需要接收）
        return bean;
    }

    /**
     * 将字符串转换成为int类型的数据
     * @param strInt
     * @param defaultValue 如果strInt为null则该值为默认值
     * @return
     */
    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
