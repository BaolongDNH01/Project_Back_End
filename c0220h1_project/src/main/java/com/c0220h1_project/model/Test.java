package com.c0220h1_project.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Test {
    int testId;
    String testCode;
    String testName;
    String grade;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
