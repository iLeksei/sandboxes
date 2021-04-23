package com.lessons.ConcurrentDemo.CyclicBarrierDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
  public static void main(String[] args) {
    CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
      System.out.println("all threads was counted!");
//        Thread.sleep(1000);
    });

    new Thread(new MyThread(cyclicBarrier, 40)).start();
    new Thread(new MyThread(cyclicBarrier, 200)).start();
    new Thread(new MyThread(cyclicBarrier, 10)).start();
    new Thread(new MyThread(cyclicBarrier, 1000)).start();
    new Thread(new MyThread(cyclicBarrier, 2000)).start();
    new Thread(new MyThread(cyclicBarrier, 3000)).start();

  }

  private static class MyThread implements Runnable {
    private CyclicBarrier cyclicBarrier;
    private long end;
    private long total = 0;
    public MyThread(CyclicBarrier cyclicBarrier, long end) {
        this.cyclicBarrier = cyclicBarrier;
        this.end = end;
    }

    @Override
    public void run() {
      for (long i = 0; i < end; i++) {
        total+=i;
      }
      try {
        System.out.println(Thread.currentThread() + ": total count is: " + this.total);
        cyclicBarrier.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    }
  }
}
