/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Gijs
 */
public class Grade {

    private int id;
    private int userId;
    private int testId;
    private int grade;
    private String testTitle;

    public Grade() {

    }

    public Grade(int id) {
    }

    public Grade(int id, int userId, int testId, int grade, String testTitle) {
        this.id = id;
        this.userId = userId;
        this.testId = testId;
        this.grade = grade;
        this.testTitle = testTitle;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

}
