package com.c0220h1_project.controller;

import com.c0220h1_project.model.User;
import com.c0220h1_project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserRestController {
    @Autowired
    UserService userService;

    @GetMapping("/listUser")
    public ResponseEntity<Page<User>> getListUser(@PageableDefault(size = 10) Pageable pageable) {
        if (userService.findAll(pageable).isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/allUser")
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/register")
    public ResponseEntity registerUser(User user){
        if (userService.save(user)){
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.CONFLICT);
    }

    @GetMapping("/delete-user/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
            userService.deleteUser(id);
            return new ResponseEntity(null,HttpStatus.OK);
    }
    @GetMapping("/new-user")
    public ResponseEntity<User> findUserNew(){
        return new ResponseEntity<>(userService.findTopByOrderByIdDesc(), HttpStatus.OK);
    }
}
