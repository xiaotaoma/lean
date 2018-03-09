package com.mxt.concurrent.block;

/**
 * Created by mxt on 18-3-9.
 */
public class DeadLock {
    private String objId;
    public DeadLock(String objId) {
        this.objId = objId;
    }

    private static void threadPrint(String msg) {
        String name = Thread.currentThread().getName();
        System.out.println(name + ":" + msg);
    }

    private void print(String msg) {
        threadPrint("objId="+objId + " - " + msg);
    }

    public synchronized void checkOther(DeadLock other) {
        print("entering checkOther()");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print("in checkOther(), invoke other.action()");

    }

    public synchronized void action() {
        print("enter action");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print("leaving action()");
    }

    public static void main(String[] args) {
        final DeadLock deadLock1 = new DeadLock("obj1");
        final DeadLock deadLock2 = new DeadLock("obj2");
        Runnable a = new Runnable() {
            public void run() {
                deadLock1.checkOther(deadLock2);
            }
        };
        Thread threadA = new Thread(a);
        threadA.start();

        Runnable b = new Runnable() {
            public void run() {
                deadLock2.checkOther(deadLock1);
            }
        };
        Thread threadB = new Thread(b);
        threadB.start();


        /*threadPrint("finished sleeping");

        threadPrint("about to interrupt() threadA");
        threadA.interrupt();

        try {
            Thread.sleep(1000);
        } catch ( InterruptedException x ) {

        }

        threadPrint("about to interrupt() threadB");
        threadB.interrupt();

        try {
            Thread.sleep(1000);
        } catch ( InterruptedException x ) {

        }

        threadPrint("did that break the deadlock?");*/

    }
}
