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
import models.Chapter;
import models.Course;
import models.Helper;


@WebServlet(name = "createChapter", urlPatterns = {"/createChapter"})
public class createChapter extends HttpServlet {

    private List<String> errors;
    private boolean success = false;

    public createChapter() {
        this.errors = new ArrayList<String>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.errors.clear();
        String url = "pages/createChapter.jsp";

        if (!Helper.isTeacher(request.getSession().getAttribute("user")) && !Helper.isAdmin(request.getSession().getAttribute("user"))) {
            this.errors.add("You do not have the correct permissions to visit this page.");
            request.setAttribute("errors", this.errors);
            url = "/pages/404.jsp";
        } else {
            List<Course> courses = DB.getInstance().getCourses(Helper.getLanguage(request.getSession()));
            request.setAttribute("courses", courses);
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String course_id = request.getParameter("course_id").trim();
        String chapterName = request.getParameter("chapterName").trim();
        String chapter_description = request.getParameter("chapter_description").trim();
        System.out.println("chapter_description: " + chapter_description);
        String chapter_content = request.getParameter("chapter_content").trim();
        String s_language = request.getParameter("language_id");
        int language = Helper.isInt(s_language);
        
        //String language_id = request.getParameter("language_id");
        this.errors.clear();
        this.success = false;
        //int int_language = Helper.isInt(language_id);

        if (course_id.equals("")) {
            this.errors.add("\"Course\" is a required field.");
        }
        if (chapterName.equals("")) {
            this.errors.add("\"Chapter name\" is a required field.");
        }
        if (chapter_description.equals("")) {
            this.errors.add("\"Chapter description\" is a required field.");
        }
        if (chapter_content.equals("")) {
            this.errors.add("\"Chapter content\" is a required field.");
        }
        if (!Helper.allowedLanguage(language)) {
            this.errors.add("Invalid language selected, please try again.");
        }

        Course course = null;
        int int_course_id = Helper.isInt(course_id);
        
        if (int_course_id > 0) {
            course = DB.getInstance().getCourse(int_course_id, Helper.getLanguage(request.getSession()));
            if (course == null) {
                this.errors.add("Selected course does not exist");
            }
        } else {
            this.errors.add("Selected course does not exist.");
        }

        String url = "/pages/createChapter.jsp";
        if (errors.isEmpty()) {
            Chapter chapter = new Chapter();
            chapter.setCourse_id(int_course_id);
            chapter.setChapterName(chapterName);
            chapter.setChapter_description(chapter_description);
            chapter.setChapter_content(chapter_content);            
            chapter.setLanguage(language);
            
            
            int chapter_id = DB.getInstance().insertChapter(chapter);

            System.out.println("chapter_id: " + chapter_id);
            
            if (chapter_id > 0) {
                this.success = true;
                url = "editChapter?id=" + chapter_id;
                response.sendRedirect(url);
                return;
            } else {
                this.errors.add("Something went wrong creating the test, please try again.");
                request.setAttribute("errors", this.errors);
            }
        } else {
            request.setAttribute("errors", this.errors);
            request.setAttribute("courses", DB.getInstance().getCourses(Helper.getLanguage(request.getSession())));
        }

        request.setAttribute("course_id", course_id);
        request.setAttribute("chapterName", chapterName);
        request.setAttribute("chapter_description", chapter_description);
        request.setAttribute("chapter_content", chapter_content);        
        request.setAttribute("success", this.success);

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
