package cn.liuht.im.client;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 14:19
 */
public class Client3 {

    public static void main(String[] args) {
        NettyClient.connect(NettyClient.MAX_RETRY);
    }
}
