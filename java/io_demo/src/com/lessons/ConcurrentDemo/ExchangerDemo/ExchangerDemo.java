package com.lessons.ConcurrentDemo.ExchangerDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangerDemo {
  public static void main(String[] args) {
    Exchanger<String> exc = new Exchanger<>();
    new Thread(new Predicator(exc)).start();
    new Thread(new Consumer(exc)).start();
  }

  private static class Predicator implements Runnable {
    Exchanger<String> exc;
    static List<String> list = new ArrayList<>();

    static {
      list.add("hello");
      list.add("from");
      list.add("predicator");
    }

    public Predicator(Exchanger<String> exc) {
      this.exc = exc;
    }

    @Override
    public void run() {
      for (int i = 0, length = list.size(); i < length; i++) {
        try {
          Thread.sleep(500);
          exc.exchange(list.get(i));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private static class Consumer implements Runnable {
    private final Exchanger<String> exc;
    private String str;

    public Consumer(Exchanger<String> exc) {
      this.exc = exc;
    }

    @Override
    public void run() {
      try {
        for (int i = 0; i < 3; i++) {
          Thread.sleep(200);
          str = exc.exchange(new String());
        }
        System.out.println("got string: " + str);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
