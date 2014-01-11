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
        if( ! Helper.isTeacher(request.getSession().getAttribute("user")) && ! Helper.isAdmin(request.getSession().getAttribute("user"))){
            this.errors.add("You do not have the correct permissions to visit this page.");
            request.setAttribute("errors", this.errors);
            url = "/pages/404.jsp";
        }
        else{
            Test test = DB.getInstance().getTest(Integer.parseInt(request.getParameter("id")), Helper.getLanguage(request.getSession()));
            request.setAttribute("test", test);
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.errors.clear();
        String test_nr = request.getParameter("test_id").trim();

        int test_id = Helper.isInt(test_nr);
        
        if(test_id != -1){
            DB.getInstance().disableTest(test_id);
        }
        else{
            this.errors.add("Invalid test.");
        }
        
        request.setAttribute("errors", errors);
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);

    }
}