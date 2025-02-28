package com.rtogether.api.util;

import java.lang.reflect.Field;

public class ServiceUtils {
    public static <T> void copyNonNullProperties(T source, T target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Source and target must not be null");
        }

        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = target.getClass().getDeclaredFields();

        for (Field sourceField : sourceFields) {
            try {
                sourceField.setAccessible(true);
                Object value = sourceField.get(source);

                if (value != null) {
                    for (Field targetField : targetFields) {
                        if (targetField.getName().equals(sourceField.getName()) &&
                                targetField.getType().isAssignableFrom(sourceField.getType())) {
                            targetField.setAccessible(true);
                            targetField.set(target, value);
                            break;
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error while copying properties: ", e);
            }
        }
    }
}
