package com.master.business.domain.iface.user;

import com.master.business.domain.model.user.User;
import com.master.core.framework.db.IBaseDao;
import com.master.core.framework.exception.DataAccessException;
import org.springframework.stereotype.Repository;

    
/**
  * UserDao 数据访问对象
  * @version v1.0.0
  * @since jdk1.8+
  * @author 123
  */
@Repository
public interface IUserDao extends IBaseDao<User>{
	/**
	 * 获取
	 * @param userid 
	 * @return User 对象
	 * @throws DataAccessException
	 */
	public User get(String userid) throws DataAccessException;
}