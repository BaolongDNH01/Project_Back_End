package com.c0220h1_project.controller;

import com.c0220h1_project.model.Exam;
import com.c0220h1_project.service.exam.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ExamController {
    @Autowired
    ExamService examService;

    @GetMapping("/get-test/{id}")
    public ResponseEntity<Exam> getTest(@PathVariable Integer id) {
        Exam exam = examService.findById(id);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

    @PostMapping("/create-exam")
    public void createExam(@RequestBody Exam exam){
        examService.save(exam);
    }


    @GetMapping("/get-exam/{id}")
    public ResponseEntity<Exam> getExam(@PathVariable Integer id) {
        Exam exam = examService.findById(id);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }
    @GetMapping("/get-user-point")
    public ResponseEntity<Object> getUserPointDesc(){
        return new ResponseEntity<>(  examService.findUserPointDesc(), HttpStatus.OK);
    }
}
