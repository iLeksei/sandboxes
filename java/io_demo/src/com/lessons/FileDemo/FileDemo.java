package com.lessons.FileDemo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;

import static java.lang.Integer.*;

public class FileDemo {

  public void showFileDemo() throws IOException {
    String dirPath = Paths.get(".").normalize().toAbsolutePath().normalize().toString();
    new File(dirPath + "/src/com/lessons/FileDemo/demo").mkdir();

//    System.out.println(Paths.get("../lessons/BufferDemo").normalize().toAbsolutePath().toString());
    String path = Paths.get(".").normalize().toAbsolutePath().normalize().toString() + "/src/com/lessons/FileDemo";
    Iterator<Path> iterator = Paths.get(path)
            .iterator();

    File[] files = new File(path).listFiles();
    Arrays.stream(files).forEach(file -> {
      if (file.isDirectory()) {
        if (file.getName().equals("demo")) {
          System.out.println("this is demo dir");
        } else if (file.getName().equals("2") || file.getName().equals("4")) {
          String s = file.getAbsolutePath() + "/damo.txt";
          try {
            new File(s).createNewFile();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
      System.out.println(file.toString());
    });

//    while (iterator.hasNext()) {
//      Path next = iterator.next();
//      System.out.println(next.toString());
//    }
  }


}
