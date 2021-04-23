package com.lessons.NioDemo;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class NioDemo {

  public static void main(String[] args) throws IOException {
    new NioDemo().showChannelDemo();
  }

  public void showNioDemo() throws IOException {
    String pathName = Paths.get(".").normalize().toAbsolutePath().resolve("src/com/lessons/NioDemo").toString();
    String pathFrom = Paths.get(pathName).resolve("from").toString();
    String pathTo = Paths.get(pathName).resolve("to").toString();

    Files.copy(
            Paths.get(pathFrom).resolve( "fromToTo.txt"),
            Paths.get(pathTo).resolve("copied.txt"), StandardCopyOption.REPLACE_EXISTING);

    List<String> lines = Files.readAllLines(Paths.get(pathTo).resolve("copied.txt"));
    lines.forEach(System.out::println);

    System.out.println("::::::::::: BufferReader At Next Lines::::::::::");
    Charset charset = StandardCharsets.UTF_8;
    try(BufferedReader bf = Files.newBufferedReader(Paths.get(pathTo).resolve("copied.txt"), charset)) {
      bf.lines().forEach(System.out::println);
    }

    System.out.println("::::::::::: BufferWriter At Next Lines::::::::::");
    try(BufferedWriter bw = Files.newBufferedWriter(Paths.get(pathTo).resolve("copied.txt"), charset, StandardOpenOption.APPEND)) {
      String sourceText = "\ni have written in here!!! \nDo you listen me!?";
      bw.append(sourceText);
    }
    System.out.println("::::::::::: Buffer InputStream At Next Lines::::::::::");
    try(InputStream in = Files.newInputStream(Paths.get(pathTo).resolve("copied.txt"))) {
      BufferedReader buff = new BufferedReader(new InputStreamReader(in), 1024);
      buff.lines().forEach(System.out::println);
    }

  }

  public void showChannelDemo() throws IOException {
    String path = Paths.get(".").normalize().toAbsolutePath().resolve("src/com/lessons/NioDemo/readAndWrite").toString();
    RandomAccessFile file = new RandomAccessFile(new File(path, "channelDemo.txt"),"rw");
    FileChannel channel = file.getChannel();
    ByteBuffer buff = ByteBuffer.allocate(100);

//    channel.write(ByteBuffer.wrap("1: hello from channel".getBytes()));
    int fileBytes = channel.read(buff);           // 1.read all bytes into buffer
    while (fileBytes > 0) {                       // 2. use while is has bytes
      buff.flip();                                // 3. put cursor at the beginning

      while (buff.hasRemaining()) {               // 4. read bytes while they are
        System.out.print((char)buff.get());
      }
      buff.clear();                               // 5. clear buffer
      fileBytes = channel.read(buff);             // 6. push next bytes to buffer
    }

    channel.close();                              // 7. close channel
  }
}
