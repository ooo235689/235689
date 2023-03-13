package com.pengqianhuai.hospitaldata.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pengqianhuai.hospitaldata.entity.SysUser;
import com.pengqianhuai.hospitaldata.mapper.SysUserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("执行了授权 doGetAuthorizationInfo");

        SimpleAuthorizationInfo simpInfo = new SimpleAuthorizationInfo();

        //获取当前用户的对象
        Subject subject= SecurityUtils.getSubject();

        //获取用户信息
        SysUser sysUser=(SysUser)subject.getPrincipal();

        return simpInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证 doGetAuthorizationInfo");
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken userToken=(UsernamePasswordToken)authenticationToken;//获取登录的信息
        //获取用户名 密码  数据库取
        System.out.println(userToken.getUsername());

        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("loginname",userToken.getUsername());

        SysUser query = sysUserMapper.selectOne(queryWrapper);
        System.out.println(query);
        if(query==null){//没有这个用户
            return null;
        }
        Session session=subject.getSession();//获取用户的session
        session.setAttribute("loginuser",query);

        if(!userToken.getUsername().equals(query.getLoginname())){//判断登录的用户名密码 匹配数据库是否正确
            return null;//抛出异常
        }

        //密码认证，shiro做
        return new SimpleAuthenticationInfo(query,query.getPwd(),"");
    }
}
