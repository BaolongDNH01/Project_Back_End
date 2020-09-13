
package com.c0220h1_project.model.login_msg.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {

    private String token;

    private String username;

    private String email;

    private String avatar;

    private Collection<? extends GrantedAuthority> authorities;


    public JwtResponse() {
    }

    public JwtResponse(String token, String username, String email, String avatar, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.avatar = avatar;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
