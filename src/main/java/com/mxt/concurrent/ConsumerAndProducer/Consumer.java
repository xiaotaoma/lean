package com.mxt.concurrent.ConsumerAndProducer;

import java.math.BigDecimal;

/**
 * 消费者
 */
public class Consumer implements Runnable{

    @Override
    public void run() {
        while (true) {
            Integer integer = get();
            BigDecimal bigDecimal = new BigDecimal(integer);
            bigDecimal = bigDecimal.multiply(bigDecimal);
            System.out.println(bigDecimal + "的平方是：" + bigDecimal.toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Integer get() {
        try {
            Integer take = Test.queue.take();
            System.out.println("取出数字：" + take + ",当前容量：" + Test.queue.size());
            return take;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
