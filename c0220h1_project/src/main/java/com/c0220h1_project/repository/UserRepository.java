package com.c0220h1_project.repository;

import com.c0220h1_project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findAllByUsername(String Username);
    User findTopByOrderByIdDesc();
}
