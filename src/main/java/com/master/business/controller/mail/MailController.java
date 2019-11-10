package com.master.business.controller.mail;

import com.master.business.service.mail.IMailService;
import com.master.core.annotation.SessionParam;
import com.master.core.framework.web.AuthUser;
import com.master.core.framework.web.BaseController;
import com.master.core.framework.web.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mail")
public class MailController extends BaseController {
    private Logger log = LoggerFactory.getLogger(MailController.class);

    @Autowired
    private IMailService mailService;

    /**
     * 发送纯文本邮件
     *
     * @param recipients 发送给谁
     * @param title      标题
     * @param content    内容
     */
    @RequestMapping("/send")
    public void mail(@SessionParam("authUser") AuthUser authUser, String recipients, String content, String title, Model model) {
        log.info("###  开始发送邮件， 收件人=[{}]", recipients);
        try {
            ResultData resultData = mailService.sendTextMail(recipients, content, title);
            collect(resultData, model);
        } catch (Exception e) {
            collect(e, model);
        }
    }
}
