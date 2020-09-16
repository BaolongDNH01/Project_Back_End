package com.c0220h1_project.service.exam.impl;

import com.c0220h1_project.model.Exam;
import com.c0220h1_project.model.exam.ExamDto;
import com.c0220h1_project.repository.ExamRepository;
import com.c0220h1_project.service.exam.ExamService;
import com.c0220h1_project.service.test.TestService;
import com.c0220h1_project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    ExamRepository examRepository;
    @Autowired
    UserService userService;
    @Autowired
    TestService testService;
    @Override
    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public Exam findById(Integer examId) {
        return examRepository.findById(examId).orElse(null);
    }

    @Override
    public void save(ExamDto examDto) {
        Exam exam = new Exam();
        exam.setTimes(examDto.getTimes());
        exam.setMark(examDto.getMark());
        exam.setExamDate(examDto.getExamDate());
        exam.setAnswer(examDto.getAnswer());
        exam.setUser(userService.findUserName(examDto.getUser()));
        exam.setTest(testService.findByIdReturnTest(examDto.getTest()));
        examRepository.save(exam);
    }

    @Override
    public void delete(Integer examId) {
        examRepository.deleteById(examId);
    }

    @Override
    public List<Object> findUserPointDesc() {
        return examRepository.findUserPointDesc();
    }

    @Override
    public List<Exam> findByUserId(Integer id) {
        return examRepository.findByUserId(id);
    }


}
