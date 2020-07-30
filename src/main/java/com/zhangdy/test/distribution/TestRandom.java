package com.zhangdy.test.distribution;

import lombok.var;

import java.util.Random;

public class TestRandom {


    public static void main(String[] args) {
        Random random = new Random();
        double min = 1.0;
        double max = 10.0;

        for (int i=0;i<1000;i++) {
            double boundedDouble = min +random.nextDouble()* (max - min);
            System.out.println(boundedDouble);
        }



//        for (int i = 1; i < 1000; i++) {
//            double numberInNormalDistribution = getNumberInNormalDistribution(180, 10);
//            System.out.println(numberInNormalDistribution);
//        }
    }
    public static double getNumberInNormalDistribution(double mean, double std_dev){
        return mean+(randomNormalDistribution()*std_dev);
    }


    public static double randomNormalDistribution(){
        double u=0.0, v=0.0, w=0.0, c=0.0;
        do{
            //获得两个（-1,1）的独立随机变量
            u=Math.random()*2-1.0;
            v=Math.random()*2-1.0;
            w=u*u+v*v;
        }while(w==0.0||w>=1.0);
        //这里就是 Box-Muller转换
        c=Math.sqrt((-2*Math.log(w))/w);
        //返回2个标准正态分布的随机数，封装进一个数组返回
        //当然，因为这个函数运行较快，也可以扔掉一个
        //return [u*c,v*c];
        return u*c;
    }

}
