//package com.zhangdy.test.algorithm.leetcode;
//
//import java.util.ArrayDeque;
//import java.util.Queue;
//
//public class P225 {
//    public static void main(String[] args) {
//
//    }
//    class MyStack {
//
//        /** Initialize your data structure here. */
//
//        private Queue<Integer> q1;
//        private Queue<Integer> q2;
//
//        public MyStack() {
//            q1 = new ArrayDeque<>();
//            q2 = new ArrayDeque<>();
//        }
//
//        /** Push element x onto stack. */
//        public void push(int x) {
//            q1.add(x);
//        }
//
//        /** Removes the element on top of the stack and returns that element. */
//        public int pop() {
//            if (q2.isEmpty()) {
//                while(!q1.isEmpty()){
//                    q2.add(q1.poll());
//                }
//            }
//            if (!q2.isEmpty()) {
//                return q2.poll();
//            }
//            throw new RuntimeException("queue is null");
//        }
//
//        /** Get the top element. */
//        public int top() {
//
//        }
//
//        /** Returns whether the stack is empty. */
//        public boolean empty() {
//
//        }
//}
