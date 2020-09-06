package com.c0220h1_project.service.question;

import com.c0220h1_project.model.question.Question;
import com.c0220h1_project.model.question.QuestionDto;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> findAll();
    QuestionDto findById(String questionId);
    Question findByIdQuestion(String questionId);
    void save(Question question);
    void delete(String questionId);
}
