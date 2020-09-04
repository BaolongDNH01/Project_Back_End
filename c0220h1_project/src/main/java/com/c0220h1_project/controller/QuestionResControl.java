package com.c0220h1_project.controller;

import com.c0220h1_project.model.Question;
import com.c0220h1_project.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestionResControl {
    @Autowired
    QuestionService questionService;
    @RequestMapping(value = "/question", method = RequestMethod.GET)
    public ResponseEntity<List<Question>> getListQuestion(){
        return new ResponseEntity<>(questionService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET)
    public ResponseEntity<Question> getQuestion(@PathVariable String id){
        return new ResponseEntity<>(questionService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/create-question", method = RequestMethod.POST)
    public ResponseEntity<Void> createQuestion(@RequestBody Question question, UriComponentsBuilder builder){
        questionService.save(question);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/question/{id}").buildAndExpand(question.getQuestionId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/update-question/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateQuestion(@PathVariable String id, @RequestBody Question questionForm){
        Question question = questionService.findById(id);
        question.setQuestion(questionForm.getQuestion());
        question.setAnswer(questionForm.getAnswer());
        question.setRightAnswer(questionForm.getRightAnswer());
        question.setSubject(questionForm.getSubject());
        question.setTests(questionForm.getTests());
        questionService.save(question);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @RequestMapping(value = "/delete-question/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable String id){
        questionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
