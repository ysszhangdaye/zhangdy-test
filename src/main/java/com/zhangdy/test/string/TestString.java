package com.zhangdy.test.string;

import com.google.common.collect.Lists;
import com.zhangdy.util.ThreadUtil;
import org.apache.http.nio.client.HttpAsyncClient;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 12933
 * @Title: TestString
 * @ProjectName zhangdy-test
 * @Description: TODO
 * @date 2019/1/2917:10
 */
public class TestString {


    public void test1(){
        try{
            System.out.println(1);
            ThreadUtil.SET_MILLISECONDS(500);

            test1();

        }catch (Exception e) {

        }


    }

    public static void main(String[] args) {

        Class<TestString> clazz = TestString.class;

        System.out.println(clazz.getName());


//       TestString a= new TestString();
//       a.test1();


//
//
//        List<String> msg = Lists.newArrayList();
//
//        StringBuffer remark = new StringBuffer();
//        msg.forEach(m -> {
//            remark.append(m).append(";");
//        });
//
//         System.out.println("remark:"+remark);

//        Long a = 3750L;
//        a = a/100;
//        System.out.println(a);
//
//        StringBuffer s = new StringBuffer();
//        s.setLength(0);
//        s.append("2222");
//        System.out.println("a"+s);
//
//        int length = 4;
//        String codeFormat = "%0"+String.valueOf(length)+"d";//%03d
//        String str = String.format(codeFormat, 1);
//        System.out.println(str);
    }


}
