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

/**
 *
 * @author Bono
 */
@WebServlet(name = "teacherPanel", urlPatterns = {"/teacherPanel"})
public class teacherPanel extends HttpServlet {

    private List<String> errors;

    public teacherPanel() {
        this.errors = new ArrayList<String>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO: Check if teacher
        this.errors.clear();
        String url = "/pages/teacherPanel.jsp";

        if (!Helper.isTeacher(request.getSession().getAttribute("user"))) {
            this.errors.add("You do not have the correct permissions to visit this page.");
            request.setAttribute("errors", this.errors);

            url = "/pages/404.jsp";
        } else {
            //courses ophalen
            ArrayList<Course> courses = DB.getInstance().getCourses(Helper.getLanguage(request.getSession()));
            request.setAttribute("courses", courses);

            //tests ophalen
            ArrayList<Test> tests = DB.getInstance().getTests(Helper.getLanguage(request.getSession()));
            request.setAttribute("tests", tests);

            //questions ophalen
            ArrayList<Question> questions = DB.getInstance().getQuestions();
            request.setAttribute("questions", questions);
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
