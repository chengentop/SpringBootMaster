package com.master.business.domain.model.user;

import java.io.Serializable;

/**
  * t_user 数据持久化对象
  * @version v1.0.0
  * @since jdk1.8+
  * @author 123
  */
public class User implements Serializable{
	private static final long serialVersionUID = 1L;    
	public static final String USERID="userid";  //

	private String userid;  //
	private String username;  //
	private String password;  //
	private String salt;  //
	private String fullname;  //



	public String getFullname(){
		return this.fullname;
	}
	public void setFullname(String fullname){
		this.fullname = fullname;
	}
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getSalt(){
		return this.salt;
	}
	public void setSalt(String salt){
		this.salt = salt;
	}
	public String getUserid(){
		return this.userid;
	}
	public void setUserid(String userid){
		this.userid = userid;
	}
	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username){
		this.username = username;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("com.master: ");
		if(null != fullname)sb.append("fullname=").append(fullname).append(",");
		if(null != password)sb.append("password=").append(password).append(",");
		if(null != salt)sb.append("salt=").append(salt).append(",");
		if(null != userid)sb.append("userid=").append(userid).append(",");
		if(null != username)sb.append("username=").append(username).append(",");
		return sb.toString();
	}
}