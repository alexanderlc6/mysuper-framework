package com.supermy.frameworks.test.produceconsume;

import org.springframework.beans.factory.annotation.Qualifier;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by AlexLc on 2020/4/11.
 */
public class Consumer implements Runnable {
    private BlockingQueue<ConsumeData> queue;
    private static final int SLEEP_TIME = 1000;

    public Consumer(BlockingQueue<ConsumeData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("Start consumer id:" + Thread.currentThread().getId());
        Random r = new Random();

        try{
            while (true){
                ConsumeData consumeData = queue.take();
                if(null != consumeData){
                    //计算平方值
                    int total = consumeData.getData() * consumeData.getData();
                    System.out.println(MessageFormat.format("{0}*{1}={2}",
                            consumeData.getData(), consumeData.getData(), total));
                    Thread.sleep(r.nextInt(SLEEP_TIME));
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();;
            Thread.currentThread().interrupt();
        }
    }
}
