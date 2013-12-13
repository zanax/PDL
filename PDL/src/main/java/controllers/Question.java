/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

/**
 *
 * @author Maarten
 */
public class Question {
    private int testId;
    private String question;
    private String correctAnswer;
    private String answer1;
    private String answer2;
    private String answer3;

    public Question() {
    }
    
    public Question(int test_id, String question, String correctAnswer, String answer1, String answer2, String answer3) {
        this.testId = test_id;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
    }

    public int getTestId() {
        return testId;
    }
    
    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    void setQuestion(String question) {
        this.question = question;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }
    
    
    
    
    
}
