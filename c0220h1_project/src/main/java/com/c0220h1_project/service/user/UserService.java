package com.c0220h1_project.service.user;

import com.c0220h1_project.model.User;

import java.util.List;

public interface UserService {
    boolean save(User user);

    Boolean findByUsername(String Username);

    List<User> findAll();

    void deleteUser(Integer id);

    User findTopByOrderByIdDesc();
}


