package com.c0220h1_project.repository;

import com.c0220h1_project.model.Exam;
import com.c0220h1_project.model.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam,Integer> {
    @Query("select u.user.username, count(u.user) as positive from Exam u group by u.user order by positive desc")
    List<Object> findUserTopPositive();

    @Query("select  u.user.username, avg(u.mark) as point from Exam u where u.test.subject.subjectId = :subjectId group by u.user having count(u.user) >= 10 order by point desc")
    List<Object> findUserTopExam(Integer subjectId);

    //    tinh
    List<Exam> findByUserUsername(String username);

    // khanh
    List<Exam> findExamsByTest(Test test);
}
