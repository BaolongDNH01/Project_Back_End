package com.c0220h1_project.controller;

import com.c0220h1_project.model.question.QuestionDto;
import com.c0220h1_project.model.question.list_question_in_exam.QuestionInExam;
import com.c0220h1_project.service.question.QuestionInExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestionInExamResController {
    @Autowired
    QuestionInExamService questionInExamService;

    @GetMapping(value = "/question-in-exam")
    public ResponseEntity<List<QuestionInExam>> getListQuestionInExam() {
        List<QuestionInExam> questionInExamsList = questionInExamService.findAll();
        return new ResponseEntity<>(questionInExamsList, HttpStatus.OK);
    }
    @PostMapping(value = "/addQuestionInExam")
    public void createQuestionInExam(@RequestBody List<QuestionInExam> questionInExams) {
        for (QuestionInExam questionInExam : questionInExams) {
            questionInExamService.save(questionInExam);
        }
    }

    @RequestMapping(value = "/delete-question-in-exam/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteQuestionInExam(@PathVariable String[] id){
        questionInExamService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/question-in-exam/{id}", method = RequestMethod.GET)
    public ResponseEntity<QuestionInExam> getQuestionInExamById(@PathVariable String id){
        return new ResponseEntity<>(questionInExamService.findById(id), HttpStatus.OK);
    }
}
