package com.master.business.domain.iface.user;

import com.master.business.domain.model.user.UserRole;
import com.master.core.framework.db.IBaseDao;
import com.master.core.framework.exception.DataAccessException;
import org.springframework.stereotype.Repository;

    
/**
  * UserRoleDao 数据访问对象
  * @version v1.0.0
  * @since jdk1.8+
  * @author 123
  */
@Repository
public interface IUserRoleDao extends IBaseDao<UserRole>{
	/**
	 * 获取
	 * @param userid 
	 * @return UserRole 对象
	 * @throws DataAccessException
	 */
	public UserRole get(String userid) throws DataAccessException;
}