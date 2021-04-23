package com.lessons.ConcurrentDemo;

public class SynhDemo {
  private static long prev = 0;
  private static long current = 1;

  public static synchronized void caclNext() {
    long next = prev + current;
    prev = current;
    current = next;
    System.out.println(Thread.currentThread().getName() + " : " + current);
  }

  public static void main(String[] args) {

    Thread t1 = new Thread(new FibRunner());
    t1.start();

    Thread t2 = new Thread(new FibRunner());
    t2.start();


  }

  private static class FibRunner implements Runnable {
    @Override
    public void run() {
      for (int i = 0, length = 20; i < length; i++) {
        caclNext();
        //          Thread.sleep(500);
      }
    }
  }

}
