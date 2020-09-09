package com.c0220h1_project.service.exam;

import com.c0220h1_project.model.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> findAll();
    Exam findById(Integer examId);
    void save(Exam exam);
    void delete(Integer examId);
    List<Object> findUserPointDesc();

    //    tinh
    List<Exam> findByUserId(Integer id);
}
