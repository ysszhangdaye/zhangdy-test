package com.zhangdy.test.string;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class TestA {


    public static void main(String[] args) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        TestEntity2 testEntity2 = new TestEntity2();
        testEntity2.setA("123123123");
        TestEntity testEntity = new TestEntity<>();
        testEntity.setWatchAddress(true);
        testEntity.setEntity(testEntity2);
        System.out.println(objectMapper.writeValueAsString(testEntity));
        TestEntity<TestEntity2> testEntity1 = objectMapper.readValue(objectMapper.writeValueAsString(testEntity), TestEntity.class);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(os);
        output.writeObject(testEntity);
        output.close();

        ByteArrayInputStream bis = new ByteArrayInputStream(os.toByteArray());
        Hessian2Input input = new Hessian2Input(bis);
        TestEntity test = (TestEntity) input.readObject();
        System.out.println(2);


    }
    @Getter
    @Setter
    public static class TestEntity<T> implements Serializable {
        private Boolean watchAddress;
        private TestEntity2 entity;
    }

    @Getter
    @Setter
    public static class TestEntity2 implements Serializable{
        private String a;
    }


}
