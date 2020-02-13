package com.zhangdy.test.lambda;

import com.zhangdy.test.java.User;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.function.*;

public class Test1 {


    public static void main(String[] args) throws Exception {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.DECEMBER, 31);
        Date strDate = calendar.getTime();
        DateFormat formatUpperCase = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("2019-08-31 to yyyy-MM-dd: " + formatUpperCase.format(strDate));
        formatUpperCase = new SimpleDateFormat("YYYY-MM-dd");
        System.out.println("2019-08-31 to YYYY/MM/dd: " + formatUpperCase.format(strDate));

        SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyyMMdd");


        Date parse = yyyymmdd.parse("20191231");

        String format = yyyymmdd.format(parse);
        System.out.println(format);

//        Predicate<Integer> predicate = x -> x > 18;
//        User zhangsan = new User("zhangsan", 19, BigDecimal.ZERO);
//        System.out.println("张三的年龄大于18吗？：" + predicate.test(zhangsan.getAge()));
//        Consumer consumer = System.out::println;
//        consumer.accept("who are you");
//
//        Function<User, Integer> getAge = User::getAge;
//        System.out.println(getAge.apply(zhangsan));
//
//        Supplier supplier = () -> Integer.valueOf(BigDecimal.TEN.toString());
//        System.out.println(supplier.get());
//
//        UnaryOperator<Boolean> unaryOperator = uglily -> !uglily;
//        Boolean apply2 = unaryOperator.apply(true);
//        System.out.println(apply2);
//
//
//        BinaryOperator<Integer> operator = (x, y) -> x * y;
//        Integer integer = operator.apply(2, 3);
//        System.out.println(integer);
//        test(() -> "w213");
    }


    public static void test(Worker worker) {
        String work = worker.work();
        System.out.println(work);
    }

    public interface Worker {
        String work();
    }
}
