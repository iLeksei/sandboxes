package com.lessons;

import com.lessons.BufferDemo.BufferDemo;
import com.lessons.FileDemo.FileDemo;
import com.lessons.NioDemo.NioDemo;
import com.lessons.SerializationDemo.SerializationDemo;
import com.lessons.WriterDemo.WriteDemo;
import com.lessons.ZipDemo.ZipDemo;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println(System.getenv("user.nickname"));
        // WriterDemo
//        WriteDemo writeDemo = new WriteDemo();
//        writeDemo.writeDemo();

        //BufferDemo
//        BufferDemo bufferDemo = new BufferDemo();
//        bufferDemo.showBufferDemo();

        //SerializationDemo
//        SerializationDemo serializationDemo = new SerializationDemo();
//        serializationDemo.showSerialize();

        //FileDemo
//        FileDemo fileDemo = new FileDemo();
//        fileDemo.showFileDemo();

        //ZipDemo
//        new ZipDemo().showZipDemo();

        //NioDemo
//        new NioDemo().showNioDemo();

//        ChannelDemo
        new NioDemo().showChannelDemo();
//        System.out.println("current directory: " + System.getProperty("user.dir"));
    }
}
