package com.c0220h1_project.service.exam.impl;

import com.c0220h1_project.model.Exam;
import com.c0220h1_project.repository.ExamRepository;
import com.c0220h1_project.service.exam.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    ExamRepository examRepository;

    @Override
    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public Exam findById(Integer examId) {
        return examRepository.findById(examId).orElse(null);
    }

    @Override
    public void save(Exam exam) {
        examRepository.save(exam);
    }

    @Override
    public void delete(Integer examId) {
        examRepository.deleteById(examId);
    }
}
