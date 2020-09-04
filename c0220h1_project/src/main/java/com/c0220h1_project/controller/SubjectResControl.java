package com.c0220h1_project.controller;

import com.c0220h1_project.model.Subject;
import com.c0220h1_project.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SubjectResControl {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/getAllSubject")
    public ResponseEntity<List<Subject>> getAllSubject() {
        return new ResponseEntity<>(subjectService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getSubjectById/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Integer id) {
        return new ResponseEntity<>(subjectService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/addSubject")
    public void addSubject(@RequestBody Subject subject) {
        subjectService.save(subject);

    }

}