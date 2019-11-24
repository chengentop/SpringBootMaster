package com.master.business.domain.model.user;

import java.io.Serializable;
import java.sql.Timestamp;

/**
  * t_role_power 数据持久化对象
  * @version v1.0.0
  * @since jdk1.8+
  * @author 123
  */
public class RolePower implements Serializable{
	private static final long serialVersionUID = 1L;    
	public static final String ID="id";  //
	

	private String powerid;  //权限id
	private String roleid;  //角色id
	private Integer state;  //状态:1. 有效 2. 无效
	private Timestamp intime;  //创建时间
	private Timestamp modtime;  //修改时间
	public Timestamp getIntime(){
		return this.intime;
	}
	public void setIntime(Timestamp intime){
		this.intime = intime;
	}
	public Timestamp getModtime(){
		return this.modtime;
	}
	public void setModtime(Timestamp modtime){
		this.modtime = modtime;
	}
	public String getPowerid(){
		return this.powerid;
	}
	public void setPowerid(String powerid){
		this.powerid = powerid;
	}
	public String getRoleid(){
		return this.roleid;
	}
	public void setRoleid(String roleid){
		this.roleid = roleid;
	}
	public Integer getState(){
		return this.state;
	}
	public void setState(Integer state){
		this.state = state;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("com.master: ");
		if(null != intime)sb.append("intime=").append(intime).append(",");
		if(null != modtime)sb.append("modtime=").append(modtime).append(",");
		if(null != powerid)sb.append("powerid=").append(powerid).append(",");
		if(null != roleid)sb.append("roleid=").append(roleid).append(",");
		if(null != state)sb.append("state=").append(state).append(",");
		return sb.toString();
	}
}