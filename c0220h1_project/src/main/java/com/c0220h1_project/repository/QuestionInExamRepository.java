package com.c0220h1_project.repository;

import com.c0220h1_project.model.question.list_question_in_exam.QuestionInExam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionInExamRepository extends JpaRepository<QuestionInExam,String> {
}
