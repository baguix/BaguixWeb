/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/10/1.
 */
package com.baguix.web.service.util;

import com.baguix.web.model.db.core.TPermission;
import com.baguix.web.model.db.core.TRole;
import com.baguix.web.model.db.core.TUser;
import com.baguix.web.model.db.core.TUserRole;
import com.baguix.web.model.page.core.User;
import com.baguix.web.service.core.UserServiceI;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <b>权限认证</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
@Service
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserServiceI userService;

    /**
     * <b>登录认证</b><br>
     *
     * @param authenticationToken 认证令牌
     * @return 认证信息（AuthenticationInfo）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String uid = token.getUsername();
        TUser user = userService.findByUid(uid);
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getUid(), user.getPassword(), getName());
        }
        return null;
    }

    /**
     * <b>权限认证</b><br>
     *
     * @param principalCollection 角色表
     * @return 认证信息（AuthenticationInfo）
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String uid = (String) principalCollection.fromRealm(getName()).iterator().next();
        TUser user = userService.findByUid(uid);
        if (user != null) {
            SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
            Set<TUserRole> userRoles = user.getUserRoles();
            Set<String> roles = new HashSet<>();
            Collection<String> permissions = new ArrayList<String>();

            for (TUserRole ur : userRoles) {
                TRole role = ur.getRole();
                List<TPermission> rolePermissions = role.getPermissions();
                for (TPermission permission : rolePermissions) {
                    permissions.add(permission.getName());
                }
                roles.add(role.getName());
            }
            authInfo.setRoles(roles);
            authInfo.addStringPermissions(permissions);
            return authInfo;
        }
        return null;
    }
}
