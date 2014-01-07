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
@WebServlet(name = "myCourses", urlPatterns = {"/myCourses"})
public class myCourses extends HttpServlet {
    private List<String> errors;

    public myCourses() {
        this.errors = new ArrayList<String>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.errors.clear();
        String url = "/pages/myCourses.jsp";
        
        if ( !Helper.isStudent(request.getSession().getAttribute("user"))) {
            this.errors.add("You do not have the correct permissions to visit this page.");
            request.setAttribute("errors", this.errors);
            url = "/pages/404.jsp";
        } else {

            List<Course> courses = DB.getInstance().getUserCourses((User) request.getSession().getAttribute("user"), Helper.getLanguage(request.getSession()));
            
            if(courses.isEmpty()){
                //TODO: Toevoegen aan errors arraylist i.p.v. request attribute!
                request.setAttribute("errors", "You are not subscribed to any courses");
            }
           
            request.setAttribute("courses", courses);
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
