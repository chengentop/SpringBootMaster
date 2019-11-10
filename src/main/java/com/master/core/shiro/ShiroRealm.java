package com.master.core.shiro;

import com.master.business.domain.iface.user.IPowerDao;
import com.master.business.domain.iface.user.IRoleDao;
import com.master.business.domain.iface.user.IUserDao;
import com.master.business.domain.model.user.Power;
import com.master.business.domain.model.user.Role;
import com.master.business.domain.model.user.User;
import com.master.business.service.user.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IPowerDao powerDao;


    /**
     * 验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名密码 第一种方式
//        String username = (String) authenticationToken.getPrincipal();
//        String password = new String((char[]) authenticationToken.getCredentials());

        // 第二种方式
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());

        //从数据库查询用户信息
        User user = new User();
        user.setUsername(username);
        List<User> users = userDao.find(user);

        //可以在这里直接对用户名校验,或者调用 CredentialsMatcher 校验
        //调用 CredentialsMatcher 校验 还需要创建一个类 继承CredentialsMatcher  如果在上面校验了,这个就不需要了
        if (users.get(0) == null) {
            throw new UnknownAccountException("用户名或密码错误!");
        }
        if (!password.equals(users.get(0).getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误!");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(users.get(0), users.get(0).getPassword(), getName());
        return info;
    }

    /**
     * 授权用户权限
     * 授权的方法是在碰到<shiro:hasPermission name=''></shiro:hasPermission>标签的时候调用的
     * 它会去检测shiro框架中的权限(这里的permissions)是否包含有该标签的name值,如果有,里面的内容显示
     * 如果没有,里面的内容不予显示(这就完成了对于权限的认证.)
     * <p>
     * shiro的权限授权是通过继承AuthorizingRealm抽象类，重载doGetAuthorizationInfo();
     * 当访问到页面的时候，链接配置了相应的权限或者shiro标签才会执行此方法否则不会执行
     * 所以如果只是简单的身份认证没有权限的控制的话，那么这个方法可以不进行实现，直接返回null即可。
     * <p>
     * 在这个方法中主要是使用类：SimpleAuthorizationInfo 进行角色的添加和权限的添加。
     * authorizationInfo.addRole(role.getRole()); authorizationInfo.addStringPermission(p.getPermission());
     * <p>
     * 当然也可以添加set集合：roles是从数据库查询的当前用户的角色，stringPermissions是从数据库查询的当前用户对应的权限
     * authorizationInfo.setRoles(roles); authorizationInfo.setStringPermissions(stringPermissions);
     * <p>
     * 就是说如果在shiro配置文件中添加了filterChainDefinitionMap.put("/add", "perms[权限添加]");
     * 就说明访问/add这个链接必须要有“权限添加”这个权限才可以访问
     * <p>
     * 如果在shiro配置文件中添加了filterChainDefinitionMap.put("/add", "roles[100002]，perms[权限添加]");
     * 就说明访问/add这个链接必须要有 "权限添加" 这个权限和具有 "100002" 这个角色才可以访问
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        List<Role> roles = roleDao.selectRole(user.getUserid());
        roles.forEach(role -> {
            authorizationInfo.addRole(role.getRolename());
            List<Power> list = powerDao.selectPower(role.getRoleid());
            list.forEach(power -> {
                authorizationInfo.addStringPermission(power.getPermissioncode());
            });
        });
        return authorizationInfo;
    }

    /**
     * 重写方法,清除当前用户的的 授权缓存
     *
     * @param principals
     */
    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的 认证缓存
     *
     * @param principals
     */
    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    /**
     * 重写方法，清除缓存
     *
     * @param principals
     */
    @Override
    protected void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 自定义方法：清除所有 授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 自定义方法：清除所有 认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 自定义方法：清除所有的  认证缓存  和 授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
