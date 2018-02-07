package com.java.thread;

public class MySynchronized {
    public static void main(String[] args) {
        final MySynchronized mySynchronized = new MySynchronized();
        new Thread() {
            public void run() {
                synchronized (mySynchronized) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread1,start");
                }
            }
        }.start();
        new Thread() {
            public void run() {
                synchronized (mySynchronized) {
                    System.out.println("thread2,start");
                }
            }
        }.start();
    }
}
