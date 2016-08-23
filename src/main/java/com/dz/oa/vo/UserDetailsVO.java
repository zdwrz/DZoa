package com.dz.oa.vo;

import com.dz.oa.entity.UserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

/**
 * Created by daweizhuang on 8/11/16.
 */
public class UserDetailsVO implements UserDetails {

    private String password;
    private final String username;
    private UserDetail detailInfo;
    private final Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public UserDetailsVO(String username, String password, UserDetail detailInfo, Set<GrantedAuthority> authorities) {
        this(username,password,detailInfo,authorities,true,true,true,true);
    }

    public UserDetailsVO(String username, String password, UserDetail detailInfo,
                         Set<GrantedAuthority> authorities, boolean accountNonExpired,
                         boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.username = username;
        this.password = password;
        this.detailInfo = detailInfo;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public UserDetail getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(UserDetail detailInfo) {
        this.detailInfo = detailInfo;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "UserDetailsVO{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", detailInfo=" + detailInfo +
                ", authorities=" + authorities +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                '}';
    }
}
