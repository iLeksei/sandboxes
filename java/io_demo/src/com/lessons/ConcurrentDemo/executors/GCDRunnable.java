package com.lessons.ConcurrentDemo.executors;

import java.util.Random;

import static com.lessons.ConcurrentDemo.ColorScheme.*;

public class GCDRunnable extends Random implements Runnable {

  public GCDRunnable() {
  }

  @Override
  public void run() {
    String threadName = Thread.currentThread().getName();

    System.out.println(BLUE + ":::::::" + " Starting " + threadName + " ::::::::");
    for (int i = 0, length = 10000000; i < length; i++) {
      int a = nextInt();
      int b = nextInt();

      if (i % 10000 == 0) { // просто для уменьшения кол-ва сообщений
        if (!Thread.interrupted()) {
          int gcd = computeGCD(a, b);
          if (gcd > 5) { // просто для уменьшения кол-ва сообщений
            System.out.println(GREEN + "Running " + threadName + " , max GCD of " + a + " and " + b + " is " + gcd);
          }
        } else {
          System.out.println(RED + "thread " + threadName + " was interrupted");
          return;
        }

      }
    }

    System.out.println(BLUE + ":::::::" + " Living thread: " + threadName + " ::::::::");
  }

  /**
   * @return - max common divider number
   */
  private int computeGCD(int num1, int num2) {
    if (num2 == 0) {
      return num1;
    } else {
      return computeGCD(num2, num1 % num2);
    }
  }
}
