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
        System.out.println(Integer.MAX_VALUE);
        System.out.println( 12*60 *60 *1000);

//        List<String> list = Lists.newArrayList();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        list.add("5");
//        list.add("6");
//        list.add("7");
//        System.out.println(String.join(",", list));

//
//        User lisi = new User("lisi");
//        User zhangsan = new User("zhangsan");
//
//
//
//
//        User lisi1 = new User("lisi1");
//        User zhangsan1 = new User("zhangsan1");
//
//
//        List<User> lista  = Lists.newArrayList();
//        lista.add(lisi);
//        lista.add(zhangsan);
//
//
//
//        List<User> listb  = Lists.newArrayList(lisi1, zhangsan1);
//
//        listb.add(lisi1);
//        listb.add(zhangsan);
//
//        List<User> collect = lista.stream().filter(a -> listb.stream().noneMatch(b -> a.getName().equals(b.getName()))).collect(Collectors.toList());
//
//        System.out.println(JSON.toJSONString(collect));


//
//        List<User> collect = lista.stream().filter(a -> {
//            Optional<User> first = listb.stream().filter(bo -> a.name.equalsIgnoreCase(bo.name)).findFirst();
//            return !first.isPresent();
//
//        }).collect(Collectors.toList());
//
//        System.out.println(JSON.toJSONString(collect));

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class User {


        private String name;
    }
}
