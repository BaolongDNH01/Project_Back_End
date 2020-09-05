package com.c0220h1_project.controller;

import com.c0220h1_project.model.test.Test;
import com.c0220h1_project.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestResControl {

    @Autowired
    private TestService testService;

    @GetMapping("/getAllTest")
    public ResponseEntity<List<Test>> getAllTest() {
        return new ResponseEntity<>(testService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getTestById/{id}")
    public ResponseEntity<Test> getTestById(@PathVariable Integer id){
        Test test = testService.findById(id);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @PostMapping("addTest")
    public void addTest(@RequestBody Test test){
        testService.save(test);
    }
}
