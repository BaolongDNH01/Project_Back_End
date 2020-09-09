package com.c0220h1_project.service.question.impl;

import com.c0220h1_project.model.Subject;
import com.c0220h1_project.model.question.Question;
import com.c0220h1_project.model.question.QuestionDto;
import com.c0220h1_project.model.test.Test;
import com.c0220h1_project.repository.QuestionRepository;
import com.c0220h1_project.repository.SubjectRepository;
import com.c0220h1_project.service.question.QuestionService;
import com.c0220h1_project.service.subject.SubjectService;
import com.c0220h1_project.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    SubjectService subjectService;
    @Autowired
    TestService testService;

    @Autowired
    SubjectRepository subjectRepository;

    private QuestionDto convertToQuestionDTO(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(question.getQuestionId());
        questionDto.setQuestion(question.getQuestion());
        questionDto.setAnswerA(question.getAnswerA());
        questionDto.setAnswerB(question.getAnswerB());
        questionDto.setAnswerC(question.getAnswerC());
        questionDto.setAnswerD(question.getAnswerD());
        questionDto.setRightAnswer(question.getRightAnswer());
        questionDto.setRightAnswer(question.getRightAnswer());
        Set<Test> tests = question.getTests();
        Set<Integer> id = new HashSet<>();
        for (Test test : tests) {
            id.add(test.getTestId());
        }
        questionDto.setTestId(id);
        Subject subject = question.getSubject();
        questionDto.setSubjectId(subject.getSubjectId());

        return questionDto;
    }

    @Override
    public List<QuestionDto> findAll() {
        return ((List<Question>) questionRepository.findAll()).stream().map(this::convertToQuestionDTO).collect(Collectors.toList());
    }

    @Override
    public QuestionDto findById(String questionId) {
        return (questionRepository.findById(questionId)).map(this::convertToQuestionDTO).orElse(null);

    }

    @Override
    public Question findByIdQuestion(String questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }

    @Override
    public void save(QuestionDto questionDto) {
        Question question = new Question();
        question.setQuestionId(questionDto.getQuestionId());
        question.setQuestion(questionDto.getQuestion());
        question.setAnswerA(questionDto.getAnswerA());
        question.setAnswerB(questionDto.getAnswerB());
        question.setAnswerC(questionDto.getAnswerC());
        question.setAnswerD(questionDto.getAnswerD());
        question.setRightAnswer(questionDto.getRightAnswer());
        question.setSubject(subjectService.findById(questionDto.getSubjectId()));
        Set<Test> tests = new HashSet<>();
        for (int id : questionDto.getTestId()) {
            tests.add(testService.findById(id));
        }
        question.setTests(tests);
        questionRepository.save(question);
    }

    @Override
    public void delete(String questionId) {
        questionRepository.deleteById(questionId);
    }

    @Override
    public String importFile(String[] arrData) {
        List<String> arrDataTrim = new ArrayList<>();
        for (String data : arrData) {
            if (!data.equals("")) {
                arrDataTrim.add(data);
            }
        }
        if (arrDataTrim.size() > 100) {
            return "file quá dài so với yêu cầu";
        } else {
            checkValidFile(arrDataTrim);
        }
        return null;
    }

    private String checkValidFile(List<String> arrData) {
        Question questionCheck = new Question();

        String questionCode = arrData.get(0);
        System.out.println(questionCode);
        if (questionCode.length() > 5) {
            return "thêm ko thành công, mã đề phải ít hơn 6 ký tự";
        } else {
            questionCheck.setQuestionId(questionCode);
        }

        String question = arrData.get(1);
        System.out.println(question);
        if (question.length() > 100) {
            return "thêm ko thành công, câu hỏi quá dài";
        } else {
            questionCheck.setQuestion(question);
        }

        String answerA = arrData.get(2);
        System.out.println(answerA);
        if (question.length() > 100) {
            return "thêm ko thành công, câu trả lời dài";
        } else {
            questionCheck.setAnswerA(answerA);
        }

        String answerB = arrData.get(3);
        System.out.println(answerB);
        if (question.length() > 100) {
            return "thêm ko thành công, câu trả lời dài";
        } else {
            questionCheck.setAnswerB(answerB);
        }

        String answerC = arrData.get(4);
        System.out.println(answerC);
        if (question.length() > 100) {
            return "thêm ko thành công, câu trả lời dài";
        } else {
            questionCheck.setAnswerC(answerC);
        }


        String answerD = arrData.get(5);
        System.out.println(answerD);
        if (question.length() > 100) {
            return "thêm ko thành công, câu trả lời dài";
        } else {
            questionCheck.setAnswerD(answerD);
        }

        String rightAnswer = arrData.get(6);
        System.out.println(rightAnswer);
        if (question.length() > 100) {
            return "thêm ko thành công, câu trả lời dài";
        } else {
            questionCheck.setRightAnswer(rightAnswer);
        }



        String testSubject = arrData.get(7);
        Subject subject = null;
        System.out.println(testSubject);
        if(testSubject.equals("toan")){
            subject = subjectRepository.findById(1).orElse(null);
            System.out.println("ở đây");
        }else if (testSubject.equals("ly")) {
            subject = subjectRepository.findById(2).orElse(null);
        }else if (testSubject.equals("hoa")) {
            subject = subjectRepository.findById(3).orElse(null);
        }else if (testSubject.equals("anh")) {
            subject = subjectRepository.findById(4).orElse(null);
        }
        questionCheck.setSubject(subject);

        System.out.println(subject);
        questionRepository.save(questionCheck);

        return "save ok!";
    }
}
