package com.tnh.spring.security.oauth2.server.configure;

import com.tnh.spring.security.oauth2.server.domain.TbPermission;
import com.tnh.spring.security.oauth2.server.domain.TbUser;
import com.tnh.spring.security.oauth2.server.service.TbPermissionService;
import com.tnh.spring.security.oauth2.server.service.TbUserService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

//实现security下的UserDetailsService接口
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private TbUserService tbUserService;
    @Autowired
    private TbPermissionService tbPermissionService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //查询该用户信息
        TbUser user = tbUserService.getTbUserByName(s);
        //创建角色权限集合
        List<GrantedAuthority> grantedAuthorities= Lists.newArrayList();
        //用户存在，进行相应的权限设置并返回结果集
        if (user != null) {
            System.out.println(user);
            //获取该用户的角色权限
            List<TbPermission> tbPermissions = tbPermissionService.selectByUserId(user.getId());
            tbPermissions.forEach(t->{
                //创建角色权限
                GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(t.getEnname());
                //将角色权限放入集合
                grantedAuthorities.add(grantedAuthority);
            });
            //返回security包下的user对象封装结果集，包括：用户名、密码、角色权限
            return new User(user.getUsername(),user.getPassword(),grantedAuthorities);
        }
        //用户不存在返回空
        return null;
    }
}
