package com.c0220h1_project.controller;

import com.c0220h1_project.model.question.Question;
import com.c0220h1_project.model.question.QuestionDto;
import com.c0220h1_project.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("questionList")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestionResControl {
    @Autowired
    QuestionService questionService;
    @GetMapping()
    public ResponseEntity<List<QuestionDto>> getListQuestion(){
        return new ResponseEntity<>(questionService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<QuestionDto> getQuestion(@PathVariable String id){
        return new ResponseEntity<>(questionService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/create-question", method = RequestMethod.POST)
    public ResponseEntity<Void> createQuestion(@RequestBody QuestionDto questionDto, UriComponentsBuilder builder){
        questionService.save(questionDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/question/{id}").buildAndExpand(questionDto.getQuestionId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/update-question/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateQuestion(@PathVariable String id, @RequestBody QuestionDto questionDtoForm){
        Question question = questionService.findByIdQuestion(id);
        questionDtoForm.setQuestionId(question.getQuestionId());
        questionService.save(questionDtoForm);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete/{id}")
    public void deleteQuestion(@PathVariable String id){
        questionService.delete(id);
    }
}
