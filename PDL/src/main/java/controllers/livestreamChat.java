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

/**
 *
 * @author Bono
 */
@WebServlet(name = "livestreamChat", urlPatterns = {"/livestreamChat"})
public class livestreamChat extends HttpServlet {
    private List<String> errors;

    public livestreamChat() {
        this.errors = new ArrayList<String>();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.errors.clear();
        
        String url = "pages/livestreamChat.jsp";
        if(request.getSession().getAttribute("user") == null){
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
        
    }
}
