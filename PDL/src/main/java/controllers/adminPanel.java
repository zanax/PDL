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
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Course;
import models.Helper;
import models.Question;
import models.Test;
import models.User;

/**
 *
 * @author Bono
 */
@WebServlet(name = "adminPanel", urlPatterns = {"/adminPanel"})
public class adminPanel extends HttpServlet {

    private List<String> errors;

    public adminPanel() {
        this.errors = new ArrayList<String>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO: Check if teacher
        this.errors.clear();
        String url = "/pages/adminPanel.jsp";
        

        if (!Helper.isAdmin(request.getSession().getAttribute("user"))) {
            this.errors.add("You do not have the correct permissions to visit this page.");
            request.setAttribute("errors", this.errors);

            url = "/pages/404.jsp";
        } else {
            //courses ophalen
            ArrayList<Course> courses = DB.getInstance().getCourses();
            request.setAttribute("courses", courses);

            //tests ophalen
            ArrayList<Test> tests = DB.getInstance().getTests();
            request.setAttribute("tests", tests);

            //questions ophalen
            ArrayList<Question> questions = DB.getInstance().getQuestions();
            request.setAttribute("questions", questions);
            
            //questions ophalen
            ArrayList<User> users = DB.getInstance().getUsers();
            request.setAttribute("users", users);
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}