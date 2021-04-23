package com.lessons.BufferDemo;

import java.io.*;
import java.nio.file.Paths;

public class BufferDemo {

  public  void showBufferDemo() throws IOException {
    String filePath = Paths.get(".").normalize().toAbsolutePath().toString() + "/src/com/lessons/BufferDemo/BuffDemo.txt";
    int[] buff = new int[1024];
    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)),1024);

    String c;
    while ((c = bufferedReader.readLine()) != null) {
      System.out.println(c);
    }

    bufferedReader.close();

  }

}
