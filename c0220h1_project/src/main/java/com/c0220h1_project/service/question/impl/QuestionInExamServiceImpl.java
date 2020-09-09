package com.c0220h1_project.service.question.impl;

import com.c0220h1_project.model.question.list_question_in_exam.QuestionInExam;
import com.c0220h1_project.repository.QuestionInExamRepository;
import com.c0220h1_project.service.question.QuestionInExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionInExamServiceImpl implements QuestionInExamService {

    @Autowired
    QuestionInExamRepository questionInExamRepository;

    @Override
    public List<QuestionInExam> findAll() {
        return questionInExamRepository.findAll();
    }

    @Override
    public QuestionInExam findById(String id) {
        return questionInExamRepository.findById(id).orElse(null);
    }

    @Override
    public void save(QuestionInExam questionInExam) {
        questionInExamRepository.save(questionInExam);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            questionInExamRepository.deleteById(id);
        }

    }
}
