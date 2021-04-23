package com.lessons.ConcurrentDemo.producerConsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;

import static com.lessons.ConcurrentDemo.ColorScheme.BLUE;
import static com.lessons.ConcurrentDemo.ColorScheme.GREEN;

public class ProducerConsumer {

  private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

  public static void main(String[] args) {
    new Thread(new Producer()).start();
    new Thread(new Consumer()).start();
  }

  private static class Producer implements Runnable {
    String[] messages = {
            "Lorem ipsum dolor sit amet",
            "consectetur adipiscing elit.",
            "Etiam dolor purus, interdum id neque et, eleifend tristique nulla.",
            "Sed accumsan purus at odio malesuada, ultricies finibus lectus egestas.",
            "Praesent sed faucibus tortor.",
            "Nulla imperdiet turpis sem, vel euismod mi pulvinar at.",
            "Donec et turpis in nisi placerat dictum. ",
            "Morbi ac vehicula est. ",
            "Phasellus quis enim gravida, rutrum augue viverra, placerat augue.",
            "Ut ultricies porta viverra. ",
            "Nulla rhoncus est nulla, in gravida lectus venenatis eu.",
            "DONE"
    };

    private void produce() throws InterruptedException {
      Random r = new Random();
      for (int i = 0, length = messages.length; i < length; i++) {
        queue.put(messages[i]);
        System.out.println(GREEN + "Producing: " + messages[i] + ". Queue size is: " + queue.size());
        Thread.sleep(r.nextInt(1000));
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

    private void consume() throws InterruptedException {
      Random r = new Random();

      while (true) {
        String message = queue.take();
        System.out.println(BLUE + "Consuming: " + message + ".Queue size is: " + queue.size());
        if (!"DONE".equals(message)) {
          Thread.sleep(r.nextInt(3000));
        } else {
          return;
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
