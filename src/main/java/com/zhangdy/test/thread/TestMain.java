package com.zhangdy.test.thread;

public class TestMain {

    static IRequestProcessor preProcess;

    public void startup(){
        PrintProcess printProcess = new PrintProcess();

        new Thread(printProcess, "print-thread").start();
        SaveProcess saveProcess = new SaveProcess(printProcess);
        new Thread(saveProcess, "save-thread").start();
        preProcess = new PreProcess(saveProcess);

        new Thread(((PreProcess)preProcess), "pre-thread").start();


    }

    public static void main(String[] args) {
        TestMain testMain = new TestMain();
        testMain.startup();



        Request request=new Request();
        request.setName("zhangdy1");
        preProcess.process(request);
        Request request2=new Request();

        request2.setName("zhangdy2");
        preProcess.process(request2);

    }

}
