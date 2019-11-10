package com.master.business.controller.Advice;

import com.master.core.framework.web.BaseController;
import com.master.core.framework.web.ResultData;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages ="com.master.business.controller")
public class MyControllerAdvice extends BaseController  {


    /**
     * 权限不足报错拦截
     *
     * @return
     * @throws Exception
     */
    @ExceptionHandler(UnauthorizedException.class)
    public void unauthorizedExceptionHandler(Model model){
        ResultData data  = ResultData.init();

        data.setData("msg","\"UnauthorizedException-------> 权限不足\"");
        collect(data,model);
        System.out.println("UnauthorizedException-------> 权限不足");
    }


    /**
     * 未登录报错拦截
     * 在请求需要权限的接口,而连登录都还没登录的时候,会报此错
     *
     * @return
     * @throws Exception
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public void unauthenticatedException(Model model) {
        ResultData data  = ResultData.init();

        data.setData("msg","\"UnauthenticatedException-------> 未登录\"");
        collect(data,model);
        System.out.println("UnauthenticatedException-------> 未登录");
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public void incorrectCredentialsException(Model model){
        ResultData data  = ResultData.init();
        data.setData("msg","IncorrectCredentialsException-------> 密码错误");
        collect(data,model);
        System.out.println("IncorrectCredentialsException-------> 密码错误");
    }
}
