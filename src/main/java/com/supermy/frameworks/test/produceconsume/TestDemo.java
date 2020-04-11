package com.supermy.frameworks.test.produceconsume;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Created by AlexLc on 2020/4/11.
 */
public class TestDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<ConsumeData> queue = new LinkedBlockingDeque<>(10);

        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);

        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("consume-pool-%d").build();
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());


        threadPoolExecutor.execute(producer1);
        threadPoolExecutor.execute(producer2);
        threadPoolExecutor.execute(producer3);
        threadPoolExecutor.execute(consumer1);
        threadPoolExecutor.execute(consumer2);
        threadPoolExecutor.execute(consumer3);

        Thread.sleep(10 * 1000);

        producer1.stop();
        producer2.stop();
        producer3.stop();

        Thread.sleep(3000);
        threadPoolExecutor.shutdown();
    }
}
