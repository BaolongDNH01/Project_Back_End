package com.c0220h1_project.service.user;

import com.c0220h1_project.model.user.User;
import com.c0220h1_project.model.user.UserDto;
import java.util.List;


public interface UserService {
    Boolean save(User user);

//    void save(User user);

    Boolean findByUsername(String username);

    List<User> findAll();

    void deleteUser(Integer id);

    User findTopByOrderByIdDesc();


    User parseDto(UserDto userDto);

    //    tinh

    User findById(Integer id);

}


