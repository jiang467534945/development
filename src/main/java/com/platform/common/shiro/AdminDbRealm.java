package com.platform.common.shiro;

import com.platform.upms.model.UpmsUser;
import com.platform.upms.service.UpmsUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GaoXiang
 * @version 1.0
 * @since 2016/10/28.
 */
public class AdminDbRealm extends AuthorizingRealm {

    @Autowired
    private UpmsUserService upmsUserService;


    /**
     * 认证回调函数,登录时调用。
     * @param authenticationToken Token
     * @return 认证
     * @throws AuthenticationException 认证失败
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UpmsUser upmsUser = null;
        try {
            upmsUser =upmsUserService.selectByUserName(token.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (upmsUser == null)//没有此用户
            throw new UnknownAccountException();

        if(upmsUser.getStatus() < 1)//账户被锁定
            throw new LockedAccountException();

        ShiroUser shiroUser = new ShiroUser(String.valueOf(upmsUser.getId()), upmsUser.getUserName(),upmsUser);

        //返回认证信息由父类AuthenticatingRealm进行认证
        return new SimpleAuthenticationInfo(shiroUser, upmsUser.getPassword(), getName());
    }


    /**
     * 授权查询回调函数。
     * @param principalCollection 会话信息
     * @return 认证结果
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        ShiroUser shiroUser= (ShiroUser) principalCollection.getPrimaryPrincipal();

        //创建权限容器
        List<String> powers = new ArrayList<>();

        //绑定、判断用户所有角色和权限
        try {
            upmsUserService.findPermissionByUserId(Integer.valueOf(shiroUser.getId())).forEach(permissionId -> {
                if(StringUtils.isNoneEmpty(permissionId)) powers.add(permissionId);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        //返回权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(powers);
        return info;
    }

}
