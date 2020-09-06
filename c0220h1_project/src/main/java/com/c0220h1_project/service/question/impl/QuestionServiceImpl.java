package com.c0220h1_project.service.question.impl;

import com.c0220h1_project.model.Subject;
import com.c0220h1_project.model.question.Question;
import com.c0220h1_project.model.question.QuestionDto;
import com.c0220h1_project.model.test.Test;
import com.c0220h1_project.repository.QuestionRepository;
import com.c0220h1_project.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    private QuestionDto convertToQuestionDTO(Question question){
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(question.getQuestionId());
        questionDto.setQuestion(question.getQuestion());
        questionDto.setAnswer(question.getAnswer());
        questionDto.setRightAnswer(question.getRightAnswer());
        questionDto.setRightAnswer(question.getRightAnswer());
        Set<Test> tests = question.getTests();
        Set<Integer> id = new HashSet<>();
        for (Test test : tests){
            id.add(test.getTestId());
        }
        questionDto.setTestId(id);
        Subject subject = question.getSubject();
        questionDto.setSubjectId(subject.getSubjectId());

        return questionDto;
    }
    @Override
    public List<QuestionDto> findAll() {
        return ((List<Question>)questionRepository.findAll()).stream().map(this::convertToQuestionDTO).collect(Collectors.toList());
    }

    @Override
    public QuestionDto findById(String questionId) {
        return (questionRepository.findById(questionId)).map(this::convertToQuestionDTO).orElse(null);

    }

    @Override
    public Question findByIdQuestion(String questionId) {
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
