package com.c0220h1_project.service.question.impl;

import com.c0220h1_project.model.question.Question;
import com.c0220h1_project.repository.QuestionRepository;
import com.c0220h1_project.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question findById(String questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void delete(String questionId) {
        questionRepository.deleteById(questionId);
    }
}
