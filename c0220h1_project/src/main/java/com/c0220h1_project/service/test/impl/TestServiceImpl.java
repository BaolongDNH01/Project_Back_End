package com.c0220h1_project.service.test.impl;

import com.c0220h1_project.model.test.Test;
import com.c0220h1_project.repository.TestRepository;
import com.c0220h1_project.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestRepository testRepository;
    @Override
    public List<Test> findAll() {
        return testRepository.findAll();
    }

    @Override
    public Test findById(int testId) {
        return testRepository.findById(testId).orElse(null);
    }

    @Override
    public void save(Test test) {
        testRepository.save(test);
    }
}
