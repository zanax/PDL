/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import connection.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;

/**
 *
 * @author Bono
 */
@WebServlet(name = "register", urlPatterns = {"/register"})
public class register extends HttpServlet {
    private DatabaseConnection connection;
    private List<String> errors;
    private boolean success = false;
    
    public register(){
        this.connection = new DatabaseConnection();
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
        String gender = request.getParameter("gender");
        String firstname = request.getParameter("firstname");
        String surname = request.getParameter("surname");
        String address = request.getParameter("address");
        String zipcode = request.getParameter("zipcode");
        String password = request.getParameter("password");
        String password_confirm = request.getParameter("confirm_password");
        String email = request.getParameter("email");
        String city_id = request.getParameter("city");
        String country_id = request.getParameter("country");
        String language_id = request.getParameter("language");
        
        if(gender.equals("") || ! (gender.equals("m") || gender.equals("f")))   this.errors.add("");
        if(firstname.equals(""))                                                this.errors.add("");
        if(surname.equals(""))                                                  this.errors.add("");
        if(address.equals(""))                                                  this.errors.add("");
        if(zipcode.equals(""))                                                  this.errors.add("");
        if(password.equals(""))                                                 this.errors.add("");
        if( ! password_confirm.equals(password))                                this.errors.add("");
        if(email.equals("") /*TODO: email regex*/)                              this.errors.add("");
        if(city_id.equals("") || ! isInt(city_id))                              this.errors.add("");
        if(country_id.equals("") || ! isInt(country_id))                        this.errors.add("");
        if(language_id.equals("") || ! isInt(language_id))                      this.errors.add("");
        
        if(errors.isEmpty()){
            User user = new User();
            user.setFirstname(request.getParameter("firstname"));
            user.setSurname(request.getParameter("surname"));
            user.setAddress(request.getParameter("address"));
            user.setZipcode(request.getParameter("zipcode"));
            user.setPassword(request.getParameter("password"));
            user.setEmail(request.getParameter("email"));
    //        user.setCity(request.getParameter("city"));
    //        user.setCountry(request.getParameter("country"));
    //        user.setLanguage(request.getParameter("language"));
    //        user.setGender(request.getParameter("gender"));
            
            //query
            this.success = true;
        }
        else{
            request.setAttribute("errors", this.errors);
        }
        
        request.setAttribute("success", this.success);
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
    
    public boolean isInt(String string){
        try{
            Integer.parseInt(string);
        }
        catch(NumberFormatException e){
            return false;
        }
        
        return true;
    }
}
