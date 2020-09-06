package com.c0220h1_project.model;

import com.c0220h1_project.model.test.Test;

import javax.persistence.*;

@Entity
@Table(name = "Exam")
public class Exam {
    @Id
    private Integer examId;
    private String examDate;
    private Double mark;
    private String[] answer;
    private Double times;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    public Exam() {
    }

    public Exam(Integer examId, String examDate, Double mark, String[] answer, Double times) {
        this.examId = examId;
        this.examDate = examDate;
        this.mark = mark;
        this.answer = answer;
        this.times = times;
    }

    public Double getTimes() {
        return times;
    }

    public void setTimes(Double times) {
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
