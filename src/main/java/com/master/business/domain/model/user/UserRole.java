package com.master.business.domain.model.user;

import java.io.Serializable;

/**
  * t_user_role 数据持久化对象
  * @version v1.0.0
  * @since jdk1.8+
  * @author 123
  */
public class UserRole implements Serializable{
	private static final long serialVersionUID = 1L;    
	public static final String USERID="userid";  //
	
	private String userid;  //
	private String roleid;  //
	
	public String getUserid(){
		return this.userid;
	}
	public void setUserid(String userid){
		this.userid = userid;
	}
	public String getRoleid(){
		return this.roleid;
	}
	public void setRoleid(String roleid){
		this.roleid = roleid;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("com.master: ");
		if(null != userid)sb.append("userid=").append(userid).append(",");
		if(null != roleid)sb.append("roleid=").append(roleid).append(",");
		return sb.toString();
	}
}