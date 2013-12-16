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
import models.Helper;
import models.Teacher;

/**
 *
 * @author Zanax & Donna
 */
@WebServlet(name = "createCourse", urlPatterns = {"/createCourse"})
public class createCourse extends HttpServlet {

    private List<String> errors;

    public createCourse() {
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
        this.errors.clear();
        String url = "/pages/createCourse.jsp";
        if( ! Helper.isTeacher(request.getSession().getAttribute("user"))){
            this.errors.add("You do not have the correct permissions to visit this page.");
            request.setAttribute("errors", this.errors);
            url = "/pages/404.jsp";
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(url);
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
        if (request.getSession().getAttribute("user") instanceof Teacher) {
            // Course
            Course course = new Course();
            // Get parameters
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String maximumStudents = request.getParameter("maximumStudents");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String category = request.getParameter("category");
            this.errors.clear();
            // Name
            if (name.equals("")) {
                this.errors.add("\"Name\" is a required field.");
            } else {
                course.setName(name);
            }
            // Maximm Students
            if (!maximumStudents.equals("")) {
                try {
                    course.setMaximumStudents(Integer.parseInt(maximumStudents));
                } catch (NumberFormatException e) {
                    course.setMaximumStudents(0);
                    this.errors.add("\"Maximum Students\" only accepts numbers.");
                }
            }
            // Description
            if (description.equals("")) {
                this.errors.add("\"Description\" is a required field.");
            } else {
                course.setDescription(description);
            }
            // Start Date
            if (!startDate.equals("")) {
                course.setStartDate(null);
            }
            // End Date
            if (!endDate.equals("")) {
                course.setEndDate(null);
            }
            // Category
            if (category.equals("")) {
                this.errors.add("\"Category\" is a required field.");
            } else {
                course.setCategory(category);
            }
            // Error Check
            if (errors.isEmpty()) {
                int id = DB.getInstance().insertCourse(course);
                if (id != -1) {
                    request.setAttribute("createdCourse", name);
                    request.setAttribute("success", true);
                } else {
                    request.setAttribute("errors", "Something went wrong with the Database.");
                }
            } else {
                request.setAttribute("course", course);
                request.setAttribute("errors", this.errors);
            }
            request.setAttribute("show", true);
        } else {
            request.setAttribute("errors", "You have not the permission to Create a Course.");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/pages/createCourse.jsp");
        rd.forward(request, response);
    }
}
