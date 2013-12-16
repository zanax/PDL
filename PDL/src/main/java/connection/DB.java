package connection;

import models.Question;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import models.Chapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.crypto.Data;
import models.Course;
import models.Student;
import models.Teacher;
import models.Test;
import models.User;

public class DB {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://145.92.6.85:3306/three2learn";
    private static final String DBUSER = "pdlteam15";
    private static final String DBPASS = "89!3Ab.toor";
    private ResultSet result = null;
    private int affectedRows = -1;
    Connection conn = null;
    private static DB db = null;

    private DB() {
    }

    public static DB getInstance() {
        if (db == null) {
            db = new DB();
        }
        return db;
    }

    public void destroy() {
        db = null;
    }

    private void startConnection() {
        try {
            Class.forName(DRIVER);
            DriverManager.setLoginTimeout(5);
            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        conn = null;
    }

    public ResultSet performSelect(PreparedStatement query) {
        try {
            result = query.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int performUpdate(PreparedStatement pstmt) {
        try {
            affectedRows = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    public Connection getConnection() {
        return conn;
    }

    //
    // User
    public int insertUser(User user) {
        int id = -1;

        try {
            startConnection();

            String sql = "insert "
                    + "   into User(firstname, surname, address, zipcode, gender, email, banned, password, city, country, language_id)"
                    + "   values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)  ";
            PreparedStatement prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepared_statement.setString(1, user.getFirstname());
            prepared_statement.setString(2, user.getSurname());
            prepared_statement.setString(3, user.getAddress());
            prepared_statement.setString(4, user.getZipcode());
            prepared_statement.setString(5, String.valueOf(user.getGender()));
            prepared_statement.setString(6, user.getEmail());
            prepared_statement.setBoolean(7, false);
            prepared_statement.setString(8, user.getPassword());
            prepared_statement.setString(9, user.getCity());
            prepared_statement.setString(10, user.getCountry());
            prepared_statement.setInt(11, user.getLanguage());

            prepared_statement.execute();

            ResultSet generatedKeys = prepared_statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = (int) generatedKeys.getLong(1);
            }
            generatedKeys.close();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public int editUser(User user) {

        int affected_rows = 0;

        try {
            startConnection();

            String sql = "  update User "
                    + "     set firstname = ?, surname = ?, address = ?, zipcode = ?, gender = ?, email = ?, banned = ?, password = ?, city = ?, country = ?, language_id = ?"
                    + "     where "
                    + "     user_id = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setString(1, user.getFirstname());
            prepared_statement.setString(2, user.getSurname());
            prepared_statement.setString(3, user.getAddress());
            prepared_statement.setString(4, user.getZipcode());
            prepared_statement.setString(5, String.valueOf(user.getGender()));
            prepared_statement.setString(6, user.getEmail());
            prepared_statement.setBoolean(7, false);
            prepared_statement.setString(8, user.getPassword());
            prepared_statement.setString(9, user.getCity());
            prepared_statement.setString(10, user.getCountry());
            prepared_statement.setInt(11, user.getLanguage());
            prepared_statement.setLong(12, user.getId());

            affected_rows = prepared_statement.executeUpdate();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affected_rows;
    }

    public User getUser(String email) {
        User user = null;

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from "
                    + "         User"
                    + "     where "
                    + "         email = ?"
                    + "         and"
                    + "             banned = 0"
                    + "     limit 1";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setString(1, email);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                if (rs.getBoolean("is_teacher") == false) {
                    user = new Student(rs.getInt("user_id"));
                } else {
                    user = new Teacher(rs.getInt("user_id"));
                }
                user.setFirstname(rs.getString("firstname"));
                user.setSurname(rs.getString("surname"));
                user.setAddress(rs.getString("address"));
                user.setZipcode(rs.getString("zipcode"));
                user.setGender(rs.getString("gender").charAt(0));
                user.setEmail(rs.getString("email"));
                user.setIsBanned(rs.getBoolean("banned"));
                user.setPassword(rs.getString("password"));
                user.setCity(rs.getString("city"));
                user.setCountry(rs.getString("country"));
                user.setGender(rs.getString("gender").charAt(0));
                user.setLanguage(rs.getInt("language_id"));
            }
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }

    // Course
    public int insertCourse(Course course) {
        int id = -1;

        try {
            startConnection();

            String sql = "insert "
                    + "   into Course(name, description, category)"
                    + "   values (?, ?, ?)  ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepared_statement.setString(1, course.getName());
            prepared_statement.setString(2, course.getDescription());
            prepared_statement.setString(3, course.getCategory());

            prepared_statement.execute();

            ResultSet generatedKeys = prepared_statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = (int) generatedKeys.getLong(1);
            }
            generatedKeys.close();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    // Question
    public int insertQuestion(Question question) {
        int id = -1;

        try {
            startConnection();

            String sql = "INSERT  "
                    + "   INTO Question(test_id, question, answer, answer1, answer2, answer3, type, description)"
                    + "   VALUES (?, ?, ?, ?, ?, ?, ?, ?)  ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            prepared_statement.setInt(1, question.getTestId());
            prepared_statement.setString(2, question.getQuestion());
            prepared_statement.setString(3, question.getCorrectAnswer());
            prepared_statement.setString(4, question.getAnswer1());
            prepared_statement.setString(5, question.getAnswer2());
            prepared_statement.setString(6, question.getAnswer3());

            if (("").equals(question.getAnswer1()) && ("").equals(question.getAnswer2()) && ("").equals(question.getAnswer3())) {
                prepared_statement.setString(7, "s");
            } else {
                prepared_statement.setString(7, "m");
            }

            //Dit vult de description met de vraag en de mogelijke antwoorden.
            prepared_statement.setString(8, question.getQuestion() + " " + question.getCorrectAnswer()
                    + " " + question.getAnswer1() + " " + question.getAnswer2() + " " + question.getAnswer3());

            prepared_statement.execute();

            ResultSet generatedKeys = prepared_statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = (int) generatedKeys.getLong(1);
            }
            generatedKeys.close();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public int insertTest(Test test) {
        int id = -1;

        try {
            startConnection();

            String sql = "insert "
                    + "   into Test(course_id, chapter_id, amount_of_questions, time, title, description, start_date, end_date)"
                    + "   values (?, ?, ?, ?, ?, ?, ?, ?)  ";
            System.out.println(test.getChapter_id());
            PreparedStatement prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepared_statement.setInt(1, test.getCourse_id());
            prepared_statement.setInt(3, test.getAmount_of_questions());
            prepared_statement.setInt(4, test.getTime());
            prepared_statement.setString(5, test.getTitle());
            prepared_statement.setString(6, test.getDescription());
            prepared_statement.setString(7, test.getStart_date());
            prepared_statement.setString(8, test.getEnd_date());
            if (test.getChapter_id() == 0) {
                prepared_statement.setNull(2, java.sql.Types.INTEGER);
            } else {
                prepared_statement.setInt(2, test.getChapter_id());
            }

            prepared_statement.execute();

            ResultSet generatedKeys = prepared_statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = (int) generatedKeys.getLong(1);
            }
            generatedKeys.close();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public Course getCourse(int id) {
        Course course = null;

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from "
                    + "         Course"
                    + "     where "
                    + "         courseID = ?"
                    + "     limit 1";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, id);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                course = new Course(id);
                course.setName(rs.getString("name"));
                course.setDescription(rs.getString("description"));
                course.setCategory(rs.getString("category"));
                course.setMaximumStudents(rs.getInt("maximumStudents"));
            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    public List<Course> searchCourse(String criteria) {

        Course course = null;

        List<Course> courses = new ArrayList<Course>();

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from "
                    + "         Course"
                    + "     where "
                    + "         name LIKE ?"
                    + "     OR  description LIKE ?"
                    + "     OR  category LIKE ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setString(1, "%" + criteria + "%");
            prepared_statement.setString(2, "%" + criteria + "%");
            prepared_statement.setString(3, "%" + criteria + "%");

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {

                course = new Course(rs.getInt("courseID"));
                course.setName(rs.getString("name"));
                course.setDescription(rs.getString("description"));
                course.setCategory(rs.getString("category"));
                course.setMaximumStudents(rs.getInt("maximumStudents"));

                courses.add(course);
            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public boolean updateCourse(Course course) {
        try {
            startConnection();

            String sql = "  update Course "
                    + "     set name = ?, description = ?, category = ?, maximumStudents = ?"
                    + "     where "
                    + "     courseID = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setString(1, course.getName());
            prepared_statement.setString(2, course.getDescription());
            prepared_statement.setString(3, course.getCategory());
            prepared_statement.setInt(4, course.getMaximumStudents());

            prepared_statement.setInt(5, course.getId());

            prepared_statement.execute();

            closeConnection();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Chapter
    public Chapter getChapter(int id) {
        Chapter chapter = null;

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from"
                    + "         Chapter"
                    + "     where"
                    + "         id = ?"
                    + "     limit 1";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, id);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                chapter = new Chapter(id);
                chapter.setCourse_id(rs.getInt("courseID"));
                chapter.setTitle(rs.getString("chapterName"));
                chapter.setDescription(rs.getString("description"));
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chapter;
    }

    public List<Chapter> getCourseChapters(int id) {

        List<Chapter> chapters = new ArrayList<Chapter>();

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from"
                    + "         Chapter"
                    + "     where"
                    + "         courseID = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, id);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                chapters.add(getChapter(rs.getInt("id")));
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chapters;
    }

    public List<Course> getUserCourses(User user) {

        long user_id = user.getId();

        List<Course> courses = new ArrayList<Course>();

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from "
                    + "         SubbedCourses"
                    + "     where "
                    + "         userID = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setLong(1, user_id);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                courses.add(getCourse(rs.getInt("courseID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public Test getTest(int id) {
        Test test = null;

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from"
                    + "         Test"
                    + "     where"
                    + "         id = ?"
                    + "     limit 1";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, id);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                test = new Test(id);
                test.setAmount_of_questions(rs.getInt("amount_of_questions"));
                test.setChapter_id(rs.getInt("chapter_id"));
                test.setCourse_id(rs.getInt("course_id"));
                test.setDescription(rs.getString("description"));
                test.setEnd_date(rs.getString("end_date"));
                test.setStart_date(rs.getString("start_date"));
                test.setTime(rs.getInt("time"));
                test.setTitle(rs.getString("title"));
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return test;
    }

    public List<Test> getTestsByCouseID(List<Integer> ids) {
        List<Test> tests = new ArrayList<Test>();

        try {
            startConnection();
            for (int id : ids) {
                String sql = "  select "
                        + "         *"
                        + "     from"
                        + "         Test"
                        + "     where"
                        + "         course_id = ?"
                        + "     limit 1";

                PreparedStatement prepared_statement = conn.prepareStatement(sql);
                prepared_statement.setInt(1, id);

                ResultSet rs = prepared_statement.executeQuery();

                while (rs.next()) {
                    Test test = new Test(id);
                    test.setAmount_of_questions(rs.getInt("amount_of_questions"));
                    test.setChapter_id(rs.getInt("chapter_id"));
                    test.setCourse_id(rs.getInt("course_id"));
                    test.setDescription(rs.getString("description"));
                    test.setEnd_date(rs.getString("end_date"));
                    test.setStart_date(rs.getString("start_date"));
                    test.setTime(rs.getInt("time"));
                    test.setTitle(rs.getString("title"));
                    tests.add(test);
                }
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tests;
    }

    public ArrayList<Test> getTests() {

        ArrayList<Test> tests = new ArrayList<Test>();

        try {
            startConnection();

            String sql = "  SELECT "
                    + "         * "
                    + "     FROM "
                    + "         Test "
                    + "     WHERE isActive = 1 ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                Test test = new Test(rs.getInt("id"));
                test.setAmount_of_questions(rs.getInt("amount_of_questions"));
                test.setChapter_id(rs.getInt("chapter_id"));
                test.setCourse_id(rs.getInt("course_id"));
                test.setDescription(rs.getString("description"));
                test.setEnd_date(rs.getString("end_date"));
                test.setStart_date(rs.getString("start_date"));
                test.setTime(rs.getInt("time"));
                test.setTitle(rs.getString("title"));

                tests.add(test);
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tests;
    }

    public List<Test> getUserTests(User user) {

        List<Test> tests = new ArrayList<Test>();
        List<Course> courses = getUserCourses(user);
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < courses.size(); i++) {
            sb.append(courses.get(i).getId());
            if (i != courses.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");

        try {
            startConnection();

            String sql = "  select "
                    + "         * "
                    + "     from "
                    + "         Test "
                    + "     where course_id"
                    + "     in "
                    + sb.toString();

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            ResultSet rs = prepared_statement.executeQuery();

            Test test;

            while (rs.next()) {
                test = new Test(rs.getInt("id"));
                test.setAmount_of_questions(rs.getInt("amount_of_questions"));
                test.setChapter_id(rs.getInt("chapter_id"));
                test.setCourse_id(rs.getInt("course_id"));
                test.setDescription(rs.getString("description"));
                test.setEnd_date(rs.getString("end_date"));
                test.setStart_date(rs.getString("start_date"));
                test.setTime(rs.getInt("time"));
                test.setTitle(rs.getString("title"));
                tests.add(test);
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tests;
    }

    public ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<Course>();

        try {
            startConnection();

            String sql = "  select "
                    + "         * "
                    + "     from "
                    + "         Course "
                    + "     where isActive = 1 "
                    + "     order by name asc";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                Course course = new Course(rs.getInt("courseID"));
                course.setCategory(rs.getString("category"));
                course.setDescription(rs.getString("description"));
                course.setEndDate(rs.getDate("endDate"));
//                course.setHeadTeacher(this.getTeacher());
                course.setIsActive(rs.getBoolean("isActive"));
                course.setMaximumStudents(rs.getInt("maximumStudents"));
                course.setName(rs.getString("name"));
                course.setStartDate(rs.getDate("startDate"));
//                course.setStudents(null);
//                course.setTests(null);
//                course.setChapters(null);

                courses.add(course);
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public ArrayList<Question> getQuestions() {

        ArrayList<Question> questions = new ArrayList<Question>();

        try {
            startConnection();

            String sql = "  SELECT "
                    + " * "
                    + " FROM "
                    + " Question "
                    + " WHERE isActive = 1 ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                Question question = new Question(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                question.setCorrectAnswer(rs.getString("answer"));
                question.setAnswer1(rs.getString("answer1"));
                question.setAnswer2(rs.getString("answer2"));
                question.setAnswer3(rs.getString("answer3"));
                question.setDescription(rs.getString("description"));
                question.setTestId(rs.getInt("test_id"));

                questions.add(question);
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    public List<Question> getQuestions(int test_id) {

        List<Question> questions = new ArrayList<Question>();

        try {
            startConnection();

            String sql = "  SELECT "
                    + " * "
                    + " FROM "
                    + " Question "
                    + " WHERE test_id = ? ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, test_id);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                Question question = new Question(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                question.setCorrectAnswer(rs.getString("answer"));
                question.setAnswer1(rs.getString("answer1"));
                question.setAnswer2(rs.getString("answer2"));
                question.setAnswer3(rs.getString("answer3"));
                question.setDescription(rs.getString("description"));
                question.setTestId(rs.getInt("test_id"));

                questions.add(question);
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    public boolean submitAnswers(int user_id, int test_id, Map<Integer, String> answers) {
        boolean resullt = false;

        try {
            startConnection();

            for (Map.Entry<Integer, String> entry : answers.entrySet()) {
                Integer question_id = entry.getKey();
                String answer = entry.getValue();

                String sql = "insert "
                        + "   into UserAnswer(user_id, test_id, question_id, answer)"
                        + "   values (?, ?, ?, ?)  ";
                
                PreparedStatement prepared_statement = conn.prepareStatement(sql);
                prepared_statement.setInt(1, user_id);
                prepared_statement.setInt(2, test_id);
                prepared_statement.setInt(3, question_id);
                prepared_statement.setString(4, answer);
                prepared_statement.execute();
            }

            closeConnection();
            
            resullt = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resullt;
    }

    public int updateTest(Test test) {
        int affected_rows = 0;

        try {
            startConnection();

            String sql = "update Test "
                    + "   set amount_of_questions = ?, time = ?, course_id = ?, chapter_id = ?, title = ?, description = ?, start_date = ?, end_date = ?"
                    + "   where id = ?  ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepared_statement.setInt(1, test.getAmount_of_questions());
            prepared_statement.setInt(2, test.getTime());
            prepared_statement.setInt(3, test.getCourse_id());
            prepared_statement.setInt(9, test.getId());
            prepared_statement.setString(5, test.getTitle());
            prepared_statement.setString(6, test.getDescription());
            prepared_statement.setString(7, test.getStart_date());
            prepared_statement.setString(8, test.getEnd_date());
            if (test.getChapter_id() == 0) {
                prepared_statement.setNull(4, java.sql.Types.INTEGER);
            } else {
                prepared_statement.setInt(4, test.getChapter_id());
            }

            affected_rows = prepared_statement.executeUpdate();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affected_rows;
    }

    //delete test
    public void deleteTest(int test_id) {

        try {
            startConnection();

            String sql = "DELETE "
                    + " FROM Test"
                    + " WHERE id = ?";
            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, test_id);
            prepared_statement.execute();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean disenrollCourse(long user_id, int course_id) {
        boolean ressult = false;

        try {
            startConnection();

            String sql = "  delete "
                    + "     from "
                    + "         SubbedCourses"
                    + "     where "
                    + "         userID = ? and courseID = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setLong(1, user_id);
            prepared_statement.setInt(2, course_id);

            prepared_statement.execute();
            
            ressult = true;

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ressult;
    }

    //delete course
//    public void deleteCourse(int course_id) {
//
//        try {
//            startConnection();
//
//            String sql = "DELETE "
//                    + " FROM Course"
//                    + " WHERE courseID = ?";
//            PreparedStatement prepared_statement = conn.prepareStatement(sql);
//            prepared_statement.setInt(1, course_id);
//            prepared_statement.execute();
//
//            closeConnection();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
    public ArrayList<Test> getCourseTests(int course_id) {

        ArrayList<Test> tests = new ArrayList<Test>();

        try {
            startConnection();

            String sql = "  select "
                    + "         * "
                    + "     from "
                    + "         Test "
                    + "     where course_id = ? "
                    + "     order by title asc";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, course_id);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                Test test = new Test(rs.getInt("id"));
                test.setAmount_of_questions(rs.getInt("amount_of_questions"));
                test.setChapter_id(rs.getInt("chapter_id"));
                test.setCourse_id(rs.getInt("course_id"));
                test.setDescription(rs.getString("description"));
                test.setEnd_date(rs.getString("end_date"));
                test.setStart_date(rs.getString("start_date"));
                test.setTime(rs.getInt("time"));
                test.setTitle(rs.getString("title"));

                tests.add(test);
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tests;
    }

    public void disableTest(int test_id) {
        try {
            startConnection();

            String sql = "UPDATE Test  "
                    + " set isActive = 0"
                    + " WHERE id = ?";
            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, test_id);
            prepared_statement.execute();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void disableCourse(int course_id) {
        ArrayList<Test> tests = getCourseTests(course_id);
        String sql = "";

        try {
            startConnection();

            //Als de course geen bijbehorende testen heeft
            if (tests.isEmpty()) {
                sql = "UPDATE Course "
                        + " SET isActive = 0"
                        + " WHERE courseID = ?";

                PreparedStatement prepared_statement = conn.prepareStatement(sql);
                prepared_statement.setInt(1, course_id);
                prepared_statement.execute();
            }

            //Als de course 1 of meerdere bijhorende testen heeft
            if (tests.size() > 0) {
                sql = " UPDATE Course, Test"
                        + " SET Course.isActive = 0, Test.isActive = 0 "
                        + " WHERE Course.courseID = ?  "
                        + " AND Test.course_id = ?";

                PreparedStatement prepared_statement = conn.prepareStatement(sql);
                prepared_statement.setInt(1, course_id);
                prepared_statement.setInt(2, course_id);
                prepared_statement.execute();
            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void enrollCourse(int course_id, long user_id) {
        
         try {
            startConnection();

            String sql = "insert "
                    + "   into SubbedCourses(courseID, userID)"
                    + "   values (?, ?)  ";
            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, course_id);
            prepared_statement.setLong(2, user_id);

            prepared_statement.execute();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
