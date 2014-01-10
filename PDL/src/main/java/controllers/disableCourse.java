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
import models.Helper;
import models.Teacher;

/**
 *
 * @author DMC asus
 */
@WebServlet(name = "disableCourse", urlPatterns = {"/disableCourse"})
public class disableCourse extends HttpServlet {

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
        
        Course course = DB.getInstance().getCourse(Integer.parseInt(request.getParameter("id")), Helper.getLanguage(request.getSession()));

        request.setAttribute("course", course);

        RequestDispatcher rd = request.getRequestDispatcher("/pages/disableCourse.jsp");
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
        
         String course_nr = request.getParameter("course_id").trim();

        int course_id = Integer.parseInt(course_nr);

        DB.getInstance().disableCourse(course_id, Helper.getLanguage(request.getSession()));
        
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
