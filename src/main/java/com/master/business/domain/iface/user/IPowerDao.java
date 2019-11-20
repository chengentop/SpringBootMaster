package com.master.business.domain.iface.user;

import com.master.business.domain.model.user.Power;
import com.master.core.framework.db.IBaseDao;
import com.master.core.framework.exception.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


/**
 * PowerDao 数据访问对象
 *
 * @author 123
 * @version v1.0.0
 * @since jdk1.8+
 */
@Repository
public interface IPowerDao extends IBaseDao<Power> {
    /**
     * 获取权限
     *
     * @param powerid 创建时间
     * @return Power 权限对象
     * @throws DataAccessException
     */
    public Power get(String powerid) throws DataAccessException;

    /**
     * 根据角色id 获取权限
     * @param roleid
     * @return
     * @throws DataAccessException
     */
    public List<Power> selectPower(String roleid) throws DataAccessException;

    Set<String> getMenuCode(String roleid)throws DataAccessException;

    Set<String> getPermissioncode(String roleid)throws DataAccessException;
}