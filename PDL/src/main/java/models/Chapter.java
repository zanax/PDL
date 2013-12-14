package models;

public class Chapter {
    private int id;
    private int course_id; 
    private String title;
      
    public Chapter(int id) { 
        this.id = id;
    } 
    

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getCourse_id() {
        return course_id;
    }
    
    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
} 
