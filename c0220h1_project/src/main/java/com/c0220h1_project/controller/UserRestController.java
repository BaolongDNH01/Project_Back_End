
package com.c0220h1_project.controller;

import com.c0220h1_project.model.User;
import com.c0220h1_project.model.UserPrincipal;
import com.c0220h1_project.model.login_msg.request.Login;
import com.c0220h1_project.model.login_msg.response.JwtResponse;
import com.c0220h1_project.security.JwtProvider;
import com.c0220h1_project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserRestController {
    @Autowired
    UserService userService;

    /////////// THIEN ///////////
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtProvider jwtProvider;


    @GetMapping("/listUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getListUser() {
        if (userService.findAll().isEmpty()) {
            return new ResponseEntity<List<User>>((List<User>) null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/allUser")
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@RequestBody User user){
        if (userService.save(user)){
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.CONFLICT);
    }

    @GetMapping("/delete-user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteUser(@PathVariable Integer id){
            userService.deleteUser(id);
            return new ResponseEntity(null,HttpStatus.OK);
    }
    @GetMapping("/new-user")
    public ResponseEntity<User> findUserNew(){
        return new ResponseEntity<>(userService.findTopByOrderByIdDesc(), HttpStatus.OK);
    }

    /////////// THIEN ///////////
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Login loginRequest) throws AuthenticationException {

        Authentication authentication = authManager.authenticate (
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generatingJwt(authentication);
        System.out.println("Token is generated: " + token);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        System.out.println("UserDetails: " + userPrincipal.getUsername());

        JwtResponse response = new JwtResponse(
                token,
                userPrincipal.getUsername(),
                userPrincipal.getAuthorities()
        );
        return ResponseEntity.ok(response);
    }

}

