package com.lessons.ConcurrentDemo;

import java.util.ArrayList;
import java.util.Arrays;

public class SynhBlockDemo {
  private int[] a = {0,1,2,3,4,5,6,7,8,9,10};
  private int[] b = {0,11,12,13,14,15,16,17,18,19,20};
  private ArrayList<Integer> intList1 = new ArrayList<>();
  private ArrayList<Integer> intList2 = new ArrayList<>();

  private final Object lockA = new Object();
  private final Object lockB = new Object();

  public static void main(String[] args) throws InterruptedException {
    SynhBlockDemo sbd = new SynhBlockDemo();
    sbd.copy();
  }

  private void copy() throws InterruptedException {
    long start = System.currentTimeMillis();

    Thread t1 = new Thread(new RunnerA());
    Thread t2 = new Thread(new RunnerB());
    t1.start();
    t2.start();

    t1.join();
    t2.join();

    long end = System.currentTimeMillis();
    System.out.println("total time: " + (end - start));
  }

  private void copyArrayA()  {
    synchronized (lockA) {
      for (int i = 0, length = a.length; i < length; i++) {
        intList1.add(a[i]);
        System.out.println(intList1);
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private void copyArrayB() {
    synchronized (lockB) {
      for (int i = 0, length = b.length; i < length; i++) {
        intList2.add(b[i]);
        System.out.println(intList2);
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private class RunnerA implements Runnable {
    @Override
    public void run() {
        copyArrayA();
    }
  }

  private class RunnerB implements Runnable {
    @Override
    public void run() {
        copyArrayB();
    }
  }
}
