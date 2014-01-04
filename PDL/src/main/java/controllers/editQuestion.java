/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package controllers;

import connection.DB;
import java.io.IOException;
import java.io.PrintWriter;
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
* @author DMC asus
*/
@WebServlet(name = "editQuestion", urlPatterns = {"/editQuestion"})
public class editQuestion extends HttpServlet {
    
    private final List<String> errors;
    
    public editQuestion() {
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
        if (request.getSession().getAttribute("user") instanceof Teacher) {
            List<Test> tests = DB.getInstance().getTests(Helper.getLanguage(request.getSession()));
            if (!tests.isEmpty()) {
                request.setAttribute("tests", tests);
                request.setAttribute("show", true);
            } else {
                request.setAttribute("errors", "You have no test to edit a Question for.");
            }
        } else {
            request.setAttribute("errors", "You have not the right permission.");
        }
        
        
        RequestDispatcher rd = request.getRequestDispatcher("/pages/editQuestion.jsp");
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
        
        Question questionObj = DB.getInstance().getQuestion(Integer.parseInt(request.getParameter("id")));
        
        String question = request.getParameter("question");
        String correctAnswer = request.getParameter("correctAnswer");
        String answer1 = request.getParameter("answer1");
        String answer2 = request.getParameter("answer2");
        String answer3 = request.getParameter("answer3");
        this.errors.clear();

        // Question
        if (question.equals("")) {
            this.errors.add("\"Question\" is a required field.");
        } else {
            questionObj.setQuestion(question);
        }
        
        if (correctAnswer.equals("")) {
            this.errors.add("\"Correct answer\" is a required field.");
        } else {
            questionObj.setCorrectAnswer(correctAnswer);
        }
        
        
        questionObj.setAnswer1(answer1);
        questionObj.setAnswer2(answer2);
        questionObj.setAnswer3(answer3);
        
        
        
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
}