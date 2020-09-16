package com.c0220h1_project.controller;

import com.c0220h1_project.model.login_msg.response.ResponseMessage;
import com.c0220h1_project.model.test.TestDto;
import com.c0220h1_project.repository.SubjectRepository;
import com.c0220h1_project.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestResControl {

    @Autowired
    private TestService testService;

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/getAllTest")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TestDto>> getAllTest() {
        return new ResponseEntity<>(testService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getTestById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TestDto> getTestById(@PathVariable Integer id) {
        TestDto test = testService.findById(id);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @PostMapping("/addTest")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseMessage> addTest(@RequestBody TestDto test) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(testService.save(test));
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


    @PostMapping("/importTest")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseMessage> upload(@RequestParam("file") MultipartFile file) {
        String content = "";
        try {
            content = new String(file.getBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] arrData = content.split("\n");
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(testService.importFile(arrData));
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


    @PostMapping("/deleteTest")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteFile(@RequestBody Integer[] ids) {
        testService.deleteById(ids);
    }

    @PostMapping("/removeQuestionInTest")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseMessage> removeQuestionInTest(@RequestBody String[] questionIdsAndIdTest) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(testService.removeQuestionInTest(questionIdsAndIdTest));
        System.out.println(responseMessage.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/addQuestionInTest")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseMessage> addQuestionInTest(@RequestBody String[] questionIdsAndIdTest) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(testService.addQuestionInTest(questionIdsAndIdTest));
        System.out.println(responseMessage.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

}
