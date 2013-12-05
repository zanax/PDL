/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import connection.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Course;
import models.Test;

/**
 *
 * @author Bono
 */
@WebServlet(name = "teacherPanel", urlPatterns = {"/teacherPanel"})
public class teacherPanel extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO: Check if teacher
        
        //courses ophalen
        ArrayList<Course> courses = DB.getInstance().getCourses();
        request.setAttribute("courses", courses);
        
        //tests ophalen
        ArrayList<Test> tests = DB.getInstance().getTests();
        request.setAttribute("tests", tests);
        
        RequestDispatcher rd = request.getRequestDispatcher("/pages/teacherPanel.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
