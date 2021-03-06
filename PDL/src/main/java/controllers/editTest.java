package controllers;

import connection.DB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Course;
import models.Helper;
import models.Test;

@WebServlet(name = "editTest", urlPatterns = {"/editTest"})
public class editTest extends HttpServlet {
    private List<String> errors;
    private boolean success = false;
    
    public editTest(){
        this.errors = new ArrayList<String>();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.errors.clear();
        String url = "/pages/createTest.jsp";
        if( ! Helper.isTeacher(request.getSession().getAttribute("user")) && ! Helper.isAdmin(request.getSession().getAttribute("user"))){
            this.errors.add("You do not have the correct permissions to visit this page.");
            request.setAttribute("errors", this.errors);
            url = "/pages/404.jsp";
        }
        else{
            int test_id = Helper.isInt(request.getParameter("id"));
            Test test = null;
            if(test_id > -1){
                test = DB.getInstance().getTest(test_id, Helper.getLanguage(request.getSession()));

                if(test == null){
                    this.errors.add("The requested test does not exist.");
                }
            }
            else{
                this.errors.add("The requested test does not exist.");
            }

            request.setAttribute("test", test);
            if( ! this.errors.isEmpty()) request.setAttribute("errors", this.errors);

            //Courses ophalen voor form
            List<Course> courses = DB.getInstance().getCourses(Helper.getLanguage(request.getSession()));
            request.setAttribute("courses", courses);

            url = "/pages/editTest.jsp";
            if( ! errors.isEmpty()) url = "/pages/createTest.jsp";
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title").trim();
        String description = request.getParameter("description").trim();
        String time = request.getParameter("time").trim();
        String start_date = request.getParameter("start_date").trim();
        String end_date = request.getParameter("end_date").trim();
        String course_id = request.getParameter("course_id").trim();
        String amount_of_questions = request.getParameter("question_amount").trim();
        String language_id = request.getParameter("language_id");
        int id = Helper.isInt(request.getParameter("id"));
        this.errors.clear();
        this.success = false;
        int int_time;
        int int_amount_of_questions;
        int int_language = Helper.isInt(language_id);
        
        //TODO: check if start/end dates are correct (Date object?)
        
        if(title.equals(""))                                                    this.errors.add("\"Title\" is a required field.");
        if(description.equals(""))                                              this.errors.add("\"Description\" is a required field.");
        if(time.equals(""))                                                     this.errors.add("\"Time\" is a required field.");
        if((int_time = Helper.isInt(time)) == -1)                               this.errors.add("Wrong value for field \"Time\". Time must be all digits.");
        if(start_date.equals(""))                                               this.errors.add("\"Start date\" is a required field.");
        if(end_date.equals(""))                                                 this.errors.add("\"End date\" is a required field.");
        if(course_id.equals(""))                                                this.errors.add("\"Course\" is a required field.");
        if(amount_of_questions.equals(""))                                      this.errors.add("\"Amount of questions\" is a required field.");
        if((int_amount_of_questions = Helper.isInt(amount_of_questions)) == -1) this.errors.add("Wrong value for field \"Amount of questions\". Amount of questions must be all digits.");
        if( ! Helper.allowedLanguage(int_language))                             this.errors.add("Invalid language selected, please try again.");
        
        Course course = null;
        int int_course_id = Helper.isInt(course_id);
        if(int_course_id > 0){
            course = DB.getInstance().getCourse(int_course_id, Helper.getLanguage(request.getSession()));
            if(course == null) this.errors.add("Selected course does not exist");
        }
        else{
            this.errors.add("Selected course does not exist.");
        }
        
        
        String url = "/pages/editTest.jsp";
        if( ! (id > 0)){
            url = "createTest";
        }
        
        if(errors.isEmpty()){
            Test test = new Test(id);
            test.setTitle(title);
            test.setDescription(description);
            test.setTime(int_time);
            test.setStart_date(start_date);
            test.setEnd_date(end_date);
            test.setCourse_id(int_course_id);
            test.setAmount_of_questions(int_amount_of_questions);
            test.setLanguage(int_language);
            
            int affected_rows = DB.getInstance().updateTest(test);
            
            if(affected_rows > 0){
                this.success = true;                
                request.setAttribute("test", test);
            }
            else{
                this.errors.add("Something went wrong with saving the test. Please try again.");
            }
        }
        
        request.setAttribute("courses", DB.getInstance().getCourses(Helper.getLanguage(request.getSession())));
        request.setAttribute("success", this.success);
        request.setAttribute("errors", this.errors);
        
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}