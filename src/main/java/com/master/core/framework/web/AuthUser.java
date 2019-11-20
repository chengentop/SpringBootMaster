package com.master.core.framework.web;

import java.io.Serializable;

/**
 * 认证用户
 *
 * @author zhangkui
 * @since 2017-03-29
 */
public class AuthUser implements Serializable {
    private static final long serialVersionUID = 1L;

    String userid; //用户id
    String nickname; //用户昵称
    String username; //用户姓名

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
