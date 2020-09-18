package com.c0220h1_project.service.question.impl;

import com.c0220h1_project.model.Subject;
import com.c0220h1_project.model.question.Question;
import com.c0220h1_project.model.question.QuestionDto;
import com.c0220h1_project.model.test.Test;
import com.c0220h1_project.repository.QuestionRepository;
import com.c0220h1_project.repository.SubjectRepository;
import com.c0220h1_project.repository.TestRepository;
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
    TestRepository testRepository;

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
        questionDto.setSubjectName(subject.getSubjectName());

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
            tests.add(testService.findByIdReturnTest(id));
        }
        question.setTests(tests);
        questionRepository.save(question);
    }

    @Override
    public void delete(String questionId) {
        List<Test> listDeleteQuestionInTest;
        Question question = questionRepository.findById(questionId).orElse(null);
        if (question != null){
            listDeleteQuestionInTest = testRepository.findTestsByQuestionsContaining(question);
            for (Test test : listDeleteQuestionInTest){
                    List<Question> questionList = test.getQuestions();
                    questionList.remove(question);
            }
        }
            questionRepository.deleteById(questionId);
    }



    @Override
    public List<QuestionDto> getQuestionsToAddToTest(Integer[] ids) {
        List<Question> listQuestionInExam;
        List<Question> listQuestionInSubject;
        Test test = testRepository.findById(ids[0]).get();
        listQuestionInExam = test.getQuestions();

        Subject subject = subjectRepository.findById(ids[1]).get();
        listQuestionInSubject = questionRepository.findQuestionsBySubject(subject);
        if (listQuestionInSubject.size() > 0) {
            for (Question question : listQuestionInExam) {
                listQuestionInSubject.remove(question);
            }
        } else {
            System.out.println("Ko tim thay");
        }

        return listQuestionInSubject.stream().map(this::convertToQuestionDTO).collect(Collectors.toList());
    }

    @Override
    public String importFile(String[] arrData) {
        List<String> arrDataTrim = new ArrayList<>();
        for (String data : arrData) {
            if (!data.equals("")) {
                String dataTrim = data.trim();
                arrDataTrim.add(dataTrim);
            }
        }
        if (arrDataTrim.size() > 300) {
            return "file quá dài so với yêu cầu";
        } else {
            checkValidFile(arrDataTrim);
        }
        return null;
    }



    private String checkValidFile(List<String> arrData) {
        Question questionCheck = new Question();
        for (int i = 0; i < 160; ) {
            String questionCode = arrData.get(i);
            i++;
            System.out.println(questionCode);
            if (questionCode.length() > 5) {
                return "thêm ko thành công, mã đề phải ít hơn 6 ký tự";
            } else {
                questionCheck.setQuestionId(questionCode);
            }

            String question = arrData.get(i);
            System.out.println(question);
            if (question.length() > 300) {
                return "thêm ko thành công, câu hỏi quá dài";
            } else {
                questionCheck.setQuestion(question);
            }
            i++;

            String answerA = arrData.get(i);
            System.out.println(answerA);
            if (question.length() > 300) {
                return "thêm ko thành công, câu trả lời dài";
            } else {
                questionCheck.setAnswerA(answerA);
            }
            i++;

            String answerB = arrData.get(i);
            System.out.println(answerB);
            if (question.length() > 300) {
                return "thêm ko thành công, câu trả lời dài";
            } else {
                questionCheck.setAnswerB(answerB);
            }
            i++;

            String answerC = arrData.get(i);
            System.out.println(answerC);
            if (question.length() > 300) {
                return "thêm ko thành công, câu trả lời dài";
            } else {
                questionCheck.setAnswerC(answerC);
            }
            i++;


            String answerD = arrData.get(i);
            System.out.println(answerD);
            if (question.length() > 300) {
                return "thêm ko thành công, câu trả lời dài";
            } else {
                questionCheck.setAnswerD(answerD);
            }
            i++;

            String rightAnswer = arrData.get(i);
            System.out.println(rightAnswer);
            if (question.length() > 300) {
                return "thêm ko thành công, câu trả lời dài";
            } else {
                questionCheck.setRightAnswer(rightAnswer);
            }
            i++;


            String testSubject = arrData.get(i);
            Subject subject = null;
            System.out.println(testSubject);
            subject = subjectRepository.findSubjectBySubjectName(testSubject);
            i++;

            questionCheck.setSubject(subject);

            System.out.println(subject);
            questionRepository.save(questionCheck);
        }
        return "save ok!";
    }
}
