package com.wsy.learn.jvm.chatroom;

public class Room {

    public static void main(String[] args) {
        try {
            Client client1 = new Client();
            Client client2 = new Client();

            client1.sendMsg("hello world");
            client2.sendMsg("hello socket");

            Thread.sleep(1000);

            System.out.println("client1 收到：" + client1.receiveMsg());
            System.out.println("client2 收到：" + client2.receiveMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
