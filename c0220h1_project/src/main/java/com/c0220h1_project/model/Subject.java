package com.c0220h1_project.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int subjectId;
    String subject_name;
    @OneToMany(mappedBy = "subject")
    private List<Question> questions;
    @OneToMany(mappedBy = "subject")
    private List<Test> tests;

}
