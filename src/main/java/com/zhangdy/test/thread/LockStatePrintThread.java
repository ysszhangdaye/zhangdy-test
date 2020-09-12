package com.zhangdy.test.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockStatePrintThread implements Runnable {

    private int i;
    private Lock lock;
    private State state;
    private String name;

    private int count = 10;
    @Override
    public void run() {
        while(count > 0) {
//            lock.lock();
            if (state.state % 3 == i) {
                System.out.println(name);
                count--;
                state.state++;
            }
//            lock.unlock();
        }
    }

    public LockStatePrintThread(int i, Lock lock, State state, String name) {
        this.i = i;
        this.lock = lock;
        this.state = state;
        this.name = name;
    }

    private static class State {
        protected int state = 0;
    }

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        State state = new State();

        LockStatePrintThread threadA = new LockStatePrintThread(0, lock, state, "A");
        LockStatePrintThread threadB = new LockStatePrintThread(1, lock, state, "B");
        LockStatePrintThread threadC = new LockStatePrintThread(2, lock, state, "C");

        new Thread(threadA).start();
        new Thread(threadB).start();
        new Thread(threadC).start();
    }
}
