package com.c0220h1_project.service.test;

import com.c0220h1_project.model.test.Test;
import com.c0220h1_project.model.test.TestDto;

import java.util.List;

public interface TestService {
    List<TestDto> findAll();
    String save(TestDto test);
    Test findById(int testId);
    void deleteById(Integer[] ids);
}
