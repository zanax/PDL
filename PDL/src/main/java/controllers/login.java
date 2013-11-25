/*
 * To change this template, choose Tools | Templates
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
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author Bono
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/pages/login.jsp");
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
        List<String> errors = new ArrayList<String>();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean success = false;
        String url = "/pages/login.jsp";
        
        if(email.trim().equals("")) errors.add("\"E-mail\" is a required field.");
        if(password.trim().equals("")) errors.add("\"Password\" is a required field.");
        
        //TODO: Zoek naar email in db, fetch user
        if(errors.isEmpty()){
            DB db = DB.getInstance();
            User user = db.getUser(email);
            
            if(user == null){
                errors.add("Wrong e-mail or password.");
            }
            else if( ! password.equals(user.getPassword())){
                errors.add("Wrong e-mail or password");
            }

            if(errors.isEmpty()){
                //ingelogd - set session
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                
                url = "index.jsp";
                success = true;
            }
            else{
                request.setAttribute("errors", errors);
            }
        }
        else{
            request.setAttribute("errors", errors);
        }
        
        request.setAttribute("success", success);
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}