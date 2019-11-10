package com.master;

import com.master.business.domain.model.user.Power;
import com.master.business.domain.model.user.RolePower;
import com.master.business.domain.model.user.User;
import com.master.business.service.mail.IMailService;
import com.master.business.service.user.IPowerService;
import com.master.business.service.user.IRolePowerService;
import com.master.business.service.user.IUserService;
import com.master.core.framework.web.AuthUser;
import com.master.core.util.Strings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.PipedWriter;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    @Autowired
    private IMailService mailService;

    @Test
    public void test1() throws Exception {
        mailService.sendHtmlMail("", "测试  test", "");

    }
}
