package com.company.fildname;

import java.lang.reflect.Field;

public class FieldName {

    public static String getFieldName(Field[] fields,String name) {
        for(Field field: fields) {
            field.setAccessible(true);
          if  (field.getName()==name)
            {

                boolean annotationPresent = field
                        .isAnnotationPresent(DisplayName.class);
                if (annotationPresent) {
                    DisplayName annotation = field.getAnnotation(DisplayName.class);
                    return annotation.name();
                }
                return field.getName();
            }
        }
        return null;
    }

    public static String getFieldName(Field field) {
        field.setAccessible(true);

                boolean annotationPresent = field
                        .isAnnotationPresent(DisplayName.class);
                if (annotationPresent) {
                    DisplayName annotation = field.getAnnotation(DisplayName.class);
                    return annotation.name();
                }
                return field.getName();
    }
}
