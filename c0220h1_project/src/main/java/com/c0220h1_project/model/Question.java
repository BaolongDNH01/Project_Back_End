package com.c0220h1_project.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer questionId;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String question;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String answer;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String rightAnswer;

    @ManyToMany
    Set<Test> tests;

    @ManyToOne
    @JoinColumn(name = "_subject_id", nullable = false)
    private Subject subject;

    public Question() {
    }

    public Question(@NotEmpty @Size(min = 1, max = 255) String question, @NotEmpty @Size(min = 1, max = 255) String answer, @NotEmpty @Size(min = 1, max = 255) String rightAnswer, Set<Test> tests, Subject subject) {
        this.question = question;
        this.answer = answer;
        this.rightAnswer = rightAnswer;
        this.tests = tests;
        this.subject = subject;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
