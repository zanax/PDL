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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Course;
import models.User;

/**
 *
 * @author Zanax
 */
@WebServlet(name = "enrollCourse", urlPatterns = {"/enrollCourse"})
public class enrollCourse extends HttpServlet {

    private final List<String> errors;

    public enrollCourse() {
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
        if (request.getSession().getAttribute("user") != null) {
            User user = (User) request.getSession().getAttribute("user");
            List<Course> courses = DB.getInstance().getCourses();
            courses.removeAll(DB.getInstance().getUserCourses(user));
            if (!courses.isEmpty()) {
                request.setAttribute("show", true);
                request.setAttribute("courses", courses);
            } else {
                request.setAttribute("errors", "You have no Courses to enroll to");
            }
        } else {
            request.setAttribute("errors", "You have not the right permissions");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/pages/enrollCourse.jsp");
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
            this.errors.clear();

            // ID
            int id = 0;
            if (!request.getParameter("id").equals("")) {
                id = Integer.parseInt(request.getParameter("id"));
            } else {
                this.errors.add("\"Course\" is a required field.");
            }

            // Payment Method
            String method = request.getParameter("paymentMethod");
            if (method == null) {
                this.errors.add("\"Payment Method\" is a required field.");
            }

            // Check
            if (this.errors.isEmpty()) {
                request.setAttribute("id", id);
                request.setAttribute("paymentMethod", method);
                DB.getInstance().enrollCourse(id, ((User) request.getSession().getAttribute("user")).getId());
                
                request.setAttribute("success", true); // TEMP
                
                request.getRequestDispatcher("/pages/enrollCourse.jsp").forward(request, response); // Verwijst terug naar de page
                
                //request.getRequestDispatcher("Payment").forward(request, response); // Verwijst terug naar de page
                return;
            } else {
                User user = (User) request.getSession().getAttribute("user");
                List<Course> courses = DB.getInstance().getCourses();
                courses.removeAll(DB.getInstance().getUserCourses(user));
                request.setAttribute("courses", courses);
                request.setAttribute("errors", this.errors);
            }
            request.setAttribute("show", true);
        } else {
            request.setAttribute("errors", "You have not the right permissions");
        }
        request.getRequestDispatcher("/pages/enrollCourse.jsp").forward(request, response); // Verwijst terug naar de page
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
