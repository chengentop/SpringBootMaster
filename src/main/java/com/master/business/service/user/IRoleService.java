package com.master.business.service.user;

import com.master.business.domain.model.user.Role;
import com.master.core.framework.db.PagerBean;
import com.master.core.framework.web.AuthUser;
import com.master.core.framework.web.ResultData;


/**
  *  服务接口
  * @version v1.0.0
  * @since jdk1.8+
  * @author 123
  */
public interface IRoleService {
	
	/**
	 * 获取
	 *
	 * @param roleid 
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData get(String roleid, AuthUser authuser) throws Exception;
	
	/**
	 * 查询
	 *
	 * @param role 对象
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData find(Role role, AuthUser authuser) throws Exception;
	
	/**
	 * 分页查询
	 *
	 * @param role 对象
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData findPager(Role role,PagerBean page, AuthUser authuser) throws Exception;
	
	/**
	 * 添加
	 *
	 * @param role 对象
	 * @param authuser 操作用户
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData add(Role role, AuthUser authuser) throws Exception;
	
	/**
	 * 更新
	 *
	 * @param role 对象
	 * @param authuser 操作用户
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData update(Role role, AuthUser authuser) throws Exception;
	
	/**
	 * 删除
	 *
	 * @param roleid 
	 * @param authuser 操作用户
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData delete(String roleid, AuthUser authuser) throws Exception;
}