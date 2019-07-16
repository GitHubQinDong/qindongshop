package com.qindong.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author qind6
 * @date 2019/7/4
 */
public class PackExample {
    public static Object packingExample(Object entity, Object entityExampleCriteria) {
        try {
            // 得到类对象
            Class<?> userCla = entity.getClass();
            /* 得到类中的所有属性集合 */
            Field[] fs = userCla.getDeclaredFields();
            Class<?> criteria = entityExampleCriteria.getClass();
            Object CriteriaTemp = criteria.newInstance();
            for (int i = 0; i < fs.length; i++) {
                String fieldName = "and"
                        + fs[i].getName().substring(0, 1).toUpperCase()
                        + fs[i].getName().substring(1) + "EqualTo";
                String fieldType = fs[i].getType().getName();
                fs[i].setAccessible(true);
                Method method = criteria.getMethod(fieldName,
                        Class.forName(fieldType));
                if (fs[i].get(entity) != null) {
                    method.invoke(CriteriaTemp, fs[i].get(entity));
                }
            }
            return CriteriaTemp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entityExampleCriteria;
    }
}
