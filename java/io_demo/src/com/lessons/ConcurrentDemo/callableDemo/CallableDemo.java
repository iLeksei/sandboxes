package com.lessons.ConcurrentDemo.callableDemo;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class Timer {
  public Instant start;
  public Instant end;
  
  public double getProcessingTime() {
    return Duration.between(start, end).toMillis() / 1000.0;
  }
}

public class CallableDemo {

  private static List<Future<Double>> durations = new ArrayList<>();

  public static void main(String[] args) throws Exception {
//    useCallable();
  }



  public static void useCallable() {
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < 10; i++) {
      durations.add(executorService.submit(() -> {
        Timer timer = new Timer();
        Random rand = new Random();
        timer.start = Instant.now();
        Thread.sleep(rand.nextInt(4000));
        timer.end = Instant.now();
        return timer.getProcessingTime();
      }));
    }
    executorService.shutdown();

    durations.parallelStream()
            .map(doubleFuture -> {
              try {
                return doubleFuture.get();
              } catch (InterruptedException e) {
                e.printStackTrace();
              } catch (ExecutionException e) {
                e.printStackTrace();
              }
              return 0;
            })
            .forEach(num -> {
              System.out.println("total duration: " + num);
            });
  }

}
