package com.master.business.domain.iface.user;

import com.master.business.domain.model.user.RolePower;
import com.master.core.framework.db.IBaseDao;
import com.master.core.framework.exception.DataAccessException;
import org.springframework.stereotype.Repository;

    
/**
  * RolePowerDao 数据访问对象
  * @version v1.0.0
  * @since jdk1.8+
  * @author 123
  */
@Repository
public interface IRolePowerDao extends IBaseDao<RolePower>{
	/**
	 * 获取
	 * @param id 
	 * @return RolePower 对象
	 * @throws DataAccessException
	 */
	public RolePower get(String id) throws DataAccessException;
}