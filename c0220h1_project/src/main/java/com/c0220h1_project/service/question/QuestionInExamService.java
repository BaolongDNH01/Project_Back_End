package com.c0220h1_project.service.question;

import com.c0220h1_project.model.question.Question;
import com.c0220h1_project.model.question.QuestionDto;
import com.c0220h1_project.model.question.list_question_in_exam.QuestionInExam;

import java.util.List;

public interface QuestionInExamService {
    List<QuestionInExam> findAll();
    QuestionInExam findById(String id);
    void save(QuestionInExam questionInExam);
    void delete(String[] ids);
}
