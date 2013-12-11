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
import models.Test;
import models.User;

/**
 *
 * @author Zanax
 */
@WebServlet(name = "reviewTest", urlPatterns = {"/reviewTest"})
public class reviewTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            User user = (User) request.getSession().getAttribute("user");
            List<Test> tests = DB.getInstance().getUserTests(user);
            if(!tests.isEmpty()) {
                request.setAttribute("tests", tests);
                request.setAttribute("show", true);
            } else {
                request.setAttribute("errors", "You have no Tests to review");
            }
        } else {
            request.setAttribute("errors", "You have not the right permissions");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/pages/reviewTest.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
