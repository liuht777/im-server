package cn.liuht.im.client;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 14:19
 */
public class Client1 {

    public static void main(String[] args) {
        new NettyClient("127.0.0.1", 8084);
    }
}
