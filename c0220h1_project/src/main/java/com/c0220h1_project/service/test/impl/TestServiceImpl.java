package com.c0220h1_project.service.test.impl;

import com.c0220h1_project.model.Exam;
import com.c0220h1_project.model.Subject;
import com.c0220h1_project.model.question.Question;
import com.c0220h1_project.model.test.Test;
import com.c0220h1_project.model.test.TestDto;
import com.c0220h1_project.repository.TestRepository;
import com.c0220h1_project.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestRepository testRepository;
    @Override
    public List<TestDto> findAll() {
        return testRepository.findAll().stream().map(this::convertToTestDto).collect(Collectors.toList());
    }

    @Override
    public Test findById(int testId) {
        return testRepository.findById(testId).orElse(null);
    }

    @Override
    public void save(Test test) {
        testRepository.save(test);
    }

    private TestDto convertToTestDto(Test test){
        TestDto testDto = new TestDto();

        testDto.setTestId(test.getTestId());

        testDto.setGrade(test.getGrade());

        Subject subject = test.getSubject();
        testDto.setSubjectId(subject.getSubjectId());

        testDto.setTestCode(test.getTestCode());

        testDto.setTestName(test.getTestName());

        List<Exam> listExam = new ArrayList<>();
        List<Integer> listExamId = new ArrayList<>();
        listExam = test.getExamList();
        for (Exam exam : listExam){
            listExamId.add(exam.getExamId());
        }
        testDto.setExamList(listExamId);

        Set<Question> listQuestion = new HashSet<>();
        Set<String> listQuestionId = new HashSet<>();
        listQuestion = test.getQuestions();
        for (Question question : listQuestion){
            listQuestionId.add(question.getQuestionId());
        }
        testDto.setQuestions(listQuestionId);

        return testDto;
    }

}
