/* *
 * @author zhangkui
 * @since 2015-03-02
 */
package com.master.core.framework.web.exception;

import com.master.core.framework.Constant;
import com.master.core.framework.exception.AbsBusiException;
import com.master.core.framework.exception.SystemRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统异常处理
 * 	整合分类系统所发生的异常，对异常进行整体处理，分发。
 * @author Rofly
 */
public class SystemExceptionResolver implements HandlerExceptionResolver{
	private Logger log = LoggerFactory.getLogger(SystemExceptionResolver.class);

	/** 业务异常处理转向页面 */
	private String errorView;

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		//处理会根据异常的不同类型来进行分发，如果是系统的业务发生了异常，则相应到对应的页面为 error.jsp

		String url = request.getRequestURI();
		log.debug("#系统请求[{}]异常，开始处理异常：", url, ex);
		String suffix = "";
		int lastIndex = url.lastIndexOf(".");
		if(-1 != lastIndex){
			suffix = url.substring(lastIndex);
		}

		AbsBusiException e = null;
		if(ex instanceof AbsBusiException){ //系统业务异常，添加对应的响应代码及原因
			e = (AbsBusiException)ex;
		}else{ //系统其他异常,系统异常不需要相应对应的数据
			log.info("System exception transformation, exception={}", ex.getMessage());
			e = new SystemRuntimeException();
		}
		Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();
		Map<String, String> data = new HashMap<String, String>();
		data.put("retCode", e.getRetCode());
		data.put("reason", e.getReason());
		data.put("time", ""+System.currentTimeMillis());
		result.put(Constant.RESULT_ROOT_KEY, data);

		log.info("System Exception Resolver, url={}, result={}", url, result);

		if(null != errorView){
			return new ModelAndView(complie(errorView, suffix), result);
		}else{
			return new ModelAndView(complie("error/500", suffix), result);
		}
	}
	/**
	 * 根据请求后缀重新修改响应数据格式
	 * @param view 待编译的视图名
	 * @param suffix 原请求后缀
	 * @return 带后缀的视图名
	 */
	private String complie(String view, String suffix) {
		if(".xml".equalsIgnoreCase(suffix)){
			return view+".xml";
		}else if(".json".equalsIgnoreCase(suffix)){
			return view+".json";
		}else{
			return view;
		}
	}

	public void setErrorView(String errorView) {
		this.errorView = errorView;
	}
}
