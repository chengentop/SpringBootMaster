package com.master.business.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.master.business.domain.iface.user.IRoleDao;
import com.master.business.domain.model.user.Role;
import com.master.core.util.MD5Util;
import com.master.core.util.Strings;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.master.core.framework.web.BaseService;
import com.master.core.framework.db.PagerBean;
import com.master.core.framework.db.QueryBean;
import com.master.core.framework.web.AuthUser;
import com.master.core.framework.web.ResultData;

import com.master.business.service.user.IUserService;
import com.master.business.domain.iface.user.IUserDao;
import com.master.business.domain.model.user.User;


/**
 * IUserService  服务接口实现
 *
 * @author 123
 * @version v1.0.0
 * @since jdk1.8+
 */
@Service
public class UserServiceImpl extends BaseService implements IUserService {
    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private IUserDao userDao;

    @Override
    public ResultData get(String userid, AuthUser authuser) throws Exception {
        log.debug("###[服务] 开始查询 userid=[{}]. ", userid);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        ResultData resData = ResultData.init();
        User user = userDao.get(userid);
        resData.setData("user", user);
        log.info("###[服务] 完成查询 ResultData=[{}]", resData);
        return resData;
    }

    @Override
    public ResultData find(User user, AuthUser authuser) throws Exception {
        log.debug("###[服务] 开始根据条件{}查询", user);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        ResultData resData = ResultData.init();
        List<User> users = userDao.find(user);
        resData.setData("users", users);
        log.info("###[服务] 完成查询 ResultData=[{}]", resData);
        return resData;
    }

    @Override
    public ResultData findPager(User user, PagerBean page, AuthUser authuser) throws Exception {
        log.debug("###[服务] 开始根据条件{}分页查询", user);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        ResultData resData = ResultData.init();

        page = new PagerBean(20, page.getCurrentPage());
        page.setOrderKey("intime");
        page.setAscend("desc");

        Map<String, Object> _data = new HashMap<String, Object>();

        QueryBean<User> query = new QueryBean<User>(page, user);
        List<User> users = userDao.findByPage(query);

        _data.put("users", getFieldValueList(users));
        _data.put("page", page.parseMap());
        resData.setData(_data);

        log.info("###[服务] 完成分页查询 resData=[{}]", resData);
        return resData;
    }

    @Override
    public ResultData add(User user, AuthUser authuser) throws Exception {
        log.debug("###[服务] 开始添加新, 操作用户:{}", authuser.getUsername());
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        ResultData resData = ResultData.init();
        user.setUserid(Strings.produceRandomStringByAllChar(32));
        user.setPassword(MD5Util.MD5Pwd(user.getUsername(), user.getPassword()));
        System.err.println("加密后的密码：" + user.getPassword());
        int inserted = userDao.insert(user);
        resData.setData("inserted", inserted);
        log.info("###[服务] 完成添加新，ResultData=[{}]", resData);
        return resData;
    }

    @Override
    public ResultData update(User user, AuthUser authuser) throws Exception {
        log.debug("###[服务] 开始修改, 操作用户:{}", authuser.getUsername());
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        ResultData resData = ResultData.init();
        int updated = userDao.update(user);
        resData.setData("updated", updated);
        log.info("###[服务] 完成修改，ResultData=[{}]", resData);
        return resData;
    }

    @Override
    public ResultData delete(String userid, AuthUser authuser) throws Exception {
        log.debug("###[服务] 开始删除, 操作用户:{}", authuser.getUsername());
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        ResultData resData = ResultData.init();
        User user = new User();
        user.setUserid(userid);
        int deleted = userDao.delete(user);
        resData.setData("deleted", deleted);
        log.info("###[服务] 完成删除，ResultData=[{}]", resData);
        return resData;
    }


}
