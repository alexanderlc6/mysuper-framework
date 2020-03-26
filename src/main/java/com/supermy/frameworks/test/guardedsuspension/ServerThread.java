package com.supermy.frameworks.test.guardedsuspension;

import java.util.concurrent.FutureTask;

/**
 * Created by AlexLc on 2020/3/22.
 */
public class ServerThread extends Thread {
    private RequestQueue requestQueue;

    public ServerThread(RequestQueue requestQueue, String name){
        super(name);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        while (true){
            final Request request = requestQueue.getRequest();
            final FutureData futureData = (FutureData)request.getResponseData();

            RealData realData = new RealData((request.getName()));
            //处理完成后，通知客户线程
            futureData.setRealData(realData);

//            try {
//                //模拟业务处理
//                Thread.sleep(200);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }

            System.out.println(Thread.currentThread().getName() + " handles " + request);
        }
    }
}
