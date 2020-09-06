package com.c0220h1_project.model.question;
import com.c0220h1_project.model.Subject;
import com.c0220h1_project.model.test.Test;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Question {
    @Id
    private String questionId;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String question;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String answerA;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String answerB;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String answerC;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String answerD;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String rightAnswer;

    @JsonManagedReference
    @ManyToMany
    private
    Set<Test> tests;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)

    private Subject subject;

    public Question() {
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
