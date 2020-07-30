package com.zhangdy.test.list;

import com.alibaba.fastjson.JSON;

public class DoubleNumberPlus {

    public static void main(String[] args) {

        // 431
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(4);


        // 125
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(1);

        // 返回 556 链表为 655
        ListNode plus = plus(l1, l2);
        System.out.println(JSON.toJSONString(plus));

    }

    public static ListNode plus(ListNode l1, ListNode l2){
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        ListNode p = l1;
        ListNode q = l2;
        int carry = 0;
        while(p != null || q != null){
            int x = p != null ? p.value : 0;
            int y = q != null ? q.value : 0;
            int sum = x + y + carry;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return dummyHead.next;

    }



}
