package com.lessons.RandomDemo;

import java.util.Random;
import java.util.stream.Stream;

public class RandomDemo {
  public static void main(String[] args) {
    System.out.print(new Random().nextInt(3));
    Stream.generate(Math::random).limit(10).map(i -> i * 10).mapToInt(Double::intValue).forEach(System.out::println);
  }
}
