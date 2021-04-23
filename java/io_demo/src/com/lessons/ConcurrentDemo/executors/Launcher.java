package com.lessons.ConcurrentDemo.executors;

import java.util.concurrent.*;

import static com.lessons.ConcurrentDemo.ColorScheme.RED;

public class Launcher {
  private static final int POOL_SIZE = 2;

  public static void main(String[] args) throws InterruptedException {
    System.out.println(RED + "start main thread!!!!");
    GCDRunnable r = new GCDRunnable();
//    runInOneThread(r, true);
    runWithExecutors(r, false);
//    Thread.sleep(50);
    System.out.println(RED + "stop main thread!!!!");
  }

  private static void runInOneThread(GCDRunnable r, boolean isDaemon) throws InterruptedException {
    Thread th = new Thread(r);
    if (isDaemon){
      th.setDaemon(true); // т.е. обслуживается основным потоком, есть он завершил процесс, то и этот поток, тоже завершит
    }
    th.start();
//    Thread.sleep(50);
//    th.interrupt();
  }
  public static void runWithExecutors(GCDRunnable r, boolean isDaemon) throws InterruptedException {
    // для управления потоком демоном
    ThreadFactory tf = rn -> {
      Thread thread = new Thread(rn);
      if (isDaemon) {
        thread.setDaemon(true);
      }
      return thread;
    };

    ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE, tf);
    for (int i = 0; i < 5; i++) {
      executorService.execute(r);
    }
    executorService.shutdown();

//    executorService.awaitTermination(15, TimeUnit.SECONDS); // if threads do not close after 15sec then terminate them
  }
}
