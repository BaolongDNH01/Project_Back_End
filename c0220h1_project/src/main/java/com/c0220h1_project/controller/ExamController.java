package com.c0220h1_project.controller;

import com.c0220h1_project.model.Exam;
import com.c0220h1_project.model.exam.ExamDto;
import com.c0220h1_project.service.exam.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ExamController {
    @Autowired
    ExamService examService;

    @PostMapping("/create-exam")
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')" )
    public ResponseEntity<Exam> createExam(@RequestBody ExamDto examDto, UriComponentsBuilder builder) {
        examService.save(examDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/get-exam/{id}").buildAndExpand(examDto.getExamId()).toUri());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/get-exam/{id}")
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')" )
    public ResponseEntity<Exam> getExam(@PathVariable Integer id) {
        Exam exam = examService.findById(id);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

    @GetMapping("/get-user-top-positive")
    public ResponseEntity<Object> getUserTopPositive() {
        return new ResponseEntity<>(examService.findUserTopPositive(), HttpStatus.OK);
    }
    @GetMapping("top-user-exam-subject/{subjectId}")
    public ResponseEntity<Object> get(@PathVariable Integer subjectId){
        return new ResponseEntity<>(examService.findUserTopExam(subjectId), HttpStatus.OK);
    }
}
