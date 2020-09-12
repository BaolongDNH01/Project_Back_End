package com.c0220h1_project.controller;

import com.c0220h1_project.model.Exam;
import com.c0220h1_project.service.exam.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ExamController {
    @Autowired
    ExamService examService;

    @PostMapping("/create-exam")
    public ResponseEntity<Void> createExam(@RequestBody Exam exam, UriComponentsBuilder builder) {
        examService.save(exam);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/get-exam/{id}").buildAndExpand(exam.getExamId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @GetMapping("/get-exam/{id}")
    public ResponseEntity<Exam> getExam(@PathVariable Integer id) {
        Exam exam = examService.findById(id);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

    @GetMapping("/get-user-point")
    public ResponseEntity<Object> getUserPointDesc() {
        return new ResponseEntity<>(examService.findUserPointDesc(), HttpStatus.OK);
    }
}
