package com.master.core.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Strings {
    private static Logger log = LoggerFactory.getLogger(Strings.class);
    private static Random random = new Random();

    public static boolean isEmail(String email) {
        if (null == email) return false;
        Pattern p = Pattern.compile("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)");
        return p.matcher(email).matches();
    }

    public static boolean isPhone(String phone) {
        if (null == phone) return false;
        Pattern p = Pattern.compile("^1\\d{10}");
        return p.matcher(phone).matches();
    }

    /**
     * 获取任意字符随机串  <b>字母(含大小写)、数字</b>
     */
    public static String produceRandomStringByAllChar(int length) {
        CharSequence allChar = "abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int index = Math.abs(random.nextInt() % 62);
            result.append(allChar.charAt(index));
        }
        return result.toString();
    }

    /**
     * 获取字符随机串 <b>字母(仅大写)、数字</b>
     */
    public static String produceRandomStringByUpperChar(int length) {
        CharSequence allChar = "ABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int index = Math.abs(random.nextInt() % 36);
            result.append(allChar.charAt(index));
        }
        return result.toString();
    }

    /**
     * 获取字符随机串 <b>字母(仅小写)、数字</b>
     */
    public static String produceRandomStringByLowerChar(int length) {
        CharSequence allChar = "abcdefghigklmnopqrstuvwxyz0123456789";
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int index = Math.abs(random.nextInt() % 36);
            result.append(allChar.charAt(index));
        }
        return result.toString();
    }

    /**
     * 获取数字随机串
     */
    public static String produceRandomStringByNumber(int length) {
        CharSequence allChar = "0123456789";
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int index = Math.abs(random.nextInt() % 10);
            result.append(allChar.charAt(index));
        }
        return result.toString();
    }

    public static String readFileContent(String path, String fullFilename) {
        try {
            URL url = Strings.class.getClassLoader().getResource(path + fullFilename);
            File template = null;
            try {
                template = new File(url.toURI());
            } catch (URISyntaxException e) {
                template = new File(url.getPath());
            }
            return FileUtils.readFileToString(template, "UTF-8");
        } catch (IOException e) {
            log.error("邮件模板文件未找到", e);
            return "There is nothing here.";
        }
    }

    /**
     * 获取文件名的后缀
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    public static String produceLeftFillString(String value, int length) {
        String zerostr = "";
        for (int i = 0; i < length - value.length(); i++) {
            zerostr += "0";
        }
        return zerostr + value;
    }
}
