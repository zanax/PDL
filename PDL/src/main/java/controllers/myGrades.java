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
import models.Grade;
import models.Helper;
import models.Test;
import models.User;

/**
 *
 * @author Gijs
 */
@WebServlet(name = "myGrades", urlPatterns = {"/myGrades"})
public class myGrades extends HttpServlet {

    private List<String> errors;

    public myGrades() {
        this.errors = new ArrayList<String>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO: Check if teacher
        this.errors.clear();
        String url = "/pages/myGrades.jsp";

        if (!Helper.isStudent(request.getSession().getAttribute("user"))) {
            this.errors.add("You do not have the correct permissions to visit this page.");
            request.setAttribute("errors", this.errors);

            url = "/pages/404.jsp";
        } else {

            //cijfers ophalen
            
            List<Grade> grades = DB.getInstance().getGrades((User) request.getSession().getAttribute("user"));
            request.setAttribute("grades", grades);

            //courses ophalen
            
            //ArrayList<Course> courses = DB.getInstance().getCourses();    //All courses
            //request.setAttribute("courses", courses);

            //tests ophalen
            
            ArrayList<Test> tests = DB.getInstance().getTests();          //All tests
            request.setAttribute("tests", tests);

            //System.out.println(grades);
            //System.out.println(courses);
            //System.out.println(tests);

        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
