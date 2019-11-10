package com.master.business.service.user;

import com.master.business.domain.model.user.RolePower;
import com.master.core.framework.db.PagerBean;
import com.master.core.framework.web.AuthUser;
import com.master.core.framework.web.ResultData;


/**
  *  服务接口
  * @version v1.0.0
  * @since jdk1.8+
  * @author 123
  */
public interface IRolePowerService {
	
	/**
	 * 获取
	 *
	 * @param id 
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData get(String id, AuthUser authuser) throws Exception;
	
	/**
	 * 查询
	 *
	 * @param rolepower 对象
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData find(RolePower rolepower, AuthUser authuser) throws Exception;
	
	/**
	 * 分页查询
	 *
	 * @param rolepower 对象
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData findPager(RolePower rolepower,PagerBean page, AuthUser authuser) throws Exception;
	
	/**
	 * 添加
	 *
	 * @param rolepower 对象
	 * @param authuser 操作用户
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData add(RolePower rolepower, AuthUser authuser) throws Exception;
	
	/**
	 * 更新
	 *
	 * @param rolepower 对象
	 * @param authuser 操作用户
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData update(RolePower rolepower, AuthUser authuser) throws Exception;
	
	/**
	 * 删除
	 *
	 * @param id 
	 * @param authuser 操作用户
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData delete(String id, AuthUser authuser) throws Exception;
}