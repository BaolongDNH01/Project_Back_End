package com.c0220h1_project.repository;

import com.c0220h1_project.model.Exam;
import com.c0220h1_project.model.UserPoint;
import org.omg.CORBA.Any;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam,Integer> {
    @Query("select u.user.username, sum(u.mark) as point from Exam u group by u.user order by point desc")
    List<Object> findUserPointDesc();
}
