package com.master.business.controller.user;


import com.master.business.domain.model.user.Power;
import com.master.business.service.user.IPowerService;
import com.master.core.annotation.SessionParam;
import com.master.core.framework.db.PagerBean;
import com.master.core.framework.web.AuthUser;
import com.master.core.framework.web.BaseController;
import com.master.core.framework.web.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



  
/**
  * t_power 权限控制器
  * @version v1.0.0
  * @since jdk1.8+
  * @author 123
  */
@Controller
@RequestMapping("/power")
public class PowerController extends BaseController {
	private Logger log = LoggerFactory.getLogger(PowerController.class);

	@Autowired
	private IPowerService powerService;	
	
	/**
	 * 获取权限
	 * @param powerid 创建时间
     * @param authuser 操作用户信息
	 * @param model
	 */
	@RequestMapping(value="/get", method={ RequestMethod.GET, RequestMethod.POST })
	public void get(@SessionParam("authuser") AuthUser authuser, String powerid, Model model){
		log.debug("###开始查询权限， powerid=[{}].", powerid);
		try {
			//These code is generated by machine, if you want to modify the code, suggest you to remove this line
			ResultData data = powerService.get(powerid, authuser);
			collect(data, model);
		} catch (Exception e) {
			collect(e, model);
		}
	}
	
	/**
	 * 查询权限
	 * @param power 权限对象
	 * @param authuser 操作用户信息
	 * @param model
	 */
	@RequestMapping(value="/find", method={ RequestMethod.POST })
	public void find(@SessionParam("authuser") AuthUser authuser, Power power, Model model){
		log.debug("###开始根据条件{}查询权限", power);
		try {
			//These code is generated by machine, if you want to modify the code, suggest you to remove this line
			ResultData data = powerService.find(power, authuser);
			collect(data, model);
		} catch (Exception e) {
			collect(e, model);
		}
	}
	
	
	/**
	 * 分页查询权限
	 * @param authuser 操作用户信息
	 * @param page 分页参数
	 * @param model
	 */
	@RequestMapping(value="/find-by-page", method={ RequestMethod.POST })
	public void findPager(@SessionParam("authuser") AuthUser authuser,  Power power,PagerBean page,Model model){
		log.debug("###开始根据条件{}分页查询权限", power);
		ResultData resData = ResultData.init();
		try {
			//These code is generated by machine, if you want to modify the code, suggest you to remove this line
			resData = powerService.findPager(power,page,authuser);
			collect(resData, model);
		} catch (Exception e) {
			collect(e, model);
		}
	}
	
	/**
	 * 添加权限
	 * @param power 权限对象
	 * @param authuser 操作用户信息
	 * @param model
	 */
	@RequestMapping(value="/add", method={ RequestMethod.POST })
	public void add(@SessionParam("authuser") AuthUser authuser, Power power, Model model){
		log.debug("###开始查找添加权限， [{}]", power);
		try {
			//These code is generated by machine, if you want to modify the code, suggest you to remove this line
			ResultData data = powerService.add(power, authuser);
			collect(data, model);
		} catch (Exception e) {
			collect(e, model);
		}
	}
	
	/**
	 * 更新权限
	 * @param power 权限对象
	 * @param authuser 操作用户信息
	 * @param model
	 */
	@RequestMapping(value="/update", method={ RequestMethod.POST })
	public void update(@SessionParam("authuser") AuthUser authuser, Power power, Model model){
		log.debug("###开始修改权限， [{}]", power);
		try {
			//These code is generated by machine, if you want to modify the code, suggest you to remove this line
			ResultData data = powerService.update(power, authuser);
			collect(data, model);
		} catch (Exception e) {
			collect(e, model);
		}
	}
	
	/**
	 * 删除权限
	 * @param powerid 创建时间
	 * @param authuser 操作用户信息
	 * @param model
	 */
	@RequestMapping(value="/delete", method={ RequestMethod.POST })
	public void delete(@SessionParam("authuser") AuthUser authuser, String powerid,  Model model){
		log.debug("###开始删除权限对象，powerid={}.", powerid);
		try {
			//These code is generated by machine, if you want to modify the code, suggest you to remove this line
			ResultData data = powerService.delete(powerid, authuser);
			collect(data, model);
		} catch (Exception e) {
			collect(e, model);
		}
	}
}