package com.zhangdy.test.thread;

import java.util.concurrent.LinkedBlockingQueue;

public class PrintProcess
        extends Thread  implements IRequestProcessor{

    LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue(100);


    private volatile boolean isFinish=false;

    @Override
    public void process(Request request) {
        queue.add(request);

    }

    @Override
    public void run() {
        while(!isFinish){ //不建议这么写
            try {
                Request request=queue.take();//阻塞式获取数据
                //真正的处理逻辑
                System.out.println("printProcessor:"+request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
