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
import javax.servlet.http.HttpSession;
import models.Course;
import models.Question;
import models.Teacher;

/**
 *
 * @author Gijs
 */
@WebServlet(name = "disableQuestion", urlPatterns = {"/disableQuestion"})
public class disableQuestion extends HttpServlet {

//    private List<String> errors;
//
//    public deleteCourse() {
//        this.errors = new ArrayList<String>();
//    }
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
        
        System.out.println("de doGet is aangeroepen.");
        Question question = DB.getInstance().getQuestion(Integer.parseInt(request.getParameter("id")));

        request.setAttribute("question", question);

        RequestDispatcher rd = request.getRequestDispatcher("/pages/disableQuestion.jsp");
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
        
         String question_nr = request.getParameter("question_id").trim();

        int question_id = Integer.parseInt(question_nr);

        System.out.println("dit is een grap " + "String: " + question_nr + "int: " + question_id);

        DB.getInstance().disableCourse(question_id);
        
        RequestDispatcher rd = request.getRequestDispatcher("/pages/teacherPanel.jsp");
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
