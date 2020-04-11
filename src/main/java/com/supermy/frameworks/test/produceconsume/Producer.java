package com.supermy.frameworks.test.produceconsume;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by AlexLc on 2020/4/11.
 */
public class Producer implements Runnable {

    private volatile boolean isRunning = true;
    private BlockingQueue<ConsumeData> queue;
    private static AtomicInteger count = new AtomicInteger();

    private static final int SLEEP_TIME = 1000;

    public Producer(BlockingQueue<ConsumeData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        ConsumeData consumeData = null;
        Random r = new Random();
        System.out.println("Start producer id:" + Thread.currentThread().getId());

        try {
            while (isRunning){
                Thread.sleep(r.nextInt(SLEEP_TIME));
                consumeData = new ConsumeData(count.incrementAndGet());
                System.out.println("Task data [" + consumeData.getData() +"] is put into queue.");

                if(!queue.offer(consumeData, 3, TimeUnit.SECONDS)){
                    System.out.println("Failed to input data:" + consumeData);
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop(){
        isRunning = false;
    }
}
