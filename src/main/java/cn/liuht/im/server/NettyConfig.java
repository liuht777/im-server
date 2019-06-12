package cn.liuht.im.server;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置类
 *
 * @author liuht
 * 2019/6/6 14:21
 */
@Component
@ConfigurationProperties(prefix = "im")
@Data
public class NettyConfig {

    /**
     * 监听端口
     */
    private int port;
}
