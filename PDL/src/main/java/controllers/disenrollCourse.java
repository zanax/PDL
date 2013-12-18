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
            User user = (User) request.getSession().getAttribute("user");
            List<Course> courses = DB.getInstance().getUserCourses(user);
            if (!courses.isEmpty()) {
                request.setAttribute("show", true);
                request.setAttribute("courses", courses);
            } else {
                request.setAttribute("errors", "You have no Courses to disenroll to");
            }
        } else {
            request.setAttribute("errors", "You have not the right permissions");
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
            List<Course> courses = DB.getInstance().getCourses();
            if (!courses.isEmpty()) {
                if (request.getParameter("id") != null) {
                    if (request.getParameter("agree") != null) {
                        int course_id = Integer.parseInt(request.getParameter("id"));
                        long user_id = ((User) request.getSession().getAttribute("user")).getId();
                        //if () {
                        DB.getInstance().disenrollCourse(user_id, course_id);
                        request.setAttribute("success", true);

                        DB.getInstance().amountMinusOne(course_id);
                        System.out.print(course_id);
                        RequestDispatcher rd = request.getRequestDispatcher("/pages/disenrollCourse.jsp"); // TEMP
                        rd.forward(request, response);
                        return;

                        //} else {
                        //    request.setAttribute("errors", "There were some problems with the Database");
                    //}
                } else {
                    request.setAttribute("errors", "You did not agree to disenroll from the Course");
                }
            } else {
                request.setAttribute("errors", "You did not choose a Course to disenroll from");
            }
            request.setAttribute("courses", courses);
            request.setAttribute("show", true);
        } else {
            request.setAttribute("errors", "You have no Courses to disenroll to");
        }
    }

    
        else {
            request.setAttribute("errors", "You have not the right permissions");
    }
    RequestDispatcher rd = request.getRequestDispatcher("/pages/disenrollCourse.jsp");

    rd.forward (request, response);
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
