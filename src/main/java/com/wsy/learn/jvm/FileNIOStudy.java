package com.wsy.learn.jvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileNIOStudy {

    public void read() {
        try {
            FileInputStream fin = new FileInputStream("/Users/wangsiyuan1/Desktop/test.txt");
            FileChannel fc = fin.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            fc.read(buffer);
            buffer.flip();

            StringBuffer s = new StringBuffer();
            while (buffer.remaining() > 0) {
                byte b = buffer.get();
                s.append((char) b);
                //System.out.print(((char) b));
            }
            System.out.print(s);
            fc.close();
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void write() {
        try {
            File file = new File("/Users/wangsiyuan1/Desktop/test.txt");
            FileOutputStream outputStream = new FileOutputStream(file);
            FileChannel channel = outputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            String string = "java nio";
            buffer.put(string.getBytes());
            buffer.flip();     //此处必须要调用buffer的flip方法
            //channel.write(buffer);
            channel.write(buffer);
            channel.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        FileNIOStudy study = new FileNIOStudy();
        study.write();
        study.read();
    }
}
