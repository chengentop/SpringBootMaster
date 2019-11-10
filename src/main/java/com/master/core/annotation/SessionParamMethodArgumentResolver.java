package com.master.core.annotation;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SessionParamMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 让方法和参数，两种target通过
		if (parameter.hasParameterAnnotation(SessionParam.class)) {
			return true;
		} else if (parameter.getMethodAnnotation(SessionParam.class) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		String annoVal = null;

		if (parameter.getParameterAnnotation(SessionParam.class) != null) {
			annoVal = parameter.getParameterAnnotation(SessionParam.class).value();
		} else if (parameter.getMethodAnnotation(SessionParam.class) != null) {
			annoVal = parameter.getMethodAnnotation(SessionParam.class) != null ? StringUtils.defaultString(parameter
					.getMethodAnnotation(SessionParam.class).value()) : StringUtils.EMPTY;
		}
		if (webRequest.getAttribute(annoVal, RequestAttributes.SCOPE_SESSION) != null) {
			return webRequest.getAttribute(annoVal, RequestAttributes.SCOPE_SESSION);
		} else
			return null;
	}
}
