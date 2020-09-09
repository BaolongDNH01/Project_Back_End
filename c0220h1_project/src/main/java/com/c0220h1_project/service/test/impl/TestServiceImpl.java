package com.c0220h1_project.service.test.impl;

import com.c0220h1_project.model.Exam;
import com.c0220h1_project.model.Subject;
import com.c0220h1_project.model.question.Question;
import com.c0220h1_project.model.test.Test;
import com.c0220h1_project.model.test.TestDto;
import com.c0220h1_project.repository.ExamRepository;
import com.c0220h1_project.repository.QuestionRepository;
import com.c0220h1_project.repository.SubjectRepository;
import com.c0220h1_project.repository.TestRepository;
import com.c0220h1_project.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestRepository testRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ExamRepository examRepository;

    @Override
    public List<TestDto> findAll() {
        List<TestDto> testDtoList = testRepository.findAll().stream().map(this::convertToTestDto).collect(Collectors.toList());
        return testDtoList;
    }

    @Override
    public Test findById(int testId) {
        return testRepository.findById(testId).orElse(null);
    }

    @Override
    public void deleteById(Integer[] ids) {
        for (int id : ids) {
            testRepository.deleteById(id);
        }
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
        if (arrDataTrim.size() > 64) {
            return "can't import file(data more than 64 rows)";
        } else {
            checkValidFile(arrDataTrim);
        }
        return null;
    }

    private String checkValidFile(List<String> arrData) {
        Test test = new Test();

//        check và thêm tên đề thi
        String testName = arrData.get(0);
        System.out.println(testName);
        if (testName.length() > 51) {
            return "import unsuccessful, name too long";
        } else {
            test.setTestName(testName);
        }

//        check và thêm môn

        String testSubject = arrData.get(1);
        Subject subject = null;
        System.out.println(testSubject);
        subject = subjectRepository.findSubjectBySubjectName(testSubject);
        System.out.println(subject);
        if(subject == null){
            return "import unsuccessful, subject is not exist";
        }
        test.setSubject(subject);


//        thêm mã đề
        String testCode = arrData.get(2);
        System.out.println(testCode);
        if (testCode.length() > 5) {
            return "import unsuccessful, test code must be less than 6 character";
        } else {
            test.setTestCode(testCode);
        }

//      thêm khối thi
        String testGrade = arrData.get(3);
        System.out.println(testGrade);
        if (testGrade.length() > 3) {
            return "import unsuccessful, grade must be less than 4 character";
        } else {
            test.setGrade(testGrade);
        }

//        thêm câu hỏi
        Set<Question> questionSet = new HashSet<>();
        Integer totalQuestion = 0;
        for (int i = 4; i < 64; i += 6) {
            Question question = new Question();
            String idQuestion = geneRandomId();
            question.setQuestionId(idQuestion);
            question.setQuestion(arrData.get(i));
            question.setAnswerA(arrData.get(i + 1));
            question.setAnswerB(arrData.get(i + 2));
            question.setAnswerC(arrData.get(i + 3));
            question.setAnswerD(arrData.get(i + 4));
            question.setRightAnswer(arrData.get(i + 5));
            question.setSubject(subject);
            questionRepository.save(question);
            questionSet.add(questionRepository.findById(idQuestion).orElse(null));
            totalQuestion += 1;
        }
//        kiểm tra xem đã đủ 10 câu chưa rồi add thêm
        List<Question> questions = questionRepository.findQuestionsBySubject(subject);
        if (totalQuestion - 10 < 0) {
            if (questions.size() > 10 - totalQuestion) {
                for (int i = 0; i < 10 - totalQuestion; i++) {
                    Question question = questions.get((int) Math.floor(Math.random() * questions.size()));
                    questionSet.add(question);
                }
            }else {
                questionSet.addAll(questions);
                test.setQuestions(questionSet);
                return "import successful, file not enough question then automatically add questions from system";
            }
        }
        test.setQuestions(questionSet);
        System.out.println(test);
        testRepository.save(test);
        return "import successful";
    }


        private String geneRandomId () {
            int idQuestion = (int) (Math.random() * 9999);
            if (questionRepository.existsById(Integer.toString(idQuestion))) {
                geneRandomId();
            } else {
                return Integer.toString(idQuestion);
            }
            return null;
        }

        @Override
        public String save (TestDto test){
            Test test1 = new Test();

            if (!testRepository.existsTestByTestName(test.getTestName())) {
                test1.setTestName(test.getTestName());
            } else return "add unsuccessful, name test existed";

            test1.setGrade(test.getGrade());

            Subject subject = subjectRepository.findById(test.getSubjectId()).orElse(null);
            if (subject == null) {
                return "add unsuccessful, subject not existed";
            } else test1.setSubject(subject);

            test1.setQuestions(getRandomQuestion(test1));

            test1.setTestCode(test.getTestCode());

            testRepository.save(test1);

            return "add successful";
        }

        private Set<Question> getRandomQuestion (Test test1){
            List<Question> questions = new ArrayList<>();
            Set<Question> questionSet = new HashSet<>();
            questions = questionRepository.findQuestionsBySubject(test1.getSubject());
            if (questions.size() >= 10) {
                for (int i = 0; i < 10; i++) {
                    Question question = questions.get((int) Math.floor(Math.random() * questions.size()));
                    questionSet.add(question);
                }
            } else {
                questionSet.addAll(questions);
            }
            return questionSet;
        }

        private TestDto convertToTestDto (Test test){
            TestDto testDto = new TestDto();

            testDto.setTestId(test.getTestId());

            testDto.setGrade(test.getGrade());

            Subject subject = test.getSubject();
            testDto.setSubjectId(subject.getSubjectId());
            testDto.setSubjectName(subject.getSubjectName());

            testDto.setTestCode(test.getTestCode());

            testDto.setTestName(test.getTestName());

            List<Exam> listExam = new ArrayList<>();
            List<Integer> listExamId = new ArrayList<>();
            listExam = test.getExamList();
            for (Exam exam : listExam) {
                listExamId.add(exam.getExamId());
            }
            testDto.setExamList(listExamId);

            Set<Question> listQuestion = new HashSet<>();
            Set<String> listQuestionId = new HashSet<>();
            listQuestion = test.getQuestions();
            for (Question question : listQuestion) {
                listQuestionId.add(question.getQuestionId());
            }
            testDto.setQuestions(listQuestionId);

            return testDto;
        }

    }
