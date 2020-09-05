package com.c0220h1_project.repository;

import com.c0220h1_project.model.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Integer> {
}
