package com.lessons.ConcurrentDemo;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static com.lessons.ConcurrentDemo.ColorScheme.*;

public class ConcurrentDemo {


  public static void main(String[] args) {

    Thread st1 = new SimpleThread();
    st1.setPriority(1);
    st1.start();

    Thread st2 = new SimpleThread("Second Thread", BLUE);
    st2.setPriority(10);
    st2.start();

    st2.interrupt();

    System.out.println("_________MAIN THREAD_________");

  }

}

class SimpleThread extends Thread {
  private String name = "Simple thread";
  private String color = GREEN;

  public SimpleThread(String name, String color) {
    this.name = name;
    this.color = color;
  }

  public SimpleThread() {
  }

  @Override
  public void run() {
    currentThread().setName(name);
    System.out.println(color + ":::::::::::" + currentThread().getName() + "::::::::::::");
    Stream.iterate(1, i -> i+=1).limit(10).forEach(i -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        System.out.println(RED + "WARN:::::::" + currentThread().getName() + " WAS INTERRUPTED:::::::");
        return;
      }
      System.out.println(color + i);
    });

    System.out.println(color + "::::::END OF " + currentThread().getName() + "THREAD::::::");

  }
}