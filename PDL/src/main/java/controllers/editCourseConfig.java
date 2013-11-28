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
import models.Course;

/**
 *
 * @author Zanax
 */
@WebServlet(name = "editCourseConfig", urlPatterns = {"/editCourseConfig"})
public class editCourseConfig extends HttpServlet {

    private List<String> errors;
    private boolean databaseError = false;

    public editCourseConfig() {
        this.errors = new ArrayList<String>();
    }

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
        // IF-statement als User een Teacher is

        if (request.getParameter("id") != null) {
            DB db = DB.getInstance();
            Course course = db.getCourse(Integer.parseInt(request.getParameter("id")));

            if (course != null) { // Als database niet goedt werkte zal dit NULL zijn, of als de course niet bestaat(!)?
                request.setAttribute("course", course);
            } else {
                this.databaseError = true;
            }

            request.setAttribute("databaseError", this.databaseError);
            RequestDispatcher rd = request.getRequestDispatcher("/pages/editCourseConfig.jsp");
            rd.forward(request, response);
        }
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

        if (request.getParameter("id") != null) {
            DB db = DB.getInstance();
            Course course = db.getCourse(Integer.parseInt(request.getParameter("id")));
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

}