package com.master.business.domain.model.user;

import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.List;

/**
 * t_role 数据持久化对象
 *
 * @author 123
 * @version v1.0.0
 * @since jdk1.8+
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String ROLEID = "roleid";  //

    private String roleid;  //
    private String rolename;  //

    private String[] permissions;

    private List<User> users;

    private List<Power> menus;

    public String getRoleid() {
        return this.roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return this.rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Power> getMenus() {
        return menus;
    }

    public void setMenus(List<Power> menus) {
        this.menus = menus;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("com.master: ");
        if (null != roleid) sb.append("roleid=").append(roleid).append(",");
        if (null != rolename) sb.append("rolename=").append(rolename).append(",");
        return sb.toString();
    }
}