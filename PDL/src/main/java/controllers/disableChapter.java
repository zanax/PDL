package controllers;

import connection.DB;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Chapter;
import models.Helper;

@WebServlet(name = "disableChapter", urlPatterns = {"/disableChapter"})
public class disableChapter extends HttpServlet {

//    private List<String> errors;
//
//    public deleteCourse() {
//        this.errors = new ArrayList<String>();
//    }
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

        Chapter chapter = DB.getInstance().getChapter(Integer.parseInt(request.getParameter("id")), Helper.getLanguage(request.getSession()));

        request.setAttribute("chapter", chapter);

        RequestDispatcher rd = request.getRequestDispatcher("/pages/disableChapter.jsp");
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

        String chapter_nr = request.getParameter("id").trim();

        int chapter_id = Integer.parseInt(chapter_nr);

        System.out.println("dit is een chapter " + "String: " + chapter_nr + "int: " + chapter_id);

        DB.getInstance().disableChapter(chapter_id);

        RequestDispatcher rd = request.getRequestDispatcher("/pages/teacherPanel.jsp");
        rd.forward(request, response);
    }

}
