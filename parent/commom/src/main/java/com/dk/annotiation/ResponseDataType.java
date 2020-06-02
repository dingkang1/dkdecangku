package com.dk.annotiation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseDataType {
    Class<?> value() default String.class;
}
