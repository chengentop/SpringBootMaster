package com.master.business.service.user;

import com.master.business.domain.model.user.Power;
import com.master.core.framework.db.PagerBean;
import com.master.core.framework.web.AuthUser;
import com.master.core.framework.web.ResultData;

import java.util.List;


/**
  * 权限 服务接口
  * @version v1.0.0
  * @since jdk1.8+
  * @author 123
  */
public interface IPowerService {
	
	/**
	 * 获取权限
	 *
	 * @param powerid 创建时间
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData get(String powerid, AuthUser authuser) throws Exception;
	
	/**
	 * 查询权限
	 *
	 * @param power 权限对象
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData find(Power power, AuthUser authuser) throws Exception;
	
	/**
	 * 分页查询权限
	 *
	 * @param power 权限对象
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData findPager(Power power,PagerBean page, AuthUser authuser) throws Exception;
	
	/**
	 * 添加权限
	 *
	 * @param power 权限对象
	 * @param authuser 操作用户
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData add(Power power, AuthUser authuser) throws Exception;
	
	/**
	 * 更新权限
	 *
	 * @param power 权限对象
	 * @param authuser 操作用户
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData update(Power power, AuthUser authuser) throws Exception;
	
	/**
	 * 删除权限
	 *
	 * @param powerid 创建时间
	 * @param authuser 操作用户
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData delete(String powerid, AuthUser authuser) throws Exception;
}