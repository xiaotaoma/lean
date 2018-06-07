package com.mxt.concurrent.ConsumerAndProducer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {
    public static LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue(5);



    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Producer());
        executorService.submit(new Consumer());
        //executorService.shutdownNow();
    }


}
