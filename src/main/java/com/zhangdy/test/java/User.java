package com.zhangdy.test.java;


import com.alibaba.fastjson.JSON;
import lombok.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    @I18nConvert
    private String name;
    @I18nConvert
    private List<String> language;
    @I18nConvert
    private List<User> user;

    public User(String name) {
        this.name = name;
    }

}
