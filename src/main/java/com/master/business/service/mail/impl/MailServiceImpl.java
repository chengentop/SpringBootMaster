package com.master.business.service.mail.impl;

import com.master.business.service.mail.IMailService;
import com.master.core.framework.web.BaseService;
import com.master.core.framework.web.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.validation.executable.ValidateOnExecution;

/**
 * IMailService 邮件 服务接口实现
 *
 * @author 123
 * @version v1.0.0
 * @since jdk1.8+
 */

@Service
public class MailServiceImpl extends BaseService implements IMailService {

    private final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public ResultData sendTextMail(String recipients, String content, String title) throws Exception {
        log.debug("[###]  开始发送文本邮件，收件人邮箱=[{}]", recipients);
        ResultData data = ResultData.init();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipients);  //收件人邮箱
        message.setFrom("18291710694@163.com");  //发送者邮箱
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
        log.info("[###]  完成发送文本邮件");
        return data;
    }

    @Override
    public ResultData sendHtmlMail(String to, String subject, String content) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        //true表示需要创建一个multipart message
        String contents = "<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("18291710694@163.com");
        helper.setTo("185554710@qq.com");
        helper.setSubject(subject);
        helper.setText(contents, true);
        mailSender.send(message);
        log.info("html邮件发送成功");
        return null;
    }

    @Override
    public ResultData sendAttachmentsMail(String toAddr, String title, String content, String filePath) throws Exception {
        return null;
    }

    @Override
    public ResultData sendInlineResourceMail(String toAddr, String title, String content, String rscPath, String rscId) throws Exception {
        return null;
    }
}
