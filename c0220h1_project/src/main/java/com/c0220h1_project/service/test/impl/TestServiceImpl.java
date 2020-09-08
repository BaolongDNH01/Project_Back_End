package com.c0220h1_project.service.test.impl;

import com.c0220h1_project.model.Exam;
import com.c0220h1_project.model.Subject;
import com.c0220h1_project.model.question.Question;
import com.c0220h1_project.model.test.Test;
import com.c0220h1_project.model.test.TestDto;
import com.c0220h1_project.repository.ExamRepository;
import com.c0220h1_project.repository.QuestionRepository;
import com.c0220h1_project.repository.SubjectRepository;
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
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ExamRepository examRepository;
    
    @Override
    public List<TestDto> findAll() {
        List<TestDto> testDtoList = testRepository.findAll().stream().map(this::convertToTestDto).collect(Collectors.toList());
        return testDtoList;
    }

    @Override
    public Test findById(int testId) {
        return testRepository.findById(testId).orElse(null);
    }

    @Override
    public void deleteById(Integer[] ids) {
        for(int id : ids) {
            testRepository.deleteById(id);
        }
    }

    @Override
    public String save(TestDto test) {
        Test test1 = new Test();

        if(!testRepository.existsTestByTestName(test.getTestName())){
            test1.setTestName(test.getTestName());
        }else return "add unsuccessful, name test existed!";

        test1.setGrade(test.getGrade());
        
        Subject subject = subjectRepository.findById(test.getSubjectId()).orElse(null);
        if (subject == null){
            return "add unsuccessful, subject not existed!";
        }else test1.setSubject(subject);
        
        test1.setQuestions(getRandomQuestion(test1));

        test1.setTestCode("001");

        testRepository.save(test1);

        return "add successful!/";
    }

    private Set<Question> getRandomQuestion(Test test1){
        List<Question> questions = new ArrayList<>();
        Set<Question> questionSet = new HashSet<>();
        questions = questionRepository.findQuestionsBySubject(test1.getSubject());
        if(questions.size() >= 10){
            for (int i = 0; i < 10; i++) {
                Question question = questions.get((int) Math.floor(Math.random()*questions.size()));
                questionSet.add(question);
            }
        }else {
            questionSet.addAll(questions);
        }
        return questionSet;
    }

    private TestDto convertToTestDto(Test test){
        TestDto testDto = new TestDto();

        testDto.setTestId(test.getTestId());

        testDto.setGrade(test.getGrade());

        Subject subject = test.getSubject();
        testDto.setSubjectId(subject.getSubjectId());
        testDto.setSubjectName(subject.getSubjectName());

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
