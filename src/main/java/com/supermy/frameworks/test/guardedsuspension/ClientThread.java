package com.supermy.frameworks.test.guardedsuspension;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexLc on 2020/3/22.
 */
public class ClientThread extends Thread {
    private RequestQueue requestQueue;
    private List<Request> myRequestList = new ArrayList<>();

    public ClientThread(RequestQueue requestQueue, String name){
        super(name);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Request request = new Request("RequestID:" + i + " Thread name:" + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + " requests " + request);

            //设置一个FutureData返回值
            request.setRespData(new FutureData());
            requestQueue.addRequest(request);

            //发送请求
            myRequestList.add(request);

            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){

            }
        }

        //取得服务端返回值
        for (Request req : myRequestList){
            System.out.println("ClientThread name is:" + Thread.currentThread().getName()
                                + " Response is:" + req.getResponseData().getContent());
        }

        System.out.println(Thread.currentThread().getName() + " request end");
    }
}
