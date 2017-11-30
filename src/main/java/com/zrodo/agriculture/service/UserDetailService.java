package com.zrodo.agriculture.service;

import com.zrodo.agriculture.domain.AccountInfo;
import com.zrodo.agriculture.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userDetailService")
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    public UserDetailService( UserMapper usersMapper){
        this.userMapper = usersMapper;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AccountInfo accountInfo = userMapper.queryUserByAccount(username);
        if (accountInfo==null)
            throw new UsernameNotFoundException("找不到该账户信息！");//抛出异常，会根据配置跳到登录失败页面

        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();//GrantedAuthority是security提供的权限类，

        getRoles(accountInfo,list);//获取角色，放到list里面

        org.springframework.security.core.userdetails.User auth_user = new
                org.springframework.security.core.userdetails.User(accountInfo.getAccount(),accountInfo.getPassword(),list);//返回包括权限角色的User给security
        return auth_user;
    }

    /**
     * 获取所属角色
     * @param list
     */
    public void getRoles(AccountInfo accountInfo,List<GrantedAuthority> list){
        for (String role:accountInfo.getRoleId().split(",")) {//权限如果前缀是ROLE_，security就会认为这是个角色信息，而不是权限，例如ROLE_MENBER就是MENBER角色，CAN_SEND就是CAN_SEND权限
            list.add(new SimpleGrantedAuthority("ROLE_"+role));
        }
    }
}