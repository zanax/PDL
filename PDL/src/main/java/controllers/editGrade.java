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
import models.Grade;
import models.Helper;
import models.Test;
import models.User;

/**
 *
 * @author Maarten
 */
@WebServlet(name = "editGrade", urlPatterns = {"/editGrade"})
public class editGrade extends HttpServlet {

    private List<String> errors;

    public editGrade() {
        this.errors = new ArrayList<String>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.errors.clear();
        String url = "/pages/editGrade.jsp";
        if (Helper.isTeacher(request.getSession().getAttribute("user"))) {
            try {
                int testID = Integer.parseInt(request.getParameter("testID"));
                int studentID = Integer.parseInt(request.getParameter("studentID"));
                User student = DB.getInstance().getUser(studentID);
                if (student != null) {
                    Test test = DB.getInstance().getTest(testID, Helper.getLanguage(request.getSession()));
                    if (test != null) {
                        Grade grade = DB.getInstance().getGrade(studentID, testID);
                        if (grade != null) {
                            request.setAttribute("show", true);
                            request.setAttribute("student", student);
                            request.setAttribute("test", test);
                            request.setAttribute("grade", grade);
                        } else {
                            request.setAttribute("errors", "Grade not found");
                        }
                    } else {
                        request.setAttribute("errors", "Test not found");
                    }
                } else {
                    request.setAttribute("errors", "Student not found");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("errors", "Failed to get the fields.");
            }
        } else {
            this.errors.add("You do not have the correct permissions to visit this page.");
            request.setAttribute("errors", this.errors);
            url = "/pages/404.jsp";
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.errors.clear();
        String url = "/pages/editGrade.jsp";
        
        if (Helper.isTeacher(request.getSession().getAttribute("user"))) {
            try {
                int testID = Integer.parseInt(request.getParameter("testID"));
                int studentID = Integer.parseInt(request.getParameter("studentID"));
                int editedGrade = Integer.parseInt(request.getParameter("grade"));
                Grade grade = new Grade();
                grade.setTestId(testID);
                grade.setGrade(editedGrade);
                grade.setUserId(studentID);
                if (DB.getInstance().updateGrade(grade)) {
                    request.setAttribute("success", true);
                } else {
                    request.setAttribute("errors", "Failed to save the Grade");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("errors", "Failed to get the fields.");
            }
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
