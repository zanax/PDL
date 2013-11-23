/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Zanax
 */
@Entity
public class Chapter { 
      
      
    @Id
    @GeneratedValue
    private long chapterId; 
      
    @ManyToOne
    @JoinColumn(name="courseID")
    private Course course; 
    
    private String chapterName;
      
    public Chapter() { 
    } 

    public long getChapterId() {
        return chapterId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }
  
      
} 
