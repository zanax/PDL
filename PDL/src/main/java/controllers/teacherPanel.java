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
import models.Chapter;
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
            List<Course> courses = DB.getInstance().getCoursesIncludingNoTranslations(Helper.getLanguage(request.getSession()));
            request.setAttribute("courses", courses);
            
            //chapters ophalen
            List<Chapter> chapters = DB.getInstance().getChaptersIncludingNoTranslations(Helper.getLanguage(request.getSession()));
            request.setAttribute("chapters", chapters);

            //tests ophalen
            List<Test> tests = DB.getInstance().getTestsIncludingNoTranslations(Helper.getLanguage(request.getSession()));
            System.out.println(tests.size());
            request.setAttribute("tests", tests);

            //questions ophalen
            List<Question> questions = DB.getInstance().getQuestions();
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
