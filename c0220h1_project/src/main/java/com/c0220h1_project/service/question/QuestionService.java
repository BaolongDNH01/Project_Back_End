package com.c0220h1_project.service.question;

import com.c0220h1_project.model.question.Question;

import java.util.List;

public interface QuestionService {
    List<Question> findAll();
    Question findById(String questionId);
    void save(Question question);
    void delete(String questionId);
}
