package com.example.mybms.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {
    private SysUser sysUser;
    private List<String> authentication;
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;


    //带权限列表的构造函数
    public LoginUser(SysUser user, List<String> authentication) {
        this.sysUser = user;
        this.authentication = authentication;
    }

    //返回权限信息的方法
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities!=null)
            return authorities;
        authorities =  authentication.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }


    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
