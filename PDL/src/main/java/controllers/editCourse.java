/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import connection.DB;
import java.io.IOException;
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
@WebServlet(name = "editCourse", urlPatterns = {"/editCourse"})
public class editCourse extends HttpServlet {

    private boolean databaseError = false;

    public editCourse() {

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

        // IF-Statement als de User en Teacher is
        
        if (request.getParameter("id") != null) {
            DB db = new DB();
            Course course = db.getCourse(Integer.parseInt(request.getParameter("id")));
            
            if (course != null) { // Als database niet goedt werkte zal dit NULL zijn
                request.setAttribute("course", course);
            } else {
               this.databaseError = true;
            }
            
            request.setAttribute("databaseError", this.databaseError);
            RequestDispatcher rd = request.getRequestDispatcher("/pages/editCourse.jsp");
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

//        request.setAttribute("success", this.success);
//        RequestDispatcher rd = request.getRequestDispatcher("/pages/register.jsp");
//        rd.forward(request, response);
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

    public boolean isInt(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}

/**
 *
 *
 * if (request.getParameter("id") != null) { // // Session // long ID =
 * Long.parseLong(request.getParameter("ID")); // Session session =
 * HibernateUtil.getSessionFactory().getCurrentSession(); // Transaction tx =
 * session.beginTransaction(); // Course managedCourse = (Course)
 * session.load(Course.class, ID); // tx.commit(); // // Request
 * request.setAttribute("id", 1); // request.setAttribute("name",
 * managedCourse.getName()); // request.setAttribute("description",
 * managedCourse.getDescription()); RequestDispatcher rd =
 * request.getRequestDispatcher("/pages/editCourse.jsp"); rd.forward(request,
 * response); }
 *
 *
 */
