package com.wsy.learn.jvm.chatroom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

/**
 * 聊天室:服务端
 */
public class Server {


    //选择器
    private Selector selector;
    //注册ServerSocketChannel后的选择键
    private SelectionKey serverKey;
    //标识是否运行
    private boolean isRun;
    //时间格式化器
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 构造函数
     *
     * @param port 服务端监控的端口号
     */
    public Server(int port) {
        isRun = true;
        init(port);
    }

    /**
     * 初始化选择器和服务器套接字
     *
     * @param port 服务端监控的端口号
     */
    private void init(int port) {
        try {
            //获得选择器实例
            selector = Selector.open();
            //获得服务器套接字实例
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            //绑定端口号
            serverChannel.socket().bind(new InetSocketAddress(port));
            //设置为非阻塞
            serverChannel.configureBlocking(false);
            //将ServerSocketChannel注册到选择器，指定其行为为"等待接受连接"
            serverKey = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            printInfo("server starting...");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        try {
            //轮询选择器选择键
            while (isRun) {
                //选择一组已准备进行IO操作的通道的key，等于1时表示有这样的key
                int n = selector.select();
                if (n > 0) {
                    //从选择器上获取已选择的key的集合并进行迭代
                    Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        //若此key的通道是等待接受新的套接字连接
                        if (key.isAcceptable()) {
                            //记住一定要remove这个key，否则之后的新连接将被阻塞无法连接服务器
                            iter.remove();
                            //获取key对应的通道
                            ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                            //接受新的连接返回和客户端对等的套接字通道
                            SocketChannel channel = serverChannel.accept();
                            if (channel == null) {
                                continue;
                            }
                            //设置为非阻塞
                            channel.configureBlocking(false);
                            //将这个套接字通道注册到选择器，指定其行为为"读"
                            channel.register(selector, SelectionKey.OP_READ);
                            System.out.println("accept..." + channel.getRemoteAddress().toString());
                        }
                        //若此key的通道的行为是"读"
                        if (key.isReadable()) {
                            readMsg(key);
                        }
                        //若次key的通道的行为是"写"
                        if (key.isWritable()) {
                            writeMsg(key);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从key对应的套接字通道上读数据
     *
     * @param key 选择键
     * @throws IOException
     */
    private void readMsg(SelectionKey key) throws IOException {
        //获取此key对应的套接字通道
        SocketChannel channel = (SocketChannel) key.channel();
        //创建一个大小为1024k的缓存区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuffer sb = new StringBuffer();
        //将通道的数据读到缓存区
        int count = channel.read(buffer);
        if (count > 0) {
            //翻转缓存区(将缓存区由写进数据模式变成读出数据模式)
            buffer.flip();
            //将缓存区的数据转成String
            sb.append(new String(buffer.array(), 0, count));
        }
        String str = sb.toString();

        String uname = channel.getRemoteAddress().toString();
        String msg = str;
        printInfo("(" + uname + ")说：" + msg);
        String dateTime = sdf.format(new Date());
        String smsg = uname + " " + dateTime + "\n  " + msg + "\n";
        Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
        while (iter.hasNext()) {
            SelectionKey selKey = iter.next();
            if (selKey != serverKey) {
                selKey.attach(smsg);
                selKey.interestOps(selKey.interestOps() | SelectionKey.OP_WRITE);
            }
        }

    }

    /**
     * 写数据到key对应的套接字通道
     *
     * @param key
     * @throws IOException
     */
    private void writeMsg(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        Object obj = key.attachment();
        //这里必要要将key的附加数据设置为空，否则会有问题
        key.attach("");
        //将数据写到通道
        channel.write(ByteBuffer.wrap(obj.toString().getBytes()));
        //重设此key兴趣
        key.interestOps(SelectionKey.OP_READ);
    }

    private void printInfo(String str) {
        System.out.println("[" + sdf.format(new Date()) + "] -> " + str);
    }

    public static void main(String[] args) {
        Server server = new Server(19999);
        server.run();
    }

}
