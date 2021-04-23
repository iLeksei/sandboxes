package com.lessons.ConcurrentDemo.waitnotify;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

import static com.lessons.ConcurrentDemo.ColorScheme.*;

public class WaitNotify {
  private static Queue<String> target = new ArrayDeque<>();

  public static void main(String[] args) {
    Message message = new Message();
    new Thread(new Producer(message)).start();
    new Thread(new Consumer(message)).start();

  }

  private static class Producer implements Runnable {
    private final Message message;
    static String[] text = {"hello", "first", "second", "third", "fourth", "fifth", "done!"};

    Producer(Message message) {
      this.message = message;
    }

    private void produce() throws InterruptedException {
      for (int i = 0, length = text.length; i < length; i++) {
        synchronized (message) {
//          target.add(text[i]);
          System.out.println(GREEN + "Produce " + i + " message: " + text[i]);
          message.setMessage(text[i]);
          message.notify();

          if (!"done!".equals(text[i])) message.wait();
        }
        Thread.sleep(400);
      }
    }

    @Override
    public void run() {
      try {
        produce();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private static class Consumer implements Runnable {
    private final Message message;

    Consumer(Message message) {
      this.message = message;
    }

    private void consume() throws InterruptedException {
      while (true) {
        Thread.sleep(400);
        synchronized (message) {
          System.out.println(RED + "Consume message: " + message.getMessage());
          if (!"DONE!".equals(message.getMessage())) {
            message.notify();
            message.wait();
          } else {
            return;
          }
        }
      }
    }

    @Override
    public void run() {
      try {
        consume();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
