package com.lessons.StreamDemo;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
  public static void main(String[] args) throws IOException {
    int[] arr = {1,3,7,57,3,1,3,5,7,456,54,6546,534,234,12,3,35,64,57,8,7546,432,12};
    String path = Paths.get(".").normalize().toAbsolutePath().resolve("src/com/lessons/StreamDemo").toString();
    new File(path, "stream.txt");

    String str = "hello buddy!\nhow are you!?\ni'm gonna to use parallel stream!";
    try (FileWriter fos = new FileWriter(new File(Paths.get(path).resolve("stream.txt").toString()))) {
      fos.write(str);
    }

    Files.lines(Paths.get(path).resolve("stream.txt"))
      .parallel()
      .map(s -> s.split(""))
      .flatMap(Arrays::stream)
      .filter(sym -> !"".equals(sym))
      .sorted(String::compareTo)
      .distinct()
      .forEach(System.out::print);
  }
}
