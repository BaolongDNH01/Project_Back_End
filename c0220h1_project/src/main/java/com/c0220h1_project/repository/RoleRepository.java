package com.c0220h1_project.repository;

import com.c0220h1_project.model.Role;
import com.c0220h1_project.model.constant.ERoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleName(ERoleName roleName);

}
