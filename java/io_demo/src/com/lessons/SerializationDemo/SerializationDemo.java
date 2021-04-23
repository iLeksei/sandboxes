package com.lessons.SerializationDemo;

import java.io.*;
import java.nio.file.Paths;

public class SerializationDemo {

  public void showSerialize() throws IOException, ClassNotFoundException {
    User user = new User("Bob", "123456");
    String filePath = Paths.get(".")
            .normalize().toAbsolutePath().toString() + "/src/com/lessons/SerializationDemo/User.txt";

    boolean userFile = new File(filePath).createNewFile();

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath, true) )) {
      oos.writeObject(user);
    }

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
     User readableUSer = (User) ois.readObject();
     System.out.println(readableUSer.getUsername());
    }

  }

}
