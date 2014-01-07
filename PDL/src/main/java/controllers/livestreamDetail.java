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
import models.User;

/**
 *
 * @author Bono
 */
@WebServlet(name = "livestreamDetail", urlPatterns = {"/livestreamDetail"})
public class livestreamDetail extends HttpServlet {
    private List<String> errors;

    public livestreamDetail() {
        this.errors = new ArrayList<String>();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        this.errors.clear();
        String url = "pages/livestreamDetail.jsp";
        User user = (User) request.getSession().getAttribute("user");
        
        if(user == null){
            this.errors.add("You do not have the correct permissions to visit this page.");
            url = "/pages/404.jsp";
        }
        
        String parameter_id = request.getParameter("id");
        int id = Helper.isInt(parameter_id);
        
        if(id == -1){
            this.errors.add("Invalid course.");
            url = "/pages/404.jsp";
        }
        
        Course course = DB.getInstance().getCourse(id, Helper.getLanguage(request.getSession()));
        if(course != null){
            request.setAttribute("course", course);
        }
        else{
            this.errors.add("Course not found.");
            url = "/pages/404.jsp";
        }
        
        if( ! errors.isEmpty()){
            request.setAttribute("errors", this.errors);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
