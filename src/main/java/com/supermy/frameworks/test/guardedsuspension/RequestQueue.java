package com.supermy.frameworks.test.guardedsuspension;

import java.util.LinkedList;

/**
 * Created by AlexLc on 2020/3/22.
 */
public class RequestQueue {
    private LinkedList queue = new LinkedList();

    public synchronized Request getRequest(){
        while (queue.size() == 0){
            try {
                //等待直到新的Request加入队列
                wait();
            }catch (InterruptedException e){

            }
        }

        //返回Request队列的第一个请求
        return (Request)queue.remove();
    }

    public synchronized void addRequest(Request request){
        queue.add(request);

        //通知getRequest()方法
        notifyAll();
    }
}
