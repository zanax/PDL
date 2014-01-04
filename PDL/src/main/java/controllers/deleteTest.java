/*
* To change this template, choose Tools | Templates
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
import models.Helper;
import models.Test;

@WebServlet(name = "deleteTest", urlPatterns = {"/deleteTest"})
public class deleteTest extends HttpServlet {
    private List<String> errors;
    
    public deleteTest(){
        this.errors = new ArrayList<String>();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.errors.clear();
        String url = "/pages/deleteTest.jsp";
        if( ! Helper.isTeacher(request.getSession().getAttribute("user"))){
            this.errors.add("You do not have the correct permissions to visit this page.");
            request.setAttribute("errors", this.errors);
            url = "/pages/404.jsp";
        }
        else{
            Test test = DB.getInstance().getTest(Integer.parseInt(request.getParameter("id")), Helper.getLanguage(request.getSession()));
            request.setAttribute("test", test);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/pages/deleteTest.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String test_nr = request.getParameter("test_id").trim();

        int test_id = Integer.parseInt(test_nr);

        System.out.println("dit is een grap " + "String: " + test_nr + "int: " + test_id);

        DB.getInstance().deleteTest(test_id);

        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);

    }
}