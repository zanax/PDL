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
import models.Helper;
import models.User;

/**
 *
 * @author Bono
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {
    
        private List<String> errors;
        
        
        public login(){
            
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
        
        this.errors.clear();
        
        String url = "/pages/login.jsp";    
        
        if(request.getSession().getAttribute("user")!=null){
            
            this.errors.add("You are already logged in.");
            request.setAttribute("errors", this.errors);
            
            url = "/pages/404.jsp";
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(url);
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
        List<String> errors = new ArrayList<String>();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean success = false;
        String url = "/pages/login.jsp";

        if (email.trim().equals("")) {
            errors.add("\"E-mail\" is a required field.");
        }
        if (password.trim().equals("")) {
            errors.add("\"Password\" is a required field.");
        }

        //TODO: Zoek naar email in db, fetch user
        if (errors.isEmpty()) {

            password = register.md5(password);

            DB db = DB.getInstance();
            User user = db.getEveryUser(email);

            if (user == null) {
                errors.add("Wrong e-mail or password.");
            } else if (!password.equals(user.getPassword())) {
                errors.add("Wrong e-mail or password");
            } else if (user.isIsBanned()) {
                errors.add("You are banned, contact the site's administrator for more info");
            }

            if (errors.isEmpty()) {
                //ingelogd - set session
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                Helper.setLanguage(user.getLanguage(), request);

                url = "/index.jsp";
                success = true;
            } else {
                request.setAttribute("errors", errors);
            }
        } else {
            request.setAttribute("errors", errors);
        }

        request.setAttribute("success", success);
        RequestDispatcher rdIndex = request.getRequestDispatcher(url);
        rdIndex.forward(request, response);
    }
}
