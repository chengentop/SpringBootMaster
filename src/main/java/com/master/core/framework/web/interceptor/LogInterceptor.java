package com.master.core.framework.web.interceptor;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogInterceptor {	
		
/*	@Autowired
	private IBusinessLogService businessLogService;
	
	@Pointcut("execution(public * com.cpoffice.business.controller.*.*.*(..))")
	public void applog(){
	}
	
    // 定义通知
	@Around(value="applog() && @annotation(businessOption)") 
    public Object interceptorApplogic(ProceedingJoinPoint pj, BusinessOption businessOption) throws Throwable {  
		Object object =	pj.proceed();  
        RequestAttributes requestattributes = RequestContextHolder.getRequestAttributes();  
        AuthUser authuser = (AuthUser) requestattributes.getAttribute("authuser", RequestAttributes.SCOPE_SESSION);
        BusinessLog syslog = new BusinessLog();
        syslog.setSubject(String.format("操作了【%s】模块下【%s】功能",  businessOption.moduleName(), businessOption.menuName()));
        syslog.setOperater(authuser.getUsername()+"【"+ authuser.getNickname() +"】");
        syslog.setOperate(object.toString());
        businessLogService.add(syslog, authuser);
        return object;
    }*/
}
