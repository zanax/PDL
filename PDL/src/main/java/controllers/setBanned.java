/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import connection.DB;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;

/**
 *
 * @author Niels
 */
@WebServlet(name = "setBanned", urlPatterns = {"/setBanned"})
public class setBanned extends HttpServlet {

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
        String url = "adminPanel";

        User user = null;

        user = (User) DB.getInstance().getEveryUser(Integer.parseInt(request.getParameter("id")));


        if (request.getParameter("id") != null) {

            if (user.isIsBanned()) {

                if (DB.getInstance().unBanUser(Integer.parseInt(request.getParameter("id"))) > 0) {
                    response.sendRedirect(url);
                    return;
                } else {
                    request.setAttribute("errors", "Oops! Something went wrong.");
                    url = "/pages/404.jsp";
                }
            } else {

                if (DB.getInstance().banUser(Integer.parseInt(request.getParameter("id"))) > 0) {
                    response.sendRedirect(url);
                    return;
                } else {
                    request.setAttribute("errors", "Oops! Something went wrong.");
                    url = "/pages/404.jsp";
                }
            }
        } else {
            request.setAttribute("errors", "Oops! Something went wrong.");
            url = "/pages/404.jsp";
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
