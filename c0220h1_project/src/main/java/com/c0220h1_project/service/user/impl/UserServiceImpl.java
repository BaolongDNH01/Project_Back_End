package com.c0220h1_project.service.user.impl;

import com.c0220h1_project.model.Role;
import com.c0220h1_project.model.user.User;
import com.c0220h1_project.model.user.UserDto;
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
        if (Boolean.FALSE.equals(findByUsername(user.getUsername()))) {
            return false;
        }
        Set<Role> role = new HashSet<>();
        role.add(roleRepository.findById(2).orElse(null));
        user.setRoles(role);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setUserPassword(encoder.encode(user.getUserPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean findByUsername(String username) {
        return (userRepository.findByUsername(username) == null);
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

    @Override
    public User parseDto(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setUserPassword(userDto.getUserPassword());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getAddress());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAvatar(userDto.getAvatar());
        user.setExamList(userDto.getExamList());
        return user ;
    }

}

