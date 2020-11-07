package com.zhangdy.test.netty2.protocol;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvokerProtocol implements Serializable {
    /**
     * 类全限定名
     */
    private String clazzName;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 形参列表
     */
    private Class<?>[] argsClasses;
    /**
     * 实参列表
     */
    private Object[] args;


}
