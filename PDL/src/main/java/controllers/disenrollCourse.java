/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import connection.DB;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Course;
import models.Helper;
import models.User;

/**
 *
 * @author Zanax
 */
@WebServlet(name = "disenrollCourse", urlPatterns = {"/disenrollCourse"})
public class disenrollCourse extends HttpServlet {

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
        if (request.getSession().getAttribute("user") != null) {
            try {
                int courseID = Integer.parseInt(request.getParameter("courseID"));
                Course course = DB.getInstance().getCourse(courseID, Helper.getLanguage(request.getSession()));
                if (course != null) {
                    request.setAttribute("course", course);
                    request.setAttribute("show", true);
                } else {
                    request.setAttribute("errors", "Failed to get fields.");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errors", "Failed to get fields.");
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("/pages/disenrollCourse.jsp");
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
            try {
                int courseID = Integer.parseInt(request.getParameter("courseID"));
                if (DB.getInstance().disenrollCourse(((User) request.getSession().getAttribute("user")).getId(), courseID)) {
                    DB.getInstance().amountMinusOne(courseID);

                    request.setAttribute("courseID", courseID);
                    request.setAttribute("success", true);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errors", "Failed to get fields.");
            }
        } else {
            request.setAttribute("errors", "You have not the right permissions");
        }
        request.getRequestDispatcher("/pages/disenrollCourse.jsp").forward(request, response); // Verwijst terug naar de page
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
