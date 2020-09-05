package com.c0220h1_project.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.c0220h1_project.model.question.Question;
import com.c0220h1_project.model.test.Test;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity(name = "_subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer subjectId;

    @NotEmpty
    @Size(min = 1, max = 255)
    String subjectName;

    @JsonManagedReference
    @OneToMany(mappedBy="subject")
    private Set<Test> tests;

    @JsonBackReference
    @OneToMany(mappedBy="subject")
    private Set<Question> questions;

    public Subject() {
    }

    public Subject(@NotEmpty @Size(min = 1, max = 255) String subjectName, Set<Test> tests, Set<Question> questions) {
        this.subjectName = subjectName;
        this.tests = tests;
        this.questions = questions;
    }

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
