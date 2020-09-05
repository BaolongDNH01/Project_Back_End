package com.c0220h1_project.service.test;

import com.c0220h1_project.model.test.Test;

import java.util.List;

public interface TestService {
    List<Test> findAll();
    Test findById(int testId);
    void save(Test test);
}
