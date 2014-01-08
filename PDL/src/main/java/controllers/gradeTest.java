/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import connection.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Grade;
import models.Helper;
import models.Question;
import models.Teacher;
import models.Test;
import models.User;

/**
 *
 * @author Zanax
 */
@WebServlet(name = "gradeTest", urlPatterns = {"/gradeTest"})
public class gradeTest extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Benodige Info testID en studentID
        if (request.getSession().getAttribute("user") instanceof Teacher) {
            if (request.getParameter("testID") != null) {
                if (request.getParameter("studentID") != null) {
                    try {
                        int testID = Integer.parseInt(request.getParameter("testID"));
                        int studentID = Integer.parseInt(request.getParameter("studentID"));
                        User student = DB.getInstance().getUser(studentID);
                        if (student != null) {
                            Test test = DB.getInstance().getTest(testID, Helper.getLanguage(request.getSession()));
                            if (test != null) {
                                List<Question> questions = DB.getInstance().getQuestions(testID);
                                if (!questions.isEmpty()) {
                                    Map<Integer, String> answers = DB.getInstance().getAnswers(studentID, testID);
                                    if (!answers.isEmpty()) {
                                        request.setAttribute("student", student);
                                        request.setAttribute("answers", answers);
                                        request.setAttribute("test", test);
                                        request.setAttribute("questions", questions);
                                        request.setAttribute("show", true);
                                    } else {
                                        request.setAttribute("errors", "Test didn't contain any answers");
                                    }
                                } else {
                                    request.setAttribute("errors", "Test didn't contain any questions");
                                }
                            } else {
                                request.setAttribute("errors", "Test was not found");
                            }
                        } else {
                            request.setAttribute("errors", "Student was not found");
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                } else {
                    request.setAttribute("errors", "The ID of the Student is not known");
                }
            } else {
                request.setAttribute("errors", "The ID of the Test is not known");
            }
        } else {
            request.setAttribute("errors", "You have no permission");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/pages/gradeTest.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int testID = Integer.parseInt(request.getParameter("testID"));
            int studentID = Integer.parseInt(request.getParameter("studentID"));
            List<Question> questions = DB.getInstance().getQuestions(testID);

            if(questions != null) {
                int points = 0;
                for(Question question : questions) {
                    String g = request.getParameter(Integer.toString(question.getId()));
                    if(g.equalsIgnoreCase("true")) {
                        points++;
                    }
                }

                double gradeFormula = (((double) points / (double) questions.size()) * 9 + 1); 
                // Formula moet miss anders, aangezien ene vraag andere punten aantal geeft?
                Grade grade = new Grade();
                grade.setTestId(testID);
                grade.setUserId(studentID);
                grade.setGrade((int) gradeFormula);
                if(DB.getInstance().insertGrade(grade)) {
                    request.setAttribute("success", true);
                }
            }
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/pages/gradeTest.jsp");
        rd.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
