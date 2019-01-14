package com.wsy.learn.jvm.chatroom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    private static final String HOST = "127.0.0.1";
    //端口号要和server保持一致
    private static final int PORT = 19999;
    private SocketChannel sc;

    private static Object lock = new Object();

    public Client() throws IOException {
        sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress(HOST, PORT));
    }

    public void sendMsg(String msg) {
        try {
            while (!sc.finishConnect()) {
            }
            sc.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMsg() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.clear();
        StringBuffer sb = new StringBuffer();
        int count = 0;
        String msg = null;
        try {
            while ((count = sc.read(buffer)) > 0) {
                sb.append(new String(buffer.array(), 0, count));
            }
            if (sb.length() > 0) {
                msg = sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
