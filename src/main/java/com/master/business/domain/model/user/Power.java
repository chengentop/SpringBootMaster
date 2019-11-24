package com.master.business.domain.model.user;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * t_power 数据持久化对象
 *
 * @author 123
 * @version v1.0.0
 * @since jdk1.8+
 */
public class Power implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String POWERID = "powerid";  //权限id

    private String powerid;  //权限id
    private String permissioncode;  //权限的代码/通配符,对应代码中@RequiresPermissions 的value
    private String permissionname;  //本权限的中文释义
    private int requiredpermission;  //是否本菜单必选权限, 1.必选 2非必选 通常是"列表"权限是必选
    private String menucode;  //归属菜单,前端判断并展示菜单使用
    private String menuname;  //菜单的中文释义
    private Timestamp intime;  //创建时间


    List<Power> permissions ;

    public Timestamp getIntime() {
        return this.intime;
    }

    public void setIntime(Timestamp intime) {
        this.intime = intime;
    }

    public String getMenucode() {
        return this.menucode;
    }

    public void setMenucode(String menucode) {
        this.menucode = menucode;
    }

    public String getMenuname() {
        return this.menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getPermissioncode() {
        return this.permissioncode;
    }

    public void setPermissioncode(String permissioncode) {
        this.permissioncode = permissioncode;
    }

    public String getPermissionname() {
        return this.permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    public String getPowerid() {
        return this.powerid;
    }

    public void setPowerid(String powerid) {
        this.powerid = powerid;
    }

    public int getRequiredpermission() {
        return this.requiredpermission;
    }

    public void setRequiredpermission(int requiredpermission) {
        this.requiredpermission = requiredpermission;
    }

    public List<Power> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Power> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("com.master: ");
        if (null != intime) sb.append("intime=").append(intime).append(",");
        if (null != menucode) sb.append("menucode=").append(menucode).append(",");
        if (null != menuname) sb.append("menuname=").append(menuname).append(",");
        if (null != permissioncode) sb.append("permissioncode=").append(permissioncode).append(",");
        if (null != permissionname) sb.append("permissionname=").append(permissionname).append(",");
        if (null != powerid) sb.append("powerid=").append(powerid).append(",");
        sb.append("requiredpermission=").append(requiredpermission).append(",");
        return sb.toString();
    }
}