package com.lessons.ConcurrentDemo.CountDownLatchDemo;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

  public static void main(String[] args) {
    System.out.println("Start CountDownLatchDemo...");
    CountDownLatch countDownLatch = new CountDownLatch(5);
    Thread thread = new Thread(new MyThread(countDownLatch));
    thread.start();
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Done CountDownLatchDemo...");
  }

  private static class MyThread implements Runnable {
    private CountDownLatch countDownLatch;

    public MyThread(CountDownLatch countDownLatch) {
      this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
      for (int i = 0; i < 5; i++) {
        System.out.println(countDownLatch.getCount());
        countDownLatch.countDown();
      }
    }
  }

}
