package models;

import java.util.Date;

/**
 *
 * @author Zanax
 */
public class Course {
    private int courseID;
    private Chapter[] chapters;
    private Student[] students;
    private Test[] tests;
    private int maximumStudents = 0; // 0 betekent geen limiet
    private Teacher headTeacher;
    private boolean isActive = false;
    private Date startDate;
    private Date endDate;
    private String title;
    private String description;

    /**
     * 
     * 
     * 
     */
    public Course() {
        
    }
    
    /**
     * @return the courseID
     */
    public int getCourseID() {
        return courseID;
    }

    /**
     * @param courseID the courseID to set
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    /**
     * @return the maximumStudents
     */
    public int getMaximumStudents() {
        return maximumStudents;
    }

    /**
     * @param maximumStudents the maximumStudents to set
     */
    public void setMaximumStudents(int maximumStudents) {
        this.maximumStudents = maximumStudents;
    }

    /**
     * @return the headTeacher
     */
    public Teacher getHeadTeacher() {
        return headTeacher;
    }

    /**
     * @param headTeacher the headTeacher to set
     */
    public void setHeadTeacher(Teacher headTeacher) {
        this.headTeacher = headTeacher;
    }

    /**
     * @return the isActive
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the chapters
     */
    public Chapter[] getChapters() {
        return chapters;
    }

    /**
     * @param chapters the chapters to set
     */
    public void setChapters(Chapter[] chapters) {
        this.chapters = chapters;
    }

    /**
     * @return the students
     */
    public Student[] getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(Student[] students) {
        this.students = students;
    }

    /**
     * @return the tests
     */
    public Test[] getTests() {
        return tests;
    }

    /**
     * @param tests the tests to set
     */
    public void setTests(Test[] tests) {
        this.tests = tests;
    }
}
