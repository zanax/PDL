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
import models.Test;

@WebServlet(name = "editChapter", urlPatterns = {"/editChapter"})
public class editChapter extends HttpServlet {

    private List<String> errors;
    private boolean success = false;

    public editChapter() {
        this.errors = new ArrayList<String>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.errors.clear();
        String url = "/pages/editChapter.jsp";

        if (!Helper.isTeacher(request.getSession().getAttribute("user")) && !Helper.isAdmin(request.getSession().getAttribute("user"))) {
            this.errors.add("You do not have the correct permissions to visit this page.");
            request.setAttribute("errors", this.errors);
            url = "/pages/404.jsp";
        } else {
            int chapter_id = Helper.isInt(request.getParameter("id"));

            Chapter chapter = null;
            if (chapter_id > -1) {
                chapter = DB.getInstance().getChapter(chapter_id, Helper.getLanguage(request.getSession()));

                if (chapter == null) {
                    this.errors.add("The requested chapter does not exist.");
                }
            } else {
                this.errors.add("The requested chapter does not exist.");
            }

            request.setAttribute("chapter", chapter);
            if (!this.errors.isEmpty()) {
                request.setAttribute("errors", this.errors);
            }

            //Courses ophalen voor form
            ArrayList<Course> courses = DB.getInstance().getCourses(Helper.getLanguage(request.getSession()));
            request.setAttribute("courses", courses);

            url = "/pages/editChapter.jsp";
            if (!errors.isEmpty()) {
                url = "/pages/createChapter.jsp";
            }
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
        String chapter_content = request.getParameter("chapter_content").trim();
        String videoUrl = request.getParameter("videoUrl").trim();

        String s_language = request.getParameter("language_id");
        int id = Helper.isInt(request.getParameter("id"));
        int language = Helper.isInt(s_language);

        //String language_id = request.getParameter("language_id");
        this.errors.clear();

        //this.success = false;
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

        String url = "/pages/editChapter.jsp";
        if (!(id > 0)) {
            url = "createChapter";
        }

        if (errors.isEmpty()) {
            Chapter chapter = new Chapter(id);
            chapter.setCourse_id(int_course_id);
            chapter.setChapterName(chapterName);
            chapter.setChapter_description(chapter_description);
            chapter.setChapter_content(chapter_content);
            chapter.setVideoUrl(videoUrl);

            chapter.setLanguage(language);

            int affected_rows = DB.getInstance().updateChapter(chapter);

            if (affected_rows > 0) {
                this.success = true;
                request.setAttribute("chapter", chapter);
            } else {
                this.errors.add("Something went wrong with saving the chapter. Please try again.");
            }
        }

        request.setAttribute("courses", DB.getInstance().getCourses(Helper.getLanguage(request.getSession())));
        request.setAttribute("success", this.success);
        request.setAttribute("errors", this.errors);

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
