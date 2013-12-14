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
        List<Question> questions = new ArrayList<Question>();
        // HardCoded
        Test test = new Test();
        test.setTitle("BonoNielsKunde");
        test.setTime(30);

        Question question1 = new Question(0, 1, "Wat is BonoKunde", "Bono", null, null, null);
        Question question2 = new Question(1, 1, "Wat is NielsKunde", "Niels", "Gijs", "Bono", "Maarten");

        questions.add(question1);
        questions.add(question2);

        request.setAttribute("test", test);
        request.setAttribute("questions", questions);
        //
        request.setAttribute("show", true);
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
        // TODO: submit test to Database
        
        String test_id = request.getParameter("test_id");
        List<Question> questions = new ArrayList<Question>();
        Map<Integer, String> answers = new HashMap<Integer, String>();
        //DB.getInstance().submitAnswers(user_id, answers);
        
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
