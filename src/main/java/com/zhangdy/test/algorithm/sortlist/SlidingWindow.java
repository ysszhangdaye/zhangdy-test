package com.zhangdy.test.algorithm.sortlist;


/**
 * 自增主键幂等校验滑动窗口（单向链表，非线程安全）
 */
public class SlidingWindow {

    public static void main(String[] args) {

        // 初始化滑动窗口最小值 （实际业务为主可以为-1 0 ）
        Node head = new Node(-1, null);

        traverse(head);

        addAndRefresh(1L, head);
        System.out.println("#加入 " + 1);
        traverse(head);

        addAndRefresh(0L, head);
        System.out.println("#加入 " + 0);

        traverse(head);

        addAndRefresh(3L, head);
        System.out.println("#加入 " + 3);
        traverse(head);

        addAndRefresh(2L, head);
        System.out.println("#加入 " + 2);;
        traverse(head);

        addAndRefresh(5L, head);
        System.out.println("#加入 " + 5);
        traverse(head);

        addAndRefresh(6L, head);
        System.out.println("#加入 " + 6);
        traverse(head);

        addAndRefresh(7L, head);
        System.out.println("#加入 " + 7);
        traverse(head);

        addAndRefresh(4L, head);
        System.out.println("#加入 " + 4);
        traverse(head);

        addAndRefresh(10L, head);
        System.out.println("#加入 " + 10);
        traverse(head);
        addAndRefresh(100L, head);
        System.out.println("#加入 " + 100);
        traverse(head);
        addAndRefresh(50L, head);
        System.out.println("#加入 " + 50);
        traverse(head);

        System.out.println(idempotent(1L, head));;
        System.out.println(idempotent(11L, head));;
        System.out.println(idempotent(500L, head));;

    }

    public static boolean idempotent(long key, Node head){
        // 小于等于最小的  肯定命中幂等
        if (key <= head.value) {
            return true;
        }
        // 链表里有命中幂等  没有则 未命中幂等
        return contains(key, head);
    }


    public static void traverse(Node head){
        while(head !=null){
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }
    public static boolean contains(long key, Node head){
        while (head != null) {
            if (head.value == key) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    public static void addAndRefresh(long key, Node head){
        add(key, head);
        refresh(head);
    }


    public static void add(long key, Node head){
        Node pre = null;
        while(head != null){
            if (head.value > key ) {
                // 插入
                Node node = new Node(key, head);
                if (pre == null) {
                    head = node;
                    return;
                } else {
                    pre.next = node;
                }
                return;
            } else {
                pre = head;
                head = head.next;
            }
        }
        pre.next = new Node(key, null);
    }

    public static void refresh(Node head){
        while (head != null && head.next != null) {
            if (head.value + 1 == head.next.value) {
                // 删除头结点
                Node next = head.next;
                head.next = next.next;
                head.value = next.value;
                next = null;
                continue;
            } else {
                // 第一个 和 第二个不是自增直接结束循环
                break;
            }
        }
    }

   public static class Node {
        public long value;
        public Node next;

        public Node(long value, Node next){
            this.value = value;
            this.next = next;
        }

    }
}
