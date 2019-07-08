package com.company.u1m4summativewongmaria.model;

/**
 * DTO to map question and answer to
 */
public class Answer {

    private String question;
    private String answer;

    // constructor

    public Answer() {
    }

    // getters and setters

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

}
