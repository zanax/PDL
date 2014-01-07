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
import models.User;

/**
 *
 * @author Zanax
 */
@WebServlet(name = "changeUser", urlPatterns = {"/changeUser"})
public class changeUser extends HttpServlet {

    private final List<String> errors;
    User user = null;

    public changeUser() {
        this.errors = new ArrayList<String>();
    }

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
        String url = "/pages/changeUser.jsp";

        if (!Helper.isAdmin(request.getSession().getAttribute("user"))) {
            this.errors.add("You do not have the correct permissions to visit this page.");
            request.setAttribute("errors", this.errors);

            url = "/pages/404.jsp";
        } else {
            user = (User) DB.getInstance().getEveryUser(Integer.parseInt(request.getParameter("id")));

            if (user != null) {
                request.setAttribute("user", user);
                request.setAttribute("show", true);
            } else {
                request.setAttribute("errors", "Something went wrong with the database.");
                url = "/pages/404.jsp";
            }
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
        if (request.getSession().getAttribute("user") != null) {
            this.errors.clear();

            // ID
            String permission = null;
            if (!request.getParameter("permission").equals("")) {
                permission = request.getParameter("permission");
            } else {
                this.errors.add("\"User type\" is a required field.");
            }

            // Check
            if (this.errors.isEmpty()) {

                if (permission != null && permission.equals("student")) {

                    int affected_rows = DB.getInstance().makeStudent((int) user.getId());

                    if (affected_rows > 0) {

                        request.setAttribute("success", true);

                    } else {
                        this.errors.add("Something went wrong when editing the user, please try again.");
                        request.setAttribute("errors", this.errors);
                        request.setAttribute("show", true);
                    }

                } else if (permission != null && permission.equals("admin")) {

                    int affected_rows = DB.getInstance().makeAdmin((int) user.getId());

                    if (affected_rows > 0) {

                        request.setAttribute("success", true);

                    } else {
                        this.errors.add("Something went wrong when editing the user, please try again.");
                        request.setAttribute("errors", this.errors);
                        request.setAttribute("show", true);
                    }

                } else if (permission != null && permission.equals("teacher")) {

                    int affected_rows = DB.getInstance().makeTeacher((int) user.getId());

                    if (affected_rows > 0) {

                        request.setAttribute("success", true);

                    } else {
                        this.errors.add("Something went wrong when editing the user, please try again.");
                        request.setAttribute("errors", this.errors);
                        request.setAttribute("show", true);
                    }
                }
                
                request.getRequestDispatcher("/pages/changeUser.jsp").forward(request, response); // Verwijst terug naar de page

                //request.getRequestDispatcher("Payment").forward(request, response); // Verwijst terug naar de page
                return;
            } else {

                request.setAttribute("errors", this.errors);
            }
            request.setAttribute("show", true);
        } else {
            request.setAttribute("errors", "You have not the right permissions");
        }
        request.getRequestDispatcher("/pages/changeUser.jsp").forward(request, response); // Verwijst terug naar de page
    }

    /**
     * Returns a short description of the servlet.
     *     
* @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
