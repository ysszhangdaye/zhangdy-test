package com.zhangdy.test.fourlinks.client;

import com.zhangdy.test.fourlinks.entity.Checkerboard;
import com.zhangdy.test.fourlinks.entity.Gamer;
import com.zhangdy.util.IDS;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {



    public static void main(String[] args) {
        Gamer creator = Gamer.builder()
                .name("劳拉")
                .id(IDS.uniqueID())
                .symbol("O")
                .robot(true)
                .build();

        Gamer gamer = Gamer.builder()
                .name("张三")
                .id(IDS.uniqueID())
                .symbol("X")
                .robot(false)
                .build();

        /** 创建棋盘  棋盘所有者为劳拉 */
        Checkerboard checkerboard = Checkerboard.create(creator, gamer);
        System.out.println("Hi,我是劳拉，我们来玩一局四连环, 我用O型棋子，你用X型棋子。");
        System.out.println("游戏规则：双方。。。");
        System.out.println("开始了！这是棋盘的初始状态");
        checkerboard.print();
        checkerboard.start();
    }

}
