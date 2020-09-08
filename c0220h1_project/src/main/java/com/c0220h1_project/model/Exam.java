package com.c0220h1_project.model;

import com.c0220h1_project.model.test.Test;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer examId;
    @NotEmpty
    private String examDate;
    @NotEmpty
    private Double mark;
    @NotEmpty
    private String[] answer;
    @NotEmpty
    private String times;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "test_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Test test;

    public Exam() {
    }

    public Exam(Integer examId, String examDate, Double mark, String[] answer, String times) {
        this.examId = examId;
        this.examDate = examDate;
        this.mark = mark;
        this.answer = answer;
        this.times = times;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public String[] getAnswer() {
        return answer;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
