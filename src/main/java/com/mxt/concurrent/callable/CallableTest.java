package com.mxt.concurrent.callable;

import java.util.concurrent.*;

/**
 * Callable、Future和FutureTask
 *
 *
 */
public class CallableTest {
    public static void main(String[] args) {
        test2();
    }

    /**
     * 使用Callable和Future获取执行结果
     */
    private static void test1() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> submit = executorService.submit(new MyTask());
        executorService.shutdown();

        try {
            System.out.println("主线程执行");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Integer integer = submit.get();
            System.out.println("执行结果" + integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    private static void test2() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask<>(new MyTask());
        executorService.submit(futureTask);
        executorService.shutdown();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程执行");

        try {
            Integer integer = futureTask.get();
            System.out.println("子线程结果，" + integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("end");
    }

    private static class MyTask implements Callable {

        @Override
        public Object call() throws Exception {
            System.out.println("子线程正在计算");
            Thread.sleep(10000);
            int sum = 0;
            for (int i = 0; i < 10000; i++) {
                sum+=i;
            }
            return sum;
        }
    }
}
