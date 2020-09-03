package com.c0220h1_project.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Question {
    @Id
    String questionId;
    String question;
    String answer;
    String rightAnswer;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

}
