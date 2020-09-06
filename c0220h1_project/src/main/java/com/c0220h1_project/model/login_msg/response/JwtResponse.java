<<<<<<< HEAD:c0220h1_project/src/main/java/com/c0220h1_project/model/message/response/JwtResponse.java
//package com.c0220h1_project.model.message.response;
//
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Collection;
//
//public class JwtResponse {
//
//    private String token;
//
//    private String username;
//
//    private Collection<? extends GrantedAuthority> authorities;
//
//
//    public JwtResponse() {
//    }
//
//    public JwtResponse(String token, String username, Collection<? extends GrantedAuthority> authorities) {
//        this.token = token;
//        this.username = username;
//        this.authorities = authorities;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
//        this.authorities = authorities;
//    }
//}
=======
package com.c0220h1_project.model.login_msg.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {

    private String token;

    private String username;

    private Collection<? extends GrantedAuthority> authorities;


    public JwtResponse() {
    }

    public JwtResponse(String token, String username, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.username = username;
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

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
>>>>>>> 783e0c6f98b667289014fed64478e179bf2502a2:c0220h1_project/src/main/java/com/c0220h1_project/model/login_msg/response/JwtResponse.java
