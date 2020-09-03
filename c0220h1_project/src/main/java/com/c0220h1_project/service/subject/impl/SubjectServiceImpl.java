package com.c0220h1_project.service.subject.impl;

import com.c0220h1_project.model.Subject;
import com.c0220h1_project.repository.SubjectRepository;
import com.c0220h1_project.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectRepository subjectRepository;
    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject findById(int subjectId) {
        return subjectRepository.findById(subjectId).orElse(null);
    }
}
