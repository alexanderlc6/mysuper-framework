package com.supermy.frameworks.test.mythreadpool;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Vector;

/**
 * Created by AlexLc on 2020/4/11.
 */
public class MyThreadPool {
    private static MyThreadPool instance = null;

    //空闲线程队列
    private List<MyThread> idleThreadList;
    //已有的线程总数
    private int threadCounter;
    private boolean isShutdown = false;

    private MyThreadPool(){
        idleThreadList = new Vector(5);
        threadCounter = 0;
    }

    public int getCreatedThreadsCount(){
        return threadCounter;
    }

    /**
     * 初始化单例对象
     * @return
     */
    public synchronized static MyThreadPool getInstance(){
        if(instance == null){
            instance = new MyThreadPool();
        }

        return instance;
    }

    /**
     * 线程进入线程池
     * @param repoolingThread
     */
    protected synchronized void repool(MyThread repoolingThread){
        if(!isShutdown){
            idleThreadList.add(repoolingThread);
        }else {
            repoolingThread.shutdown();
        }
    }

    /**
     * 停止线程池中所有线程
     */
    public synchronized void shutdown(){
        isShutdown = true;
        for (int threadIndex = 0; threadIndex < idleThreadList.size(); threadIndex++){
            MyThread idleThread = idleThreadList.get(threadIndex);
            idleThread.shutdown();
        }
    }

    /**
     * 执行任务
     */
    public synchronized void start(Runnable target){
        MyThread curWorkThread;

        if(idleThreadList != null && idleThreadList.size() > 0){
            int lastIndex = idleThreadList.size() - 1;
            curWorkThread = idleThreadList.get(lastIndex);
            idleThreadList.remove(lastIndex);

            //立即执行该任务
            curWorkThread.setTarget(target);
        }else {
            //若无空闲线程，则创建新线程
            threadCounter++;

            curWorkThread = new MyThread(target, "MyThread #" +threadCounter, this);

            //启动该工作线程
            curWorkThread.start();
        }
    }
}
