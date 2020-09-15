package com.c0220h1_project.repository;

import com.c0220h1_project.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findAllByUsername(String username);
    User findTopByOrderByIdDesc();

    Optional<User> findByUsername(String username);
    @Query(value = "SELECT _user.user_id, _user.username, _user.user_password, _user.full_name , _user.address, _user.email, _user.phone_number,_user.avatar FROM _user LEFT JOIN user_role ON  _user.user_id = user_role.user_id  WHERE (user_role.role_id = :id) ",nativeQuery = true)
    List<User> findAllMember(@Param("id")Integer id);
}
