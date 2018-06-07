package com.mxt.concurrent.ConsumerAndProducer;

import java.util.Random;

/**
 * 生产者
 */
public class Producer implements Runnable{
    private Random random;
    public Producer() {
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (true) {
            try {
                int i = random.nextInt(Integer.MAX_VALUE);
                Test.queue.put(i);
                System.out.println("放入数字：" + i + ",当前容量：" + Test.queue.size() + ", thread status :" +Thread.currentThread().getState().name());
            }catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
