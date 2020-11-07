package com.zhangdy.test.algorithm.leetcode;


import com.zhangdy.test.list.ListNode;

import java.util.ArrayList;
import java.util.List;

public class P25Test {


    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        head1.next = node1;
        node1.next = node2;
        node2.next = node5;

        traverse(head1);
    }

    public ListNode reverseKGroup(ListNode head, int k) {





        return null;
    }



    public List<ListNode> split(ListNode head, int k){
        List<ListNode> result = new ArrayList<>();
        int i = 0;
        while(head.next != null){

        }


        return result;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void traverse(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }


}
