package com.zhangdy.test.chat.protocol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 *  自定义IM协议，Instant Messaging Protocol即时通信协议
 */
@Getter
@AllArgsConstructor
@ToString(callSuper = true)
public enum  IMP {


    /** 系统消息 */
    SYSTEM("SYSTEM"),
    /** 登录指令 */
    LOGIN("LOGIN"),
    /** 登出指令 */
    LOGOUT("LOGOUT"),
    /** 聊天消息 */
    CHAT("CHAT"),
    /** 送鲜花 */
    FLOWER("FLOWER");

    private String command;
    public static boolean isIMP(String content){
        return content.matches("^\\[(SYSTEM|LOGIN|LOGIN|CHAT)\\]");
    }

}
