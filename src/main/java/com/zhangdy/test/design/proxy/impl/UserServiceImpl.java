package com.zhangdy.test.design.proxy.impl;

import com.zhangdy.test.design.proxy.UserService;
import com.zhangdy.test.design.proxy.entity.User;

public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(Long userId) {
        return User.builder()
                .userId(userId)
                .userName("zhangsan").build();
    }
}
