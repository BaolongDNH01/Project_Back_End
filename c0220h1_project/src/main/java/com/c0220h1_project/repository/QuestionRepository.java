package com.c0220h1_project.repository;

import com.c0220h1_project.model.Subject;
import com.c0220h1_project.model.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, String> {
    List<Question> findQuestionsBySubject(Subject subject) ;
}
