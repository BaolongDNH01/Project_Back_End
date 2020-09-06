package com.c0220h1_project.model.question;

import java.util.Set;

public class QuestionDto {
    private String questionId;
    private String question;
    private String answer;
    private String rightAnswer;

    private Set<Integer> testId;
    private int subjectId;

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public Set<Integer> getTestId() {
        return testId;
    }

    public void setTestId(Set<Integer> testId) {
        this.testId = testId;
    }

    public QuestionDto() {
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
