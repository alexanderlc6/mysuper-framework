package com.supermy.frameworks.test.masterworker;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by AlexLc on 2020/3/22.
 */
public class Worker implements Runnable {
    //任务队列,用于取得子任务
    private Queue<Object> workQueue = new ConcurrentLinkedQueue();

    //子任务处理结果集合
    private Map<String, Object> resultMap = new ConcurrentHashMap();

    public Queue<Object> getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(Queue<Object> workQueue) {
        this.workQueue = workQueue;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while (true){
            //获取子任务
            Object input = workQueue.poll();

            if(input == null){
                break;
            }

            //处理子任务,加入结果集合
            Object result = handle(input);
            resultMap.put(Integer.toString(input.hashCode()), result);
        }
    }

    public Object handle(Object input) {
        return input;
    }
}
