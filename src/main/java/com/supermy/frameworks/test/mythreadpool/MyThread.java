package com.supermy.frameworks.test.mythreadpool;

/**
 * 自定义线程
 * 永不退出，在手动关闭前永不结束，一直等待新任务达到
 * Created by AlexLc on 2020/4/11.
 */
public class MyThread extends Thread {
    private MyThreadPool pool;
    private Runnable target;
    private boolean isShutdown = false;
    private boolean isIdle = false;

    public MyThread(Runnable target, String name, MyThreadPool pool){
        super(name);
        this.target = target;
        this.pool = pool;
    }

    public Runnable getTarget() {
        return target;
    }

    public boolean isIdle() {
        return isIdle;
    }

    @Override
    public void run() {
        //只要不关闭，就一直不结束该线程
        while (!isShutdown){
            isIdle = false;
            if(target != null){
                //执行任务
                target.run();
            }

            //任务结束完了,转为空闲状态
            isIdle = true;

            try {
                //任务结束后，不关闭线程，而是放入线程池空闲队列
                pool.repool(this);

                synchronized (this){
                    //线程空闲，等待新任务到来
                    wait();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            isIdle = false;
        }
    }

    public synchronized void setTarget(Runnable newTarget){
        //设置新任务
        this.target = newTarget;
        //通知run()开始执行此新任务
        notifyAll();
    }

    public synchronized void shutdown(){
        isShutdown = true;
        notifyAll();
    }
}
