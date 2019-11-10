package com.master.core.framework.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.core.framework.web.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserSecurityInterceptor extends HandlerInterceptorAdapter {
	private Logger log = LoggerFactory.getLogger(UserSecurityInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {		
		 AuthUser authuser = (AuthUser) request.getSession().getAttribute("authuser");
		 log.debug("###[拦截器] session过期时间:" + request.getSession().getMaxInactiveInterval());
	     if (authuser != null) {
	    	 return  super.preHandle(request, response, handler);
	     }
        
/*        String origin = request.getHeader("Origin");
        if(origin == null) {
            origin = request.getHeader("Referer");
        }
        response.setHeader("Access-Control-Allow-Origin", origin);            // 允许指定域访问跨域资源
        response.setHeader("Access-Control-Allow-Credentials", "true");       // 允许客户端携带跨域cookie，此时origin值不能为“*”，只能为指定单一域名
        
        if(RequestMethod.OPTIONS.toString().equals(request.getMethod())) {
            String allowMethod = request.getHeader("Access-Control-Request-Method");
            String allowHeaders = request.getHeader("Access-Control-Request-Headers");
            response.setHeader("Access-Control-Max-Age", "86400");            // 浏览器缓存预检请求结果时间,单位:秒
            response.setHeader("Access-Control-Allow-Methods", allowMethod);  // 允许浏览器在预检请求成功之后发送的实际请求方法名
            response.setHeader("Access-Control-Allow-Headers", allowHeaders); // 允许浏览器发送的请求消息头
        }*/
	    response.setHeader("Access-Control-Allow-Origin", "*");            // 允许指定域访问跨域资源
	    response.setStatus(403);
        return false;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		
	}
}
