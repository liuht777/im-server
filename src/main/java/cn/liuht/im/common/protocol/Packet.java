package cn.liuht.im.common.protocol;

import lombok.Data;

/**
 * 通信数据包
 *
 * @author liuht
 * 2019/6/12 15:38
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     *
     * @return 指令
     */
    public abstract Byte getCommand();
}
