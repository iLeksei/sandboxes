package com.lessons.ConcurrentDemo.semaphore;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static com.lessons.ConcurrentDemo.ColorScheme.*;

public class WaitingQueue {

  public static void main(String[] args) throws InterruptedException {
    int operators = 5;
    int customers = 100;

    SemaphoreServiceDesk serviceDesk = new SemaphoreServiceDesk(operators, customers);
    ExecutorService executorService = Executors.newCachedThreadPool();
    IntStream.range(0, customers)
            .forEach(client -> executorService.submit(() -> {
              serviceDesk.connect();
              try {
                Thread.sleep(100);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              System.out.println(GREEN + "Number of connected customers: " + serviceDesk.getConnectedCustomers());
              System.out.println(YELLOW + "Number of clients in queue: " + serviceDesk.getCustomersWaiting());

            }));
    executorService.shutdown();
    executorService.awaitTermination(30000, TimeUnit.SECONDS);
  }

  private static class SemaphoreServiceDesk {
    private AtomicInteger connectedCustomers = new AtomicInteger();
    private AtomicInteger customersQueued;

    private Semaphore semaphore;

    SemaphoreServiceDesk(int operatorsNum, int customerNumbers) {
      this.semaphore = new Semaphore(operatorsNum);
      this.customersQueued = new AtomicInteger(customerNumbers);
    }

    public int getConnectedCustomers() {
      return connectedCustomers.get();
    }

    public int getCustomersWaiting() {
      return customersQueued.get();
    }

    public void connect() {
      Random random = new Random();
      try {
        semaphore.acquire();
        connectedCustomers.incrementAndGet();
        customersQueued.decrementAndGet();
        Thread.sleep(random.nextInt(3000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        disconnect();
      }
    }

    private void disconnect() {
      semaphore.release();
      connectedCustomers.decrementAndGet();
    }
  }

}
