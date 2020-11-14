package com.group5.BookRead.services.user;

import com.group5.BookRead.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserPrincipal implements UserDetails {
    private  User user;
    public static final String ROLE_PREFIX = "ROLE_";

    public MyUserPrincipal(final User user) {
        this.user = user;
    }

    public MyUserPrincipal() {
    }
    /**
     * get a users granted authorities
     * @return list of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + "USER"));

        return list;
    }
    /**
     * Get password
     * @return password
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    /**
     * Get username
     * @return username
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    /**
     * check if account is non-expired
     * @return true or false
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**
     * check if account is non-locked
     * @return true or false
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     * check if credential is non-expired
     * @return true or false
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * check if account is enabled
     * @return true or false
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
    /**
     * check if account is enabled
     * @return true or false
     */
    @Override
    public String toString() {
        return "" + user.getId();
    }
}
