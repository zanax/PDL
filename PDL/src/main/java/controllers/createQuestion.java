/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
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
import models.Helper;
import models.Question;
import models.Teacher;
import models.Test;

/**
*
* @author Maarten
*/
@WebServlet(name = "createQuestion", urlPatterns = {"/createQuestion"})
public class createQuestion extends HttpServlet {

    private final List<String> errors;

    public createQuestion() {
        errors = new ArrayList<String>();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
* Handles the HTTP
* <code>GET</code> method.
*
* @param request servlet request
* @param response servlet response
* @throws ServletException if a servlet-specific error occurs
* @throws IOException if an I/O error occurs
*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        this.errors.clear();
        String url = "/pages/createQuestion.jsp";
        
        if (Helper.isTeacher(request.getSession().getAttribute("user")) || Helper.isAdmin(request.getSession().getAttribute("user"))) {
// List<Test> tests = DB.getInstance().getUserTests((Teacher) request.getSession().getAttribute("user"));
            List<Test> tests = DB.getInstance().getTests(Helper.getLanguage(request.getSession()));
            if (!tests.isEmpty()) {
                request.setAttribute("tests", tests);
                request.setAttribute("show", true);
            } else {
                request.setAttribute("errors", "You have no Tests to create a Question for.");
            }
        } else {
            this.errors.add("You do not have the correct permissions to visit this page.");
            request.setAttribute("errors", this.errors);
            url = "/pages/404.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    /**
* Handles the HTTP
* <code>POST</code> method.
*
* @param request servlet request
* @param response servlet response
* @throws ServletException if a servlet-specific error occurs
* @throws IOException if an I/O error occurs
*/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("user") instanceof Teacher) {
            errors.clear();
            
            Question questionObj = new Question();
            String test = request.getParameter("test");
            String question = request.getParameter("question");
            String correctAnswer = request.getParameter("correctAnswer");
            String answer1 = request.getParameter("answer1");
            String answer2 = request.getParameter("answer2");
            String answer3 = request.getParameter("answer3");

            if (!test.equals("")) {
                try {
                    questionObj.setTestId(Integer.parseInt(test));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                errors.add("Test not filled in");
            }

            if (!question.equals("")) {
                questionObj.setQuestion(question);
            } else {
                errors.add("Question not filled in");
            }

            if (!correctAnswer.equals("")) {
                questionObj.setCorrectAnswer(correctAnswer);
            } else {
                errors.add("Correct Answer not filled in");
            }

            if (!answer1.equals("")) {
                questionObj.setAnswer1(answer1);
            } else{
                questionObj.setAnswer1("");
            }

            if (!answer2.equals("")) {
                questionObj.setAnswer2(answer2);
            } else{
                questionObj.setAnswer2("");
            }

            if (!answer3.equals("")) {
                questionObj.setAnswer3(answer3);
            } else{
                questionObj.setAnswer3("");
            }

            
               DB.getInstance().insertQuestion(questionObj);


            if (errors.isEmpty()) {
                request.setAttribute("success", true);
            } else {
                request.setAttribute("questionObj", questionObj);
                request.setAttribute("errors", errors);
            }


            request.setAttribute("show", true);
            RequestDispatcher rd = request.getRequestDispatcher("/pages/createQuestion.jsp");
            rd.forward(request, response);
        }
    }

    /**
* Returns a short description of the servlet.
*
* @return a String containing servlet description
*/
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
// optie 2
// if (request.getSession().getAttribute("user") instanceof Teacher) {
// List<Course> courses = DB.getInstance().getUserCourses((Teacher) request.getSession().getAttribute("user"));
// if (courses.isEmpty()) {
// List<Integer> course_ids = new ArrayList<Integer>();
// for (Course course : courses) { course_ids.add(course.getId()); }
// List<Test> tests = DB.getInstance().getTestsByCouseID(course_ids);
// if (!tests.isEmpty()) {
// request.setAttribute("courses", courses);
// request.setAttribute("tests", tests);
// request.setAttribute("show", true);
// } else {
// request.setAttribute("errors", "You have no Tests to create a Question for.");
// }
// } else {
// request.setAttribute("errors", "You have no Courses to create a Question for.");
// }
// } else {
// request.setAttribute("errors", "You have not the right permission.");
// }
}