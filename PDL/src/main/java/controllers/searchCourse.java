/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author Niels
 */
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
import models.Helper;
import models.User;

/**
 *
 * @author Zanax & Donna
 */
@WebServlet(name = "searchCourse", urlPatterns = {"/searchCourses"})
public class searchCourse extends HttpServlet {
    
    public searchCourse() {
        
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
        
        
        String criteria = request.getParameter("keyword");
        

        List<Course> courses = DB.getInstance().searchCourses(criteria, Helper.getLanguage(request.getSession()));
        
        if(courses.isEmpty()){
            request.setAttribute("errors", "There are no courses matching your keyword(s)");
        }

        request.setAttribute("courses", courses);

        RequestDispatcher rd = request.getRequestDispatcher("/pages/searchCourse.jsp");
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
    }
}

