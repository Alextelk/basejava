package com.urise.webapp;

public class MainDeadlock {
    public static void main(String[] args) {
        final Object LOCK_1 = new Object();
        final Object LOCK_2 = new Object();
        deadlock(LOCK_1, LOCK_2);
        deadlock(LOCK_2, LOCK_1);
    }

    public static void deadlock(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println("The thread " + Thread.currentThread().getName() + " is running");
            System.out.println("Thread " + Thread.currentThread().getName() + " try to capture lock1");
            synchronized (lock1) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread " + Thread.currentThread().getName() + " try to capture lock2");
                synchronized (lock2) {
                }
            }
        }).start();
    }
}