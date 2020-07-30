package com.zhangdy.test.fourlinks.entity;

import lombok.*;


/**
 * 玩家
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Gamer {
    private String name;
    private Long   id;
    private String symbol;
    /** true-机器人 false-真实玩家 */
    private boolean robot;
}
