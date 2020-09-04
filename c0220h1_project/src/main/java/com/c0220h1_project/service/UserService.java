package com.c0220h1_project.service;

import com.c0220h1_project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    Page<User> findAll(Pageable pageable);

    User findById(Integer id);

    void save(User user);

    void remove(Integer id);

}
