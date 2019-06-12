package cn.liuht.im;

import cn.liuht.im.server.NettyServerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author liuht
 * 2019/6/6 14:09
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private NettyServerListener nettyServerListener;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        nettyServerListener.start();
    }
}
