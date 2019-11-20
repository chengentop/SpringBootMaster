package com.master.business.domain.iface.user;

import com.master.business.domain.model.user.Role;
import com.master.core.framework.db.IBaseDao;
import com.master.core.framework.exception.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
  * RoleDao 数据访问对象
  * @version v1.0.0
  * @since jdk1.8+
  * @author 123
  */
@Repository
public interface IRoleDao extends IBaseDao<Role>{
	/**
	 * 根据权限id获取权限
	 * @param roleid 
	 * @return Role 对象
	 * @throws DataAccessException
	 */
	public Role get(String roleid) throws DataAccessException;

	/**
	 * 根据用户id获取权限
	 * @param userid
	 * @return
	 * @throws DataAccessException
	 */
	public List<Role> selectRole(String userid) throws DataAccessException;

	public Role getRole(String userid)throws DataAccessException;
}