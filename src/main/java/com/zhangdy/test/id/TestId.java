package com.zhangdy.test.id;

import com.google.common.collect.Maps;
import com.zhangdy.util.IDS;
import com.zhangdy.util.IdWorker;
import com.zhangdy.util.ThreadUtil;

import java.util.Map;

/**
 * <Description> <br>
 *
 * @author 001<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/06/11 13:15 <br>
 * @see com.zhangdy.test.id <br>
 */
public class TestId {

    public static void main(String[] args) {

//        System.out.println(IDS.uniqueID());
        Map<Integer, Integer> map = Maps.newHashMap();
        for (int i=0;;i++){
            int newNum = (int)((Math.random()*9+1)*100000);
            if (map.containsKey(newNum)) {
                map.put(newNum, map.get(newNum) + 1);
                System.out.println("exists:" + newNum + ",count:" + map.get(newNum) + ",i:" + i);
            } else {
                map.put(newNum, 1);
            }
            ThreadUtil.SET_MILLISECONDS(10);
        }



    }

}
