package com.supermy.frameworks.test.mythreadpool;

/**
 * Created by AlexLc on 2020/4/11.
 */
public class TestThread implements Runnable {
    private String name;

    public TestThread(){

    }

    public TestThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            //当不使用线程池(产生1000个线程)
//            new Thread(new TestThread("testThreadPool" + Integer.valueOf(i))).start();

            //使用自定义线程池(线程可复用，边调度边执行,实际产生线程数<1000)
            MyThreadPool.getInstance().start(new TestThread("testThreadPool" + Integer.valueOf(i)));
        }

        //计算耗时
        System.out.println("Execute elapsed time:" + (System.currentTimeMillis() - start));
        System.out.println("Total thread count is:" + MyThreadPool.getInstance().getCreatedThreadsCount());
    }
}
