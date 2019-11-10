package com.master.business.service.mail;

import com.master.core.framework.web.ResultData;

/**
 * 邮件 服务接口
 * @version v1.0.0
 * @since jdk1.8+
 * @author 123
 */
public interface IMailService {


    /**
     * 发送纯文本邮件
     * @param toAddr 发送给谁
     * @param title 标题
     * @param content 内容
     */
    public ResultData sendTextMail(String recipients, String content, String title) throws Exception;

    /**
     * 发送 html 邮件
     * @param toAddr 发送给谁
     * @param title 标题
     * @param content 内容（HTML）
     */
    public ResultData sendHtmlMail(String toAddr, String title, String content) throws Exception;

    /**
     *  发送待附件的邮件
     * @param toAddr 发送给谁
     * @param title 标题
     * @param content 内容
     * @param filePath 附件地址
     */
    public ResultData sendAttachmentsMail(String toAddr, String title, String content, String filePath) throws Exception;

    /**
     *  发送文本中有静态资源（图片）的邮件
     * @param toAddr 发送给谁
     * @param title 标题
     * @param content 内容
     * @param rscPath 资源路径
     * @param rscId 资源id (可能有多个图片)
     */
    public ResultData sendInlineResourceMail(String toAddr, String title, String content, String rscPath, String rscId) throws Exception;

}
