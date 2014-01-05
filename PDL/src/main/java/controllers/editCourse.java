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
 * @author Zanax
 */
@WebServlet(name = "editCourse", urlPatterns = {"/editCourse"})
public class editCourse extends HttpServlet {

    private List<String> errors;

    public editCourse() {
        this.errors = new ArrayList<String>();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.errors.clear();
        String url = "/pages/editCourse.jsp";
        if( ! Helper.isTeacher(request.getSession().getAttribute("user"))){
            this.errors.add("You do not have the correct permissions to visit this page.");
            request.setAttribute("errors", this.errors);
            url = "/pages/404.jsp";
        }
        else if (request.getParameter("id") != null) {
            Course course = DB.getInstance().getCourse(Integer.parseInt(request.getParameter("id")), Helper.getLanguage(request.getSession()));
            
            if (course != null) {
                request.setAttribute("course", course);
                request.setAttribute("show", true);
            } else {
                request.setAttribute("errors", "The course could not be found. Please try again.");
            }
        } else {
            request.setAttribute("errors", "Something went wrong, please try again.");
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
            if (request.getParameter("id") != null) {
                // Course
                Course course = DB.getInstance().getCourse(Integer.parseInt(request.getParameter("id")), Helper.getLanguage(request.getSession()));
                // Get parameters
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String maximumStudents = request.getParameter("maximumStudents");
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");
                String category = request.getParameter("category");
                String s_language = request.getParameter("language_id");
                int language = Helper.isInt(s_language);
                
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
                if( ! Helper.allowedLanguage(language)){
                    this.errors.add("Invalid language selected. Please try again.");
                }
                else{
                    course.setLanguage(language);
                }
                
                // Error Check
                if (errors.isEmpty()) {
                    if (DB.getInstance().updateCourse(course)) {
                        request.setAttribute("editedCourse", name);
                        request.setAttribute("success", true);
                    } else {
                        request.setAttribute("errors", "Something went wrong with the database. Please try again or contact an administrator.");
                    }
                } else {
                    request.setAttribute("errors", this.errors);
                }
                request.setAttribute("course", course);
                request.setAttribute("show", true);
            } else {
                request.setAttribute("errors", "You don't have the correct permissions to edit a course.");
            }
        } else {
            request.setAttribute("errors", "You have not selected a course to edit");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/pages/editCourse.jsp");
        rd.forward(request, response);
    }
}
