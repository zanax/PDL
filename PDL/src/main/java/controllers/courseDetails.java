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
import models.Chapter;
import models.Course;
import models.Helper;
import models.Test;
import models.User;

/**
 *
 * @author Zanax
 */
@WebServlet(name = "courseDetails", urlPatterns = {"/courseDetails"})
public class courseDetails extends HttpServlet {

    private List<String> errors;

    public courseDetails() {
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
        if (request.getParameter("id") != null) {
            try {
                Course course = DB.getInstance().getCourse(Integer.parseInt(request.getParameter("id")), Helper.getLanguage(request.getSession()));
                List<Chapter> chapters = DB.getInstance().getCourseChapters(Integer.parseInt(request.getParameter("id")), Helper.getLanguage(request.getSession()));
                List<Test> tests = DB.getInstance().getCourseTests(Integer.parseInt(request.getParameter("id")), Helper.getLanguage(request.getSession()));

                if (course != null) {
                    request.setAttribute("course", course);
                    request.setAttribute("chapters", chapters);
                    request.setAttribute("tests", tests);
                    request.setAttribute("show", true);

                    int course_id = course.getId();

                    if (request.getSession().getAttribute("user") != null) {

                        request.setAttribute("logged_in", true);

                        List<Course> subbed_courses = DB.getInstance().getUserCourses((User) request.getSession().getAttribute("user"), Helper.getLanguage(request.getSession()));

                        boolean enrolled = false;

                        if (!subbed_courses.isEmpty()) {
                            for (Course subbed_course : subbed_courses) {
                                if (subbed_course != null) {
                                    if (subbed_course.getId() == course_id) {
                                        enrolled = true;
                                        break;
                                    }
                                }
                            }
                        }

                        request.setAttribute("enrolled", enrolled);

                    } else {
                        request.setAttribute("logged_in", false);
                    }

                } else {
                    request.setAttribute("errors", "Something went wrong with the Database");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errors", "Failed to get field.");
            }
        } else {
            request.setAttribute("errors", "We missed the ID");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/pages/courseDetails.jsp");
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
