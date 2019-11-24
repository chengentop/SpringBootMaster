package com.master.business.controller.auth;

import com.master.business.domain.model.user.User;
import com.master.business.service.user.IUserService;
import com.master.core.framework.web.AuthUser;
import com.master.core.framework.web.BaseController;
import com.master.core.framework.web.ResultData;
import com.master.core.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


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
    public void login(String username, String password, HttpSession session, Model model) {
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
            AuthUser authuser = new AuthUser();
            authuser.setUserid(user.getUserid());
            authuser.setNickname(user.getUsername());
            authuser.setUsername(user.getUsername());
            session.setAttribute("authuser", authuser);
            session.setMaxInactiveInterval(1800);
            collect(data, model);
        } catch (Exception e) {
            collect(e, model);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void login_out(HttpSession session, Model model) {
        ResultData data = ResultData.init();
        try {
            session.removeAttribute("authuser");
            collect(data, model);
        } catch (Exception e) {
            collect(e, model);
        }
    }
}
