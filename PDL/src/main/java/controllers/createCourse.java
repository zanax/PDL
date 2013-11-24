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
import javax.servlet.http.HttpSession;
import models.Course;
import models.Teacher;
import models.User;

/**
 *
 * @author Zanax & Donna
 */
@WebServlet(name = "createCourse", urlPatterns = {"/createCourse"})
public class createCourse extends HttpServlet {
    private DB connection;
    private List<String> errors;
    private boolean success = false;
    private boolean databaseError = false;
    
    public createCourse(){
        this.connection = new DB();
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
        // IF-Statement als de User en Teacher is
        RequestDispatcher rd = request.getRequestDispatcher("/pages/createCourse.jsp");
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
        
        // TODO: Check of de User wel een Teacher is.
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        if(user == null || !(user instanceof Teacher)) { return; }
        
        if (request.getParameter("Create") != null) { // Check of create button is geklikt
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String category = request.getParameter("category");

            this.errors.clear();
            this.success = false;

            //Are required fields empty or have they gotten wrong values?
            if (name.equals("")) {
                this.errors.add("\"Name\" is a required field.");
            }
            if (description.equals("")) {
                this.errors.add("\"Description\" is a required field.");
            }
            if (category.equals("")) {
                this.errors.add("\"Category\" is a required field.");
            }

            if (errors.isEmpty()) {
                // Session
                DB db = new DB();
                // Course
                Course course = new Course();
                course.setName(name);
                course.setDescription(description);
                course.setCategory(category);
                //course.setHeadTeacher(null); // get the Teacher
                // Query
                int id = db.insertCourse(course);
                if(id != -1) { // Als database niet goed werkte al dit -1 zijn
                    request.setAttribute("id", id);
                    request.setAttribute("name", name);
                    this.success = true;
                } else {
                    this.databaseError = false;
                }
            } else {
                request.setAttribute("name", name); // TODO: Moet nog wel een ander systeem voor zijn, Voor als er iets fout is ingevuld dat de rest wel teruggezet moet worden
                request.setAttribute("description", description);
                request.setAttribute("category", category);
                request.setAttribute("errors", this.errors);
            }

            request.setAttribute("databaseError", this.databaseError);
            request.setAttribute("success", this.success);
            RequestDispatcher rd = request.getRequestDispatcher("/pages/createCourse.jsp");
            rd.forward(request, response);
        }
    }
}