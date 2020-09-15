package com.c0220h1_project.service.user;

import com.c0220h1_project.model.user.User;
import com.c0220h1_project.model.user.UserDto;
import java.util.List;


public interface UserService {
    Boolean save(User user);

    void edit(User user);

    Boolean findByUsername(String username);

    List<User> findAll();

    Boolean deleteUser(Integer id);

    User findTopByOrderByIdDesc();


    User parseDto(UserDto userDto);

    List<User> findAllMember();
    //    tinh

    User findById(Integer id);

}


