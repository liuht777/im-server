package cn.liuht.im.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 会话
 *
 * @author liuht
 * 2019/6/13 13:54
 */
@Data
@AllArgsConstructor
public class Session {

    /**
     * 用户唯一性标识
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;
}
