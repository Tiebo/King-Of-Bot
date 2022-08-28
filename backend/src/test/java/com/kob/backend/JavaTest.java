package com.kob.backend;

import org.junit.jupiter.api.Test;

public class JavaTest {
    @Test
    public void test1() {
        MyThread myThread = new MyThread();
        myThread.start();
        try {
            myThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 100; i++) {
            System.out.println("i + 100 = " + i + 100);
        }
    }
}
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i ++ ) {
            System.out.println("i = " + i);
        }
    }
}
