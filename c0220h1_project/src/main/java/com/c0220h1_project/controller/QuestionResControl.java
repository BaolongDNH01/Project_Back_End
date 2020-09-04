package com.c0220h1_project.controller;

import com.c0220h1_project.model.Question;
import com.c0220h1_project.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestionResControl {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/getAllQuestion")
    public ResponseEntity<List<Question>> getAllQuestion(){
        List<Question> questionList = questionService.findAll();
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    @GetMapping("getQuestionById/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable ("id") String id) {
        return new ResponseEntity<>(questionService.findById(id),HttpStatus.OK);
    }

    @DeleteMapping(value = "deleteQuestion/{id}")
    public void deleteQuestion(@PathVariable String id){
       questionService.remove(id);
    }

    @PostMapping(value = "/addQuestion")
    public void createQuestion(@RequestBody Question question){
        questionService.save(question);
    }
    @PatchMapping(value = "/editQuestion/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable("id") String id, @RequestBody Question question){
        Question question1 = questionService.findById(id);
        question1.setQuestionId(question.getQuestionId());
        question1.setQuestion(question.getQuestion());
        question1.setAnswer(question.getAnswer());
        question1.setCorrectAnswer(question.getCorrectAnswer());
        questionService.save(question);
        return new ResponseEntity<>(question,HttpStatus.OK);
    }
}
