package controllers;

import connection.DB;
import static controllers.register.md5;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Chapter;
import models.Course;
import models.Helper;
import models.Test;

@WebServlet(name = "createTest", urlPatterns = {"/createTest"})
public class createTest extends HttpServlet {

    private List<String> errors;
    private boolean success = false;

    public createTest() {
        this.errors = new ArrayList<String>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.errors.clear();
        String url = "pages/createTest.jsp";
        
        if( ! Helper.isTeacher(request.getSession().getAttribute("user")) && ! Helper.isAdmin(request.getSession().getAttribute("user"))){
            this.errors.add("You do not have the correct permissions to visit this page.");
            request.setAttribute("errors", this.errors);
            url = "/pages/404.jsp";
        }
        else{
            List<Course> courses = DB.getInstance().getCourses(Helper.getLanguage(request.getSession()));
            request.setAttribute("courses", courses);
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
        this.errors.clear();
        this.success = false;
        int int_time;
        int int_amount_of_questions;
        int int_language = Helper.isInt(language_id);

        //TODO: check if start/end dates are correct (Date object?)
        if (title.equals(""))                                                           this.errors.add("\"Title\" is a required field.");
        if (description.equals(""))                                                     this.errors.add("\"Description\" is a required field.");
        if (time.equals(""))                                                            this.errors.add("\"Time\" is a required field.");
        if ((int_time = Helper.isInt(time)) == -1)                                      this.errors.add("Wrong value for field \"Time\". Time must be all digits.");
        if ((int_time = Helper.isInt(time)) == -1)                                      this.errors.add("Wrong value for field \"Time\". Time must be all digits.");
        if (start_date.equals(""))                                                      this.errors.add("\"Start date\" is a required field.");
        if (end_date.equals(""))                                                        this.errors.add("\"End date\" is a required field.");
        if (course_id.equals(""))                                                       this.errors.add("\"Course\" is a required field.");
        if (amount_of_questions.equals(""))                                             this.errors.add("\"Amount of questions\" is a required field.");
        if ((int_amount_of_questions = Helper.isInt(amount_of_questions)) == -1)        this.errors.add("Wrong value for field \"Amount of questions\". Amount of questions must be all digits.");
        if( ! Helper.allowedLanguage(int_language))                                     this.errors.add("Invalid language selected, please try again.");
                
        Course course = null;
        int int_course_id = Helper.isInt(course_id);
        if ((int_amount_of_questions = Helper.isInt(amount_of_questions)) == -1) {
            this.errors.add("Wrong value for field \"Amount of questions\". Amount of questions must be all digits.");
        }

        if (int_course_id > 0) {
            course = DB.getInstance().getCourse(int_course_id, Helper.getLanguage(request.getSession()));
            if (course == null) {
                this.errors.add("Selected course does not exist");
            }
        } else {
            this.errors.add("Selected course does not exist.");
        }

        String url = "/pages/createTest.jsp";
        if (errors.isEmpty()) {
            Test test = new Test();
            test.setTitle(title);
            test.setDescription(description);
            test.setTime(int_time);
            test.setStart_date(start_date);
            test.setEnd_date(end_date);
            test.setCourse_id(int_course_id);
            test.setAmount_of_questions(int_amount_of_questions);
            test.setLanguage(int_language);

            int test_id = DB.getInstance().insertTest(test);
            
            if (test_id > 0) {
                this.success = true;
                url = "editTest?id=" + test_id;
                response.sendRedirect(url);
                return;
            } else {
                this.errors.add("Something went wrong creating the test, please try again.");
                request.setAttribute("errors", this.errors);
            }
        } else {
            request.setAttribute("errors", this.errors);
            request.setAttribute("courses", DB.getInstance().getCourses(Helper.getLanguage(request.getSession())));
        }

        request.setAttribute("title", title);
        request.setAttribute("description", description);
        request.setAttribute("time", time);
        request.setAttribute("start_date", start_date);
        request.setAttribute("end_date", end_date);
        request.setAttribute("course_id", course_id);
        request.setAttribute("question_amount", amount_of_questions);
        request.setAttribute("success", this.success);

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}