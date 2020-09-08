package com.c0220h1_project.model.question.list_question_in_exam;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class QuestionInExam {
    @Id
    private String questionId;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String question;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String rightAnswer;

    public QuestionInExam() {
    }

    public QuestionInExam(String questionId, @NotEmpty @Size(min = 1, max = 255) String question, @NotEmpty @Size(min = 1, max = 255) String rightAnswer) {
        this.questionId = questionId;
        this.question = question;
        this.rightAnswer = rightAnswer;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
