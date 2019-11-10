package com.master.business.domain.model.user;

import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.List;

/**
  * t_role 数据持久化对象
  * @version v1.0.0
  * @since jdk1.8+
  * @author 123
  */
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;    
	public static final String ROLEID="roleid";  //
	
	private String roleid;  //
	private String rolename;  //

	private List<Power> powers;
	
	public String getRoleid(){
		return this.roleid;
	}
	public void setRoleid(String roleid){
		this.roleid = roleid;
	}
	public String getRolename(){
		return this.rolename;
	}
	public void setRolename(String rolename){
		this.rolename = rolename;
	}

	public List<Power> getPowers() {
		return powers;
	}

	public void setPowers(List<Power> powers) {
		this.powers = powers;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("com.master: ");
		if(null != roleid)sb.append("roleid=").append(roleid).append(",");
		if(null != rolename)sb.append("rolename=").append(rolename).append(",");
		return sb.toString();
	}
}