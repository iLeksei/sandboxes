package com.lessons.ZipDemo;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.zip.*;

public class ZipDemo {
  public void showZipDemo() throws IOException {
    String newFilePath = Paths.get(".").normalize().toAbsolutePath().toString() + "/src/com/lessons/ZipDemo/demo.txt";
    String zipPath = Paths.get(".").normalize().toAbsolutePath().toString() + "/src/com/lessons/ZipDemo/archive.zip";
    boolean newFile = new File(newFilePath).createNewFile();

      FileWriter fileWriter = new FileWriter(new File(newFilePath));
      fileWriter.append("hello zip demo!");
      fileWriter.close();

    try (FileOutputStream fos = new FileOutputStream(new File(newFilePath))) {
      String hi = "hello, world!";
      byte[] hiBytes = hi.getBytes();
      fos.write(hiBytes);
    }

    try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath))) {
      ZipEntry entry = new ZipEntry("demo.txt");
      zos.putNextEntry(entry);
      FileInputStream fis = new FileInputStream(new File(newFilePath));
      byte[] buff = new byte[fis.available()];
      fis.read(buff);
      zos.write(buff);
      zos.closeEntry();
    }


  }
}
