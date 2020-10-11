package com.c0220h1_project.repository;

import com.c0220h1_project.model.question.Question;
import com.c0220h1_project.model.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Integer> {
    boolean existsTestByTestName(String name);

    List<Test> findBySubject_SubjectName(String name);
    List<Test> findTestsByQuestionsContaining(Question question);
}

