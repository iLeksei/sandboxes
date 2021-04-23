package com.lessons.ConcurrentDemo.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.lessons.ConcurrentDemo.ColorScheme.*;

public class SynchronizedBuffer {

  private static final Lock monitor = new ReentrantLock(true); //true отключает голодание потока

  private static final Condition canRead = monitor.newCondition();
  private static final Condition canWrite = monitor.newCondition();

  private static int buff = 0;
  private static boolean isEmpty = true;

  public static void main(String[] args) {
    new Thread(SynchronizedBuffer::blockingWrite).start();
    new Thread(SynchronizedBuffer::blockingRead).start();
  }


  public static void blockingWrite()  {
    for (int i = 0; i < 10; i++) {
      monitor.lock();
      try {
        while (!isEmpty) {
          System.out.println(GREEN + "Wait until buffer will be empty");
          canWrite.await(5, TimeUnit.SECONDS);
        }
        buff++;
        isEmpty = false;
        System.out.println(GREEN + "Write: " + buff);
        canRead.signal();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        monitor.unlock();
      }
    }
  }

  public static void blockingRead() {
    for (int i = 0; i < 10; i++) {
      monitor.lock();
      try {
        while (isEmpty) {
          System.out.println(BLUE + "Wait until buffer will be fullish");
          canRead.await(5, TimeUnit.SECONDS);
        }
        int readValue = buff;
        isEmpty = true;
        System.out.println(BLUE + "Read from buffer: " + readValue);
        canWrite.signal();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        monitor.unlock();
      }
    }
  }
}
