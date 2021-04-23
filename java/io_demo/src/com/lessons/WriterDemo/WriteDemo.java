package com.lessons.WriterDemo;

import javafx.scene.shape.Path;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.ArrayList;

public class WriteDemo {

  public void writeDemo() throws IOException {
    String filePath = Paths.get(".").normalize().toAbsolutePath().toString() + "/src/com/lessons/WriterDemo/writerDemo.txt";
    System.out.println("filePath:" + filePath);
    File file = new File(filePath);
    file.createNewFile();

    User user = new User("Bob", "123456");
    ArrayList<String> todos = new ArrayList<>();
    todos.add("buy food");
    todos.add("haircut");

    Writer writer = new FileWriter(new File(filePath));
    writer.write("hello, world!");
    writer.close();

    FileOutputStream fos = new FileOutputStream(new File(filePath), true);
    byte [] buffer = "hello, from output stream".getBytes();
    fos.write(buffer);
    fos.flush();
    buffer = "\nafter flush".getBytes();
    fos.write(buffer);
    fos.close();

    FileInputStream fin = new FileInputStream(new File(filePath));
    int i = -1;
    String str = "";
    while ((i = fin.read()) != -1) {
      str += (char)i;
//      System.out.println((char) i);
    }
    System.out.println(str);
    fin.close();
  }

}
