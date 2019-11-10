package com.master.business.controller.auth;

import com.master.business.domain.model.user.User;
import com.master.business.service.user.IUserService;
import com.master.core.framework.web.BaseController;
import com.master.core.framework.web.ResultData;
import com.master.core.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @commonts: 用户登录控制类
 * @vision 1.0.0
 */

@Controller
@RequestMapping("/auth")
public class AuthConteoller extends BaseController {

    private Logger log = LoggerFactory.getLogger(AuthConteoller.class);

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public void login(String username, String password, Model model) {
        ResultData data = ResultData.init();
        //对密码进行加密
        password = MD5Util.MD5Pwd(username, password);

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            // 登录操作
            subject.login(usernamePasswordToken);
            User user = (User) subject.getPrincipal();
            data.setData("登录成功", user);
            model.addAttribute("user", user);
            collect(data, model);
        } catch (Exception e) {
            collect(e, model);
        }

    }
}
