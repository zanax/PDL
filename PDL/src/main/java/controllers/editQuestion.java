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
import models.Question;
import models.Test;

@WebServlet(name = "editQuestion", urlPatterns = {"/editQuestion"})
public class editQuestion extends HttpServlet {

    private List<String> errors;
    private boolean success = false;

    public editQuestion() {
        this.errors = new ArrayList<String>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.errors.clear();
        String url = "/pages/createQuestion.jsp";
        if (!Helper.isTeacher(request.getSession().getAttribute("user")) && !Helper.isAdmin(request.getSession().getAttribute("user"))) {
            this.errors.add("You do not have the correct permissions to visit this page.");
            request.setAttribute("errors", this.errors);
            url = "/pages/404.jsp";
        } else {
            int question_id = Helper.isInt(request.getParameter("id"));
            Question questionObj = null;
            if (question_id > -1) {
                questionObj = DB.getInstance().getQuestion(question_id);

                if (questionObj == null) {
                    this.errors.add("The requested question does not exist.");
                }
            } else {
                this.errors.add("The requested question does not exist.");
            }

            request.setAttribute("questionObj", questionObj);
            if (!this.errors.isEmpty()) {
                request.setAttribute("errors", this.errors);
            }

            //Tests ophalen voor form
            List<Test> tests = DB.getInstance().getTests(Helper.getLanguage(request.getSession()));
            request.setAttribute("tests", tests);

            url = "/pages/editQuestion.jsp";
            if (!errors.isEmpty()) {
                url = "/pages/createQuestion.jsp";
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
              
        String question = request.getParameter("question");
        String correctAnswer = request.getParameter("correctAnswer");
        String answer1 = request.getParameter("answer1");
        String answer2 = request.getParameter("answer2");
        String answer3 = request.getParameter("answer3");
        String test = request.getParameter("test");
        int id = Helper.isInt(request.getParameter("id"));
        this.errors.clear();
        this.success = false;


        //TODO: check if start/end dates are correct (Date object?)

        if (question.equals("")) {
            this.errors.add("\"Question\" is a required field.");
        }
        if (correctAnswer.equals("")) {
            this.errors.add("\"Correct answer\" is a required field.");
        }

        Test testje = null;
        int int_test_id = Helper.isInt(test);
        if (int_test_id > 0) {
            testje = DB.getInstance().getTest(int_test_id, Helper.getLanguage(request.getSession()));
            if (testje == null) {
                this.errors.add("Selected test does not exist");
            }
        } else {
            this.errors.add("Selected test does not exist.");
        }

        String url = "/pages/editQuestion.jsp";

        if (!(id > 0)) {
            url = "createQuestion";
        }

        if (errors.isEmpty()) {
            Question questionObj = new Question(id);
            questionObj.setQuestion(question);
            questionObj.setCorrectAnswer(correctAnswer);
            questionObj.setAnswer1(answer1);
            questionObj.setAnswer2(answer2);
            questionObj.setAnswer3(answer3);
            questionObj.setTestId(int_test_id);
            questionObj.setQuestion(question);

            DB.getInstance().updateQuestion(questionObj);

                this.success = true;
                request.setAttribute("test", test);

        }

        request.setAttribute("tests", DB.getInstance().getTests(Helper.getLanguage(request.getSession())));
        request.setAttribute("success", this.success);
        request.setAttribute("errors", this.errors);

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}