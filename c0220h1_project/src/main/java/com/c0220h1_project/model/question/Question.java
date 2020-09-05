package com.c0220h1_project.model.question;

import com.c0220h1_project.model.Subject;
import com.c0220h1_project.model.test.Test;

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
    private String answer;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String rightAnswer;

    @ManyToMany
    private
    Set<Test> tests;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    public Question() {
    }

    public Question(String questionId, @NotEmpty @Size(min = 1, max = 255) String question, @NotEmpty @Size(min = 1, max = 255) String answer, @NotEmpty @Size(min = 1, max = 255) String rightAnswer, Set<Test> tests, Subject subject) {
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
        this.rightAnswer = rightAnswer;
        this.tests = tests;
        this.subject = subject;
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
