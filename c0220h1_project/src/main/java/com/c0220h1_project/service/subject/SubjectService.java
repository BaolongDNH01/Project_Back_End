package com.c0220h1_project.service.subject;

import com.c0220h1_project.model.Subject;
import com.c0220h1_project.model.Test;

import java.util.List;

public interface SubjectService {
    List<Subject> findAll();
    Subject findById(int subjectId);
    void save(Subject subject);
}
