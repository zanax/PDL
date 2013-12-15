/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import connection.DB;
import models.Question;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Test;
import models.User;

/**
 *
 * @author Zanax
 */
@WebServlet(name = "makeTest", urlPatterns = {"/makeTest"})
public class makeTest extends HttpServlet {

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
        if (request.getSession().getAttribute("user") instanceof User) {
            if (request.getParameter("id") != null) {
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Test test = DB.getInstance().getTest(id);
                    if (test != null) {
                        List<Question> questions = DB.getInstance().getQuestions(id);
                        if (!questions.isEmpty()) {
                            request.setAttribute("test", test);
                            request.setAttribute("questions", questions);
                            request.setAttribute("show", true);
                        } else {
                            request.setAttribute("errors", "Test didn't contain any questions");
                        }
                    } else {
                        request.setAttribute("errors", "Test was not found");
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            } else {
                request.setAttribute("errors", "The ID of the Test is not known");
            }
        } else {
            request.setAttribute("errors", "You have no permission");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/pages/makeTest.jsp");
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
        if (request.getSession().getAttribute("user") != null) {
            if (request.getAttribute("id") != null) {
                try {
                    int id = (Integer) request.getAttribute("id");
                    Test test = DB.getInstance().getTest(id);
                    if (test != null) {
                        List<Question> questions = DB.getInstance().getQuestions(id);
                        if (questions != null) {
                            User user = (User) request.getSession().getAttribute("user");
                            Map<Integer, String> answers = new HashMap<Integer, String>();
                            
                            for(Question question : questions) {
                                answers.put(question.getId(), (String) request.getAttribute(Integer.toString(question.getId())));
                            }
                            
                            //Debug
                            for(String answer : answers.values()) {
                                System.out.println(answer);
                            }
                            
//                            if (DB.getInstance().submitAnswers((int) user.getId(), answers)) {
//
//                            } else {
//
//                            }
                        } else {

                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            } else {

            }
        } else {

        }

        request.setAttribute("success", true);
        RequestDispatcher rd = request.getRequestDispatcher("/pages/makeTest.jsp");
        rd.forward(request, response);
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
