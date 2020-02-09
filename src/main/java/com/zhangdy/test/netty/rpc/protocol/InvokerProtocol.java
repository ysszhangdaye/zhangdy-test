package com.zhangdy.test.netty.rpc.protocol;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvokerProtocol implements Serializable {

    private String clazzName;
    private String methodName;
    private Class<?>[] paramClasses;
    private Object[] args;

}
