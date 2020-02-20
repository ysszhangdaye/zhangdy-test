package com.zhangdy.test.list;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestSteam {

    public static void main(String[] args) {

        User lisi = new User("lisi");
        User zhangsan = new User("zhangsan");
        User lisi1 = new User("lisi");
        User zhangsan1 = new User("zhangsan1");
        List<User> lista  = Lists.newArrayList();
        lista.add(lisi);
        lista.add(zhangsan);



        List<User> listb  = Lists.newArrayList(lisi1, zhangsan1);

        listb.add(lisi1);
        listb.add(zhangsan1);


        List<User> collect = lista.stream().filter(a -> {
            Optional<User> first = listb.stream().filter(bo -> a.name.equalsIgnoreCase(bo.name)).findFirst();
            return !first.isPresent();

        }).collect(Collectors.toList());

        System.out.println(JSON.toJSONString(collect));

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class User {


        private String name;
    }
}
