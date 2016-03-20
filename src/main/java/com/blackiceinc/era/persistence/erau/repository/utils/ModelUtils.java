package com.blackiceinc.era.persistence.erau.repository.utils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ModelUtils {

    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

    private static boolean isWrapperType(Class<?> clazz) {
        return WRAPPER_TYPES.contains(clazz);
    }

    private static Set<Class<?>> getWrapperTypes() {
        Set<Class<?>> ret = new HashSet<>();
        ret.add(String.class);
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        return ret;
    }

    public static boolean areAllFieldsNull(Object obj) throws IllegalAccessException {
        boolean result = true;

        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (isWrapperType(f.getType())) {
                if (f.get(obj) != null) {
                    result = false;
                    break;
                }
            } else {
                if (f.get(obj) != null) {
                    result = areAllFieldsNull(f.get(obj));
                    if (!result){
                        break;
                    }
                }
            }
        }

        return result;
    }

}
