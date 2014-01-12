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
import models.User;

/**
 *
 * @author Maarten
 */
@WebServlet(name = "courseBanUser", urlPatterns = {"/courseBanUser"})
public class courseBanUser extends HttpServlet {

    private List<String> errors;

    public courseBanUser() {
        this.errors = new ArrayList<String>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.errors.clear();
        String url = "/pages/courseBanUser.jsp";
        if (Helper.isTeacher(request.getSession().getAttribute("user")) || Helper.isAdmin(request.getSession().getAttribute("user"))) {
            try {
                int courseID = Integer.parseInt(request.getParameter("courseID"));
                Course course = DB.getInstance().getCourse(courseID, Helper.getLanguage(request.getSession()));
                if(course != null) {
                    List<User> users = DB.getInstance().getCourseUsers(course.getId());
                    if(!users.isEmpty()) {
                        request.setAttribute("show", true);
                        request.setAttribute("course", course);
                        request.setAttribute("users", users);
                    } else {
                        request.setAttribute("errors", "Users not found.");
                    }
                } else {
                    request.setAttribute("errors", "Course not found.");
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
        String url = "/pages/courseBanUser.jsp";
        
        if (Helper.isTeacher(request.getSession().getAttribute("user")) || Helper.isAdmin(request.getSession().getAttribute("user"))) {
            try {
                int studentID = Integer.parseInt(request.getParameter("userID"));
                int courseID = Integer.parseInt(request.getParameter("courseID"));
                if(DB.getInstance().disenrollCourse(studentID, courseID)) {
                    request.setAttribute("success", true);
                } else {
                     request.setAttribute("errors", "Failed to ban the User.");
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
