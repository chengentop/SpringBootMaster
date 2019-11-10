package com.master.core.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Util{
    /**
     * 用户注册 密码加密
     * @param username
     * @param pwd
     * @return
     */

    public static String MD5Pwd(String username, String pwd) {
        // 加密算法MD5
        // salt盐 username + salt
        // 迭代次数
        String md5Pwd = new SimpleHash("MD5", pwd,
                ByteSource.Util.bytes(username + "salt"), 2).toHex();
        return md5Pwd;
    }


}
