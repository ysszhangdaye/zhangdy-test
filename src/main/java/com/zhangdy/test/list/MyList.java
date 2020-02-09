package com.zhangdy.test.list;

import com.alibaba.fastjson.JSON;

public class MyList {



    public static void headInsert(ListNode head, ListNode newhead){
        newhead.next = head;
    }

    public static void tailInsert(ListNode tail, ListNode newTail){
        tail.next = newTail;
    }

    public static int find(ListNode head, int value){
        int result = -1;
        int idx = 0;
        while(head != null){
            if (head.value == value) {
                result = idx;
                break;
            }
            idx++;
            head = head.next;
        }
        return result;
    }


    public static void insert(ListNode p, ListNode q){
        ListNode pOldNext = p.next;
        p.next = q;
        q.next = pOldNext;
    }



    public static ListNode reverseList(ListNode head){
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
             next = head.next;
             head.next = pre;
             pre = head;
             head = next;
        }
        return pre;

    }

    public static ListNode getMid(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void delete(ListNode head, ListNode delNode){

        if (delNode == null) {
            return;
        }
        if (delNode.next != null) {
            //删除的时中间节点
            ListNode next = delNode.next;
            delNode.next = next.next;
            delNode.value = next.value;
            next = null;
            return;
        }
        if (delNode.next == null) {

            while(head != null){
                if (head.next != null && head.next == delNode) {
                    head.next = null;
                }
                head = head.next;
            }
        }


    }


    /**
     * 合并两个有序列表 （递归方式）
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode mergeList(ListNode head1, ListNode head2){
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        ListNode head = null;
        if (head1.value < head2.value) {
            head = head1;
            head.next = mergeList(head1.next, head2);
        } else {
            head = head2;
            head.next = mergeList(head1, head2.next);
        }
        return head;
    }


    /**
     * 合并连个有序列表  （循环方式）
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode mergeCycle(ListNode head1, ListNode head2) {

        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        ListNode head = head1.value < head2.value ? head1 : head2;
        ListNode cur1 = head == head1 ? head1 : head2;
        ListNode cur2 = head == head1 ? head2 : head1;

        ListNode pre = null;
        ListNode next = null;

        while (cur1 != null && cur2 != null) {
            if (cur1.value < cur2.value) {
                //这两句就是相当于指针的移动
                pre = cur1;
                cur1 = pre.next;
            } else {
                //这一句就是先保存值
                next = cur2.next;
                //这两句指的是从上面的cur1切换过来，再切换回去
                pre.next = cur2;
                cur2.next = cur1;
                //这两句就是相当于指针的移动
                pre = cur2;
                cur2 = next;
            }
        }
        //针对空的情况做判断
        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }




    public static void main(String[] args) {
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(1);

        ListNode node2 = new ListNode(2);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node8 = new ListNode(8);
        head1.next =   node2;
        node2.next =   node5;
        node5.next =   node6;
        node6.next =   node8;

        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node7 = new ListNode(7);
        ListNode node9 = new ListNode(9);
        head2.next = node3;
        node3.next = node4;
        node4.next = node7;
        node7.next = node9;

        traverse(head1);
        traverse(head2);

//        ListNode listNode = mergeList(head1, head2);
        ListNode listNode = mergeCycle(head1, head2);

        traverse(listNode);


    }


    public static void main1(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = null;


        System.out.print("原始链表     ---> ");
        traverse(node1);



        ListNode node = new ListNode(0);
        headInsert(node1, node);
        System.out.print("插入头节点   ---> ");
        traverse(node);


        ListNode node4 = new ListNode(4);
        tailInsert(node3, node4);
        System.out.print("插入尾节点   ---> ");
        traverse(node);

        int i = find(node, 2);
        System.out.println("2 在链表中位置 ---> " + i);

        insert(node3, new ListNode(6));
        System.out.print("3 节点后插入6 ---> " );
        traverse(node);

        delete(node, node2);
        System.out.print("删除节点2 ---> " );
        traverse(node);

        delete(node, node4);
        System.out.print("删除节点4 ---> " );
        traverse(node);


        ListNode node5 = reverseList(node);
        System.out.print("链表翻转 ---> " );
        traverse(node5);

        ;
//        traverse(node);
//
//        System.out.println(getMid(node).value);
    }


    public static void traverse(ListNode head){
        while(head !=null){
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }


}
