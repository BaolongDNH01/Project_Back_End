package com.c0220h1_project.service.question;

import com.c0220h1_project.model.question.Question;
import com.c0220h1_project.model.question.QuestionDto;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> findAll();
    QuestionDto findById(String questionId);
    Question findByIdQuestion(String questionId);
    void save(QuestionDto questionDto);
    void delete(String questionId);
    String importFile(String[] data);

    List<QuestionDto> getQuestionsToAddToTest(Integer[] ids);
}
