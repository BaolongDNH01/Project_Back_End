package com.c0220h1_project.repository;

import com.c0220h1_project.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findAllByUsername(String Username);
    User findTopByOrderByIdDesc();

    Optional<User> findByUsername(String username);

}
