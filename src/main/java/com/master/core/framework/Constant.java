package com.master.core.framework;


import org.springframework.scheduling.support.SimpleTriggerContext;

import java.awt.image.FilteredImageSource;

/**
 * 定义系统顶级常量配置
 *
 * @author zhangkui
 */
public class Constant {

    /**
     * 返回结果
     **/
    public final static String RESULT_ROOT_KEY = "result";
    /**
     * 数据正常返回码
     */
    public final static String SUCCESS_CODE = "true";
    /**
     * 用户数据正常返回码
     */
    public final static String SUCCESS_REASON = "成功";
    /**
     * 数据正常返回状态码
     */
    public final static String CODE = "20000";
    /**
     * 数据异常返回码
     */
    public final static String DEFAULT_ERROR_CODE = "404";
    /**
     * 数据库访问异常返回码
     */
    public final static String DA_ERROR_CODE = "222222";
    /**
     * 数据状态
     */
    public static final String DATA_STATE_NORMAL = "2"; //正常
    public static final String DATA_STATE_HIS = "3"; //历史
    public static final String DATA_STATE_DELETE = "4"; //删除
}
