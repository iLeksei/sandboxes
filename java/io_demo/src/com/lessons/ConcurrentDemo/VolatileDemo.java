package com.lessons.ConcurrentDemo;

public class VolatileDemo {

  private static volatile int counter;

  public static void main(String[] args) {

    new SimpleWriter().start();

    new SimpleReader().start();

  }

  private static class SimpleWriter extends Thread {
    public SimpleWriter() {
    }

    @Override
    public void run() {
      int localCounter = counter;
      for (byte i = 0, length = 10; i < length; i++) {
        System.out.println(ColorScheme.GREEN + "Writer increments counter by 1, counter is " + (localCounter + 1));
        counter = ++localCounter;
        try {
          Thread.sleep(700);
        } catch (InterruptedException e) {
          System.out.println("Writer was interrupted!!!");
        }
      }
    }
  }

  private static class SimpleReader extends Thread {
    public SimpleReader() {
    }

    @Override
    public void run() {
      int localCounter = counter;
        while (localCounter < 10) {
          if (localCounter != counter) {
            System.out.println(ColorScheme.BLUE + "SimpleReader reads counter and counter is: "  + localCounter);
            localCounter = counter;
          }
        }
    }
  }

}

