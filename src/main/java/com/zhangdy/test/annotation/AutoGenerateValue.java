package com.zhangdy.test.annotation;


import com.zhangdy.test.enums.GenerateTypeEnum;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoGenerateValue {

    GenerateTypeEnum value() default GenerateTypeEnum.IDS;

}
