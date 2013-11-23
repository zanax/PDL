/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Zanax
 */
@Entity
public class Student extends User { 
      
      
    @ManyToOne
    @JoinColumn(name="courseID")
    private Course course; 
    
      
    public Student() { 
    } 

  
  
      
} 
