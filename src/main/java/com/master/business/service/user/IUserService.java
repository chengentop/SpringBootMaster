package com.master.business.service.user;

import com.master.business.domain.model.user.Role;
import com.master.business.domain.model.user.User;
import com.master.core.framework.db.PagerBean;
import com.master.core.framework.web.AuthUser;
import com.master.core.framework.web.ResultData;

import java.util.List;


/**
  *  服务接口
  * @version v1.0.0
  * @since jdk1.8+
  * @author 123
  */
public interface IUserService {
	
	/**
	 * 获取
	 *
	 * @param userid 
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData get(String userid, AuthUser authuser) throws Exception;
	
	/**
	 * 查询
	 *
	 * @param user 对象
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData find(User user, AuthUser authuser) throws Exception;
	
	/**
	 * 分页查询
	 *
	 * @param user 对象
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData findPager(User user,PagerBean page, AuthUser authuser) throws Exception;
	
	/**
	 * 添加
	 *
	 * @param user 对象
	 * @param authuser 操作用户
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData add(User user, AuthUser authuser) throws Exception;
	
	/**
	 * 更新
	 *
	 * @param user 对象
	 * @param authuser 操作用户
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData update(User user, AuthUser authuser) throws Exception;
	
	/**
	 * 删除
	 *
	 * @param userid 
	 * @param authuser 操作用户
	 * @return ResultData
	 * @throws Exception
	 */
	public ResultData delete(String userid, AuthUser authuser) throws Exception;

}