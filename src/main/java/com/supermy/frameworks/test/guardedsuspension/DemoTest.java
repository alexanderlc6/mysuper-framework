package com.supermy.frameworks.test.guardedsuspension;

/**
 * Created by AlexLc on 2020/3/22.
 */
public class DemoTest {
    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();

        //服务线程开启
        for (int i = 0; i < 10; i++) {
            new ServerThread(requestQueue, "ServerThread" + i).start();
        }

        //客户端请求线程开启(请求速度高于服务端处理速度，通过RequestQueue缓存任务)
        for (int i = 0; i < 10; i++) {
            new ClientThread(requestQueue, "ClientThread" + i).start();
        }
    }
}
