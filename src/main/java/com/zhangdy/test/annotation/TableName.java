package com.zhangdy.test.annotation;

import java.lang.annotation.*;

/**
 * 表明注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TableName {
    String value();
}
