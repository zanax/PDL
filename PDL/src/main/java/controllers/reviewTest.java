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
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Question;
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
        // Nodig: testID
        if (request.getSession().getAttribute("user") != null) {
            if (request.getParameter("testID") != null) {
                try {
                    int testID = Integer.parseInt(request.getParameter("testID"));
                    Test test = DB.getInstance().getTest(testID);
                    if (test != null) {
                        List<Question> questions = DB.getInstance().getQuestions(testID);
                        if (questions != null) {
                            User user = (User) request.getSession().getAttribute("user");
                            Map<Integer, String> answers = DB.getInstance().getAnswers((int) user.getId(), testID);
                            request.setAttribute("student", user);
                            request.setAttribute("answers", answers);
                            request.setAttribute("questions", questions);
                            request.setAttribute("test", test);
                            request.setAttribute("show", true);
                        } else {
                            request.setAttribute("errors", "No Questions were found for this Test.");
                        }
                    } else {
                        request.setAttribute("errors", "The test doesn't excist.");
                    }
                } catch (NumberFormatException e) {
                }
            } else {
                request.setAttribute("errors", "TestID was unkown");
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