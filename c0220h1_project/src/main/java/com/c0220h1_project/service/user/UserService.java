package com.c0220h1_project.service.user;

import com.c0220h1_project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    boolean save(User user);

    Boolean findByUsername(String Username);

    Page<User> findAll(Pageable pageable);

    void deleteUser(Integer id);

    User findTopByOrderByIdDesc();

    List<User> findAll();

}


