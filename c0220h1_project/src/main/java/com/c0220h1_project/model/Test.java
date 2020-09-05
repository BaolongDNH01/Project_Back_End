package com.c0220h1_project.model;

import com.c0220h1_project.model.question.Question;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotEmpty
    Integer testId;

    @NotEmpty
    @Size(min = 1, max = 50)
    String testCode;
    @NotEmpty
    @Size(min = 1, max = 50)
    String testName;
    @NotEmpty
    @Size(min = 1, max = 50)
    String grade;
    @ManyToMany
    Set<Question> questions;

    @ManyToOne
    @JoinColumn(name="_subject_id", nullable=false)
    private Subject subject;

    @OneToMany(mappedBy = "test")
    private List<Exam> examList;

    public Test() {
    }

    public Test(@NotEmpty @Size(min = 1, max = 50) String testCode, @NotEmpty @Size(min = 1, max = 50) String testName, @NotEmpty @Size(min = 1, max = 50) String grade, Set<Question> questions, Subject subject) {
        this.testCode = testCode;
        this.testName = testName;
        this.grade = grade;
        this.questions = questions;
        this.subject = subject;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestCode() {
        return testCode;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<Exam> getExamList() {
        return examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }
}