package com.master.core.framework.web;

import java.util.HashMap;
import java.util.Map;

import com.master.core.framework.exception.BpmsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.master.core.framework.Constant;

public class BaseController {
	private Logger log = LoggerFactory.getLogger(BaseController.class);
	public static final String ERROR = "error/500";

	public String redirect(String path) {
		return "redirect" + ":" + (path.startsWith("/") ? "" : "/") + path;
	}

	/**
	 * 处理正常数据
	 * 
	 * @param result
	 * @param model
	 */
	protected void collect(ResultData result, Model model) {
		Map<String, Object> rd = new HashMap<String, Object>();
		rd.put("code",Constant.CODE);
		rd.put("success", true);
		rd.put("data", result.getData());
		model.addAttribute(Constant.RESULT_ROOT_KEY, rd);
	}

	/**
	 * 处理异常
	 * 
	 * @param e
	 * @param model
	 */
	protected void collect(Exception e, Model model) {
		Map<String, Object> rd = new HashMap<String, Object>();
		rd.put("success", false);
		Map<String, Object> error = new HashMap<String, Object>();
		if (e instanceof BpmsException) {
			BpmsException re = (BpmsException) e;
			error.put("code", re.getCode());
			error.put("reason", re.getReason());
		} else {
			error.put("code", Constant.DEFAULT_ERROR_CODE);
			error.put("reason", "未知错误");
		}
		rd.put("error", error);
		log.error("errorCode={}，reason={}", error.get("code"), error.get("reason"), e);
		model.addAttribute(Constant.RESULT_ROOT_KEY, rd);
	}
}
