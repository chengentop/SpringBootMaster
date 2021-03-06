package com.master.business.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.master.business.domain.iface.user.IRoleDao;
import com.master.business.domain.model.user.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.master.core.framework.web.BaseService;
import com.master.core.framework.db.PagerBean;
import com.master.core.framework.db.QueryBean;
import com.master.core.framework.web.AuthUser;
import com.master.core.framework.web.ResultData;

import com.master.business.service.user.IUserRoleService;
import com.master.business.domain.iface.user.IUserRoleDao;
import com.master.business.domain.model.user.UserRole;

  
/**
  * IUserRoleService 服务接口实现
  * @version v1.0.0
  * @since jdk1.8+
  * @author 123
  */
@Service
public class UserRoleServiceImpl extends BaseService implements IUserRoleService{
	private Logger log = LoggerFactory.getLogger(UserRoleServiceImpl.class);
	
	@Autowired
	private IUserRoleDao userroleDao;

	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public ResultData get(String userid, AuthUser authuser) throws Exception {
		log.debug("###[服务] 开始查询 userid=[{}]. ", userid);
		//These code is generated by machine, if you want to modify the code, suggest you to remove this line
		ResultData resData = ResultData.init();
		UserRole userrole = userroleDao.get(userid);
		resData.setData("userrole", userrole);
		log.info("###[服务] 完成查询 ResultData=[{}]", resData);
		return resData;
	}
	
	@Override
	public ResultData find(UserRole userrole, AuthUser authuser) throws Exception {
		log.debug("###[服务] 开始根据条件{}查询", userrole);
		//These code is generated by machine, if you want to modify the code, suggest you to remove this line
		ResultData resData = ResultData.init();
		List<UserRole> userroles = userroleDao.find(userrole);
		resData.setData("userroles", userroles);
		log.info("###[服务] 完成查询 ResultData=[{}]", resData);
		return resData;
	}
	
	@Override
	public ResultData findPager(UserRole userrole,PagerBean page, AuthUser authuser) throws Exception{
		log.debug("###[服务] 开始根据条件{}分页查询", userrole);
		//These code is generated by machine, if you want to modify the code, suggest you to remove this line
		ResultData resData = ResultData.init();
		
		page = new PagerBean(page.getPageSize() == null ? 20 : page.getPageSize(), page.getCurrentPage());
		page.setOrderKey("intime");
		page.setAscend("desc");

		Map<String, Object> _data = new HashMap<String, Object>();
		
		QueryBean<UserRole> query = new QueryBean<UserRole>(page, userrole);
		List<UserRole> userroles = userroleDao.findByPage(query);
		
		_data.put("userroles", getFieldValueList(userroles));
		_data.put("page", page.parseMap());
		resData.setData(_data);
		
		log.info("###[服务] 完成分页查询 resData=[{}]", resData);
		return resData;
	}
	
	@Override
	public ResultData add(UserRole userrole, AuthUser authuser) throws Exception {
		log.debug("###[服务] 开始添加新, 操作用户:{}", authuser.getUsername());
		//These code is generated by machine, if you want to modify the code, suggest you to remove this line
		ResultData resData = ResultData.init();
		int inserted = userroleDao.insert(userrole);
		resData.setData("inserted", inserted);
		log.info("###[服务] 完成添加新，ResultData=[{}]", resData);
		return resData;
	}
	
	@Override
	public ResultData update(UserRole userrole, AuthUser authuser) throws Exception {
		log.debug("###[服务] 开始修改, 操作用户:{}", authuser.getUsername());
		//These code is generated by machine, if you want to modify the code, suggest you to remove this line
		ResultData resData = ResultData.init();
		int updated = userroleDao.update(userrole);
		resData.setData("updated", updated);
		log.info("###[服务] 完成修改，ResultData=[{}]", resData);
		return resData;
	}
	
	@Override
	public ResultData delete(String userid, AuthUser authuser) throws Exception {
		log.debug("###[服务] 开始删除, 操作用户:{}", authuser.getUsername());
		//These code is generated by machine, if you want to modify the code, suggest you to remove this line
		ResultData resData = ResultData.init();
		UserRole userrole = new UserRole();
		userrole.setUserid(userid);
		int deleted = userroleDao.delete(userrole);
		resData.setData("deleted", deleted);
		log.info("###[服务] 完成删除，ResultData=[{}]", resData);
		return resData;
	}

	@Override
	public List<Role> find(UserRole userRole) {
		log.debug("###[服务] 开始根据条件{}查询用户权限", userRole);
		//These code is generated by machine, if you want to modify the code, suggest you to remove this line
		ResultData resData = ResultData.init();
		List<UserRole> userroles = userroleDao.find(userRole);
		List<Role> datas = new ArrayList<>();
		for (UserRole userrole : userroles) {
			datas.add(roleDao.get(userrole.getRoleid()));
		}
		log.info("###[服务] 完成查询 查询用户权限");
		return datas;
	}
}
