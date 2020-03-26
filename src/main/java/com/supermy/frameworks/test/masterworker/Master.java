package com.supermy.frameworks.test.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by AlexLc on 2020/3/22.
 */
public class Master {
    //任务队列
    private Queue<Object> workQueue = new ConcurrentLinkedQueue();

    //Worker线程队列
    private Map<String, Thread> threadQueueMap = new HashMap();

    //子任务处理结果集合
    private Map<String, Object> resultMap = new ConcurrentHashMap();

    public Master(Worker worker, int countWorker){
        worker.setWorkQueue(workQueue);
        worker.setResultMap(resultMap);

        for (int i = 0; i < countWorker; i++) {
            threadQueueMap.put(Integer.toString(i), new Thread(worker, Integer.toString(i)));
        }
    }

    /**
     * 判断是否所有队列任务已结束
     * @return
     */
    public boolean isComplete(){
        //任一个任务未结束则不算结束
        for(Map.Entry<String, Thread> entry : threadQueueMap.entrySet()){
            if(entry.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }

        return true;
    }

    /**
     * 提交一个任务
     * @param job
     */
    public void submit(Object job){
        this.workQueue.add(job);
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    /**
     * 开始执行Worker进程处理
     */
    public void execute(){
        for (Map.Entry<String, Thread> entry : threadQueueMap.entrySet()){
            entry.getValue().start();
        }
    }
}
