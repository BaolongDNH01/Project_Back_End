package com.c0220h1_project.service.user.impl;

import com.c0220h1_project.model.Role;
import com.c0220h1_project.model.User;
import com.c0220h1_project.model.constant.ERoleName;
import com.c0220h1_project.repository.RoleRepository;
import com.c0220h1_project.repository.UserRepository;
import com.c0220h1_project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public boolean save(User user) {
        if (!findByUsername(user.getUsername())) {
            return false;
        }
        Set<Role> role = new HashSet<>();
        role.add(roleRepository.findById(2).orElse(null));
        user.setRoles(role);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setUser_password(encoder.encode(user.getUser_password()));
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean findByUsername(String Username) {
        return (userRepository.findByUsername(Username) == null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findTopByOrderByIdDesc() {
        return userRepository.findTopByOrderByIdDesc();
    }
}

