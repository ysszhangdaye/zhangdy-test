package com.zhangdy.test.annotation;


import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {

    String value() default "";

    String format() default "'%s'";

}
