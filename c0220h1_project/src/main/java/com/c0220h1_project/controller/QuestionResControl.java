package com.c0220h1_project.controller;

import com.c0220h1_project.model.question.Question;
import com.c0220h1_project.model.question.QuestionDto;
import com.c0220h1_project.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestionResControl {
    @Autowired
    QuestionService questionService;
    @GetMapping(value = "/question")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<QuestionDto>> getListQuestion(){
        return new ResponseEntity<>(questionService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/question/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<QuestionDto> getQuestion(@PathVariable String id){
        return new ResponseEntity<>(questionService.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/add-question")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createQuestion(@RequestBody QuestionDto questionDto, UriComponentsBuilder builder){
        questionService.save(questionDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/question/{id}").buildAndExpand(questionDto.getQuestionId()).toUri());
        return new ResponseEntity<>("Create", HttpStatus.CREATED);
    }

    @PutMapping(value = "/update-question/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateQuestion(@PathVariable String id, @RequestBody QuestionDto questionDtoForm){
        Question question = questionService.findByIdQuestion(id);
        questionDtoForm.setQuestionId(question.getQuestionId());
        questionService.save(questionDtoForm);
        return new ResponseEntity<>("Update", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-question/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteQuestion(@PathVariable String id){
        questionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("importQuestion")
    @PreAuthorize("hasRole('ADMIN')")
    public void upload(@RequestParam("file") MultipartFile file) throws IOException {
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);
        String[] arrData = content.split("\n");
        questionService.importFile(arrData);
    }

    @GetMapping("getQuestionsToAddToTest/{ids}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<QuestionDto>> getQuestionToAddToTest(@PathVariable Integer[] ids){
            return new ResponseEntity<>(questionService.getQuestionsToAddToTest(ids),HttpStatus.OK);
    }
}
