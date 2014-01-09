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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.crypto.Data;
import models.Admin;
import models.Course;
import models.Grade;
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
    
    public int banUser(int userId) {

        int affected_rows = 0;

        try {
            startConnection();

            String sql = "  update User "
                    + "     set banned = ?"
                    + "     where "
                    + "     user_id = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setBoolean(1, true);
            prepared_statement.setInt(2, userId);

            affected_rows = prepared_statement.executeUpdate();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affected_rows;
    }
    
    public int unBanUser(int userId) {

        int affected_rows = 0;

        try {
            startConnection();

            String sql = "  update User "
                    + "     set banned = ?"
                    + "     where "
                    + "     user_id = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setBoolean(1, false);
            prepared_statement.setInt(2, userId);

            affected_rows = prepared_statement.executeUpdate();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affected_rows;
    }
    
    public int makeTeacher(int userId) {

        int affected_rows = 0;

        try {
            startConnection();

            String sql = "  update User "
                    + "     set is_admin = ?, is_teacher = ?"
                    + "     where "
                    + "     user_id = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setBoolean(1, false);
            prepared_statement.setBoolean(2, true);
            prepared_statement.setInt(3, userId);

            affected_rows = prepared_statement.executeUpdate();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affected_rows;
    }
    
    public int makeAdmin(int userId) {

        int affected_rows = 0;

        try {
            startConnection();

            String sql = "  update User "
                    + "     set is_admin = ?, is_teacher = ?"
                    + "     where "
                    + "     user_id = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setBoolean(1, true);
            prepared_statement.setBoolean(2, false);
            prepared_statement.setInt(3, userId);

            affected_rows = prepared_statement.executeUpdate();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affected_rows;
    }
    
    public int makeStudent(int userId) {

        int affected_rows = 0;

        try {
            startConnection();

            String sql = "  update User "
                    + "     set is_teacher = ?, is_admin = ?"
                    + "     where "
                    + "     user_id = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setBoolean(1, false);
            prepared_statement.setBoolean(2, false);
            prepared_statement.setInt(3, userId);

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
                if (rs.getBoolean("is_teacher") == true) {
                    user = new Teacher(rs.getInt("user_id"));
                } else if (rs.getBoolean("is_admin") == true) {
                    user = new Admin(rs.getInt("user_id"));
                } else if (rs.getBoolean("is_admin") == false && rs.getBoolean("is_teacher") == false) {
                    user = new Student(rs.getInt("user_id"));
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
    
     public User getEveryUser(String email) {
        User user = null;

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from "
                    + "         User"
                    + "     where "
                    + "         email = ?"
                    + "     limit 1";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setString(1, email);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                if (rs.getBoolean("is_teacher") == true) {
                    user = new Teacher(rs.getInt("user_id"));
                } else if (rs.getBoolean("is_admin") == true) {
                    user = new Admin(rs.getInt("user_id"));
                } else if(rs.getBoolean("is_admin") == false && rs.getBoolean("is_teacher") == false){
                    user = new Student(rs.getInt("user_id"));
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
    
    
    //haalt ook gebande users op
    public User getEveryUser(int userID) {
        User user = null;

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from "
                    + "         User"
                    + "     where "
                    + "         user_id = ?"
                    + "     limit 1";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, userID);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                if (rs.getBoolean("is_teacher") == true) {
                    user = new Teacher(rs.getInt("user_id"));
                } else if (rs.getBoolean("is_admin") == true) {
                    user = new Admin(rs.getInt("user_id"));
                } else if(rs.getBoolean("is_admin") == false && rs.getBoolean("is_teacher") == false){
                    user = new Student(rs.getInt("user_id"));
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
                user.setIsTeacher(rs.getBoolean("is_teacher"));
                user.setIsAdmin(rs.getBoolean("is_admin"));
            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }
    
    public User getUser(int userID) {
        User user = null;

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from "
                    + "         User"
                    + "     where "
                    + "         user_id = ?"
                    + "         and"
                    + "         banned = 0"
                    + "     limit 1";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, userID);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                if (rs.getBoolean("is_teacher") == true) {
                    user = new Teacher(rs.getInt("user_id"));
                } else if (rs.getBoolean("is_admin") == true) {
                    user = new Admin(rs.getInt("user_id"));
                } else if(rs.getBoolean("is_admin") == false && rs.getBoolean("is_teacher") == false){
                    user = new Student(rs.getInt("user_id"));
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
                user.setIsTeacher(rs.getBoolean("is_teacher"));
                user.setIsAdmin(rs.getBoolean("is_admin"));
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
                    + "   into Course(category)"
                    + "   values (?)  ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepared_statement.setString(1, course.getCategory());

            prepared_statement.execute();

            ResultSet generatedKeys = prepared_statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = (int) generatedKeys.getLong(1);
            }
            generatedKeys.close();

            if (id > 0) {
                sql = "  insert"
                        + "     into CourseVertaling(course_id, language_id, name, description) "
                        + "     values (?, ?, ?, ?) ";
                prepared_statement = conn.prepareStatement(sql);
                prepared_statement.setInt(1, id);
                prepared_statement.setInt(2, course.getLanguage());
                prepared_statement.setString(3, course.getName());
                prepared_statement.setString(4, course.getDescription());

                prepared_statement.execute();
            }

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
                    + "   into Test(course_id, chapter_id, amount_of_questions, time, start_date, end_date)"
                    + "   values (?, ?, ?, ?, ?, ?)  ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepared_statement.setInt(1, test.getCourse_id());
            prepared_statement.setInt(3, test.getAmount_of_questions());
            prepared_statement.setInt(4, test.getTime());
            prepared_statement.setString(5, test.getStart_date());
            prepared_statement.setString(6, test.getEnd_date());
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

            //Plaats vertaling in de DB als test succesvol in DB is geplaatst.
            if (id > 0) {
                sql = "  insert"
                        + "     into TestVertaling(language_id, test_id, title, description) "
                        + "     values (?, ?, ?, ?) ";
                prepared_statement = conn.prepareStatement(sql);
                prepared_statement.setInt(1, test.getLanguage());
                prepared_statement.setInt(2, id);
                prepared_statement.setString(3, test.getTitle());
                prepared_statement.setString(4, test.getDescription());

                prepared_statement.execute();
            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public Course getCourse(int id, int language) {
        Course course = null;

        try {
            startConnection();

            String sql = "  select "
                    + "         Course.*, CourseVertaling.*"
                    + "     from "
                    + "         Course"
                    + "     inner join CourseVertaling"
                    + "         on CourseVertaling.course_id = Course.courseID"
                    + "     where "
                    + "         courseID = ?"
                    + "     and"
                    + "         CourseVertaling.language_id = ?"
                    + "     limit 1";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, id);
            prepared_statement.setInt(2, language);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                course = new Course(id);
                course.setName(rs.getString("name"));
                course.setDescription(rs.getString("description"));
                course.setCategory(rs.getString("category"));
                course.setMaximumStudents(rs.getInt("maximumStudents"));
                course.setImgSrc(rs.getString("img_src"));
                course.setLanguage(rs.getInt("language_id"));
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
                course.setImgSrc(rs.getString("img_src"));

                courses.add(course);
            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public boolean updateCourse(Course course) {
        int affected_rows = 0;

        try {
            startConnection();

            String sql = "update Course "
                    + "   set category = ?, maximumStudents = ?"
                    + "   where Course.courseID = ?  ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepared_statement.setString(1, course.getCategory());
            prepared_statement.setInt(2, course.getMaximumStudents());
            prepared_statement.setInt(3, course.getId());

            affected_rows = prepared_statement.executeUpdate();

            if (affected_rows > 0) {
                //Set the translations
                sql = "     insert into CourseVertaling(course_id, language_id, name, description)"
                        + " values(?, ?, ?, ?)"
                        + " on duplicate key "
                        + "     update "
                        + "         name       =    values(name         ),"
                        + "         description =   values(description  )";

                prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                prepared_statement.setInt(1, course.getId());
                prepared_statement.setInt(2, course.getLanguage());
                prepared_statement.setString(3, course.getName());
                prepared_statement.setString(4, course.getDescription());

                affected_rows = prepared_statement.executeUpdate();
            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (affected_rows > 0 ? true : false);
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

    public List<Course> getUserCourses(User user, int language) {

        long user_id = user.getId();

        List<Course> courses = new ArrayList<Course>();

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from "
                    + "         SubbedCourses"
                    + "     where "
                    + "         userID = ?"
                    + " and isActive = 1" ;

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setLong(1, user_id);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                courses.add(getCourse(rs.getInt("courseID"), language));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public Test getTest(int id, int language) {
        Test test = null;

        try {
            startConnection();

            String sql = "  select "
                    + "         Test.*, TestVertaling.*"
                    + "     from"
                    + "         Test"
                    + "     inner join TestVertaling"
                    + "         on TestVertaling.test_id = Test.id"
                    + "     where"
                    + "         Test.id = ?"
                    + "     AND"
                    + "         TestVertaling.language_id = ?"
                    + "     limit 1";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, id);
            prepared_statement.setInt(2, language);

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
                //test.setLanguage(language);
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return test;
    }

//    public List<Test> getTestsByCourseID(List<Integer> ids) {
//        List<Test> tests = new ArrayList<Test>();
//
//        try {
//            startConnection();
//            for (int id : ids) {
//                String sql = "  select "
//                        + "         *"
//                        + "     from"
//                        + "         Test"
//                        + "     where"
//                        + "         course_id = ?"
//                        + "     limit 1";
//
//                PreparedStatement prepared_statement = conn.prepareStatement(sql);
//                prepared_statement.setInt(1, id);
//
//                ResultSet rs = prepared_statement.executeQuery();
//
//                while (rs.next()) {
//                    Test test = new Test(id);
//                    test.setAmount_of_questions(rs.getInt("amount_of_questions"));
//                    test.setChapter_id(rs.getInt("chapter_id"));
//                    test.setCourse_id(rs.getInt("course_id"));
//                    test.setDescription(rs.getString("description"));
//                    test.setEnd_date(rs.getString("end_date"));
//                    test.setStart_date(rs.getString("start_date"));
//                    test.setTime(rs.getInt("time"));
//                    test.setTitle(rs.getString("title"));
//                    tests.add(test);
//                }
//            }
//
//            closeConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return tests;
//    }
    public ArrayList<Test> getTests(int language) {

        ArrayList<Test> tests = new ArrayList<Test>();

        try {
            startConnection();

            String sql = "  SELECT "
                    + "         Test.*, TestVertaling.* "
                    + "     FROM "
                    + "         Test "
                    + "     INNER JOIN TestVertaling on Test.id = TestVertaling.test_id"
                    + "     WHERE Test.isActive = 1 "
                    + "     AND TestVertaling.language_id = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            prepared_statement.setInt(1, language);

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
                test.setLanguage(language);

                tests.add(test);
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tests;
    }

    /**
     * Gebruik deze methode om alle courses op te halen, onafhankelijk van taal.
     *
     * @return
     */
    public ArrayList<Test> getTestsIncludingNoTranslations(int language) {
        ArrayList<Test> tests = new ArrayList<Test>();

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from "
                    + "         Test "
                    + "     where isActive = 1 "
                    + "     order by id asc";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                Test test = new Test(rs.getInt("id"));
                test.setAmount_of_questions(rs.getInt("amount_of_questions"));
                test.setChapter_id(rs.getInt("chapter_id"));
                test.setCourse_id(rs.getInt("course_id"));
                test.setEnd_date(rs.getString("end_date"));
                test.setStart_date(rs.getString("start_date"));
                test.setTime(rs.getInt("time"));
                //test.setLanguage(language);

                sql = "     select "
                        + "     title, description"
                        + " from"
                        + "     TestVertaling"
                        + " where language_id = ?"
                        + " and test_id = ?";

                prepared_statement = conn.prepareStatement(sql);
                prepared_statement.setInt(1, language);
                prepared_statement.setInt(2, test.getId());

                ResultSet rs2 = prepared_statement.executeQuery();

                while (rs2.next()) {
                    test.setTitle(rs2.getString("title"));
                    test.setDescription(rs2.getString("description"));
                }

                if (test.getTitle() == null) {
                    test.setTitle("No translation");
                }
                if (test.getDescription() == null) {
                    test.setDescription("No translation");
                }

                tests.add(test);
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tests;
    }

    public List<Test> getUserTests(User user, int language) {

        List<Test> tests = new ArrayList<Test>();
        List<Course> courses = getUserCourses(user, language);
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
                    + "         Test.*, TestVertaling.* "
                    + "     from "
                    + "         Test "
                    + "     inner join TestVertaling"
                    + "         on TestVertaling.test_id = Test.id"
                    + "     where TestVertaling.language_id = ?"
                    + "     and "
                    + "         course_id"
                    + "             in "
                    + sb.toString();

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            prepared_statement.setInt(1, language);

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

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();

        try {
            startConnection();

            String sql = "  select "
                    + "         * "
                    + "     from "
                    + "         User "
                    + "     order by user_id asc";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getInt("user_id"));
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
                user.setLanguage(rs.getInt("language_id"));
                user.setIsTeacher(rs.getBoolean("is_teacher"));
                user.setIsAdmin(rs.getBoolean("is_admin"));

                users.add(user);
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public ArrayList<Course> getCourses(int language) {
        ArrayList<Course> courses = new ArrayList<Course>();

        try {
            startConnection();

            String sql = "  select "
                    + "         Course.*, CourseVertaling.* "
                    + "     from "
                    + "         Course "
                    + "     inner join"
                    + "         CourseVertaling"
                    + "     on CourseVertaling.course_id = Course.courseID"
                    + "     where Course.isActive = 1 "
                    + "     and CourseVertaling.language_id = ?"
                    + "     order by name asc";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, language);

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
                course.setNumberOfStudents(rs.getInt("numberOfStudents"));
                course.setImgSrc(rs.getString("img_src"));
                course.setLanguage(language);

                //course.setPopularity(rs.getInt("popularity"));
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

    /**
     * Gebruik deze methode om alle courses op te halen, onafhankelijk van taal.
     *
     * @return
     */
    public ArrayList<Course> getCoursesIncludingNoTranslations(int language) {
        ArrayList<Course> courses = new ArrayList<Course>();

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from "
                    + "         Course "
                    + "     where isActive = 1 "
                    + "     order by CourseID asc";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                Course course = new Course(rs.getInt("courseID"));
                course.setCategory(rs.getString("category"));
                course.setEndDate(rs.getDate("endDate"));
                course.setIsActive(rs.getBoolean("isActive"));
                course.setMaximumStudents(rs.getInt("maximumStudents"));
                course.setStartDate(rs.getDate("startDate"));
                course.setNumberOfStudents(rs.getInt("numberOfStudents"));
                course.setImgSrc(rs.getString("img_src"));
                course.setLanguage(language);

                sql = "     select "
                        + "     name, description"
                        + " from"
                        + "     CourseVertaling"
                        + " where language_id = ?"
                        + " and course_id = ?";

                prepared_statement = conn.prepareStatement(sql);
                prepared_statement.setInt(1, language);
                prepared_statement.setInt(2, course.getId());

                ResultSet rs2 = prepared_statement.executeQuery();

                while (rs2.next()) {
                    course.setName(rs2.getString("name"));
                    course.setDescription(rs2.getString("description"));
                }

                if (course.getName() == null) {
                    course.setName("No translation");
                }
                if (course.getDescription() == null) {
                    course.setDescription("No translation");
                }

                courses.add(course);
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public ArrayList<Course> getPopularCourses(int language) {
        ArrayList<Course> courses = new ArrayList<Course>();

        try {
            startConnection();

            String sql = "  select " //GET POPULAR COURSES
                    + "         Course.*, CourseVertaling.* "
                    + "     from "
                    + "         Course "
                    + "     inner join "
                    + "         CourseVertaling "
                    + "     on CourseVertaling.course_id = Course.courseID "
                    + "     where Course.isActive = 1 "
                    + "     and CourseVertaling.language_id = ?"
                    + "     ORDER BY numberOfStudents DESC, name "
                    + "     LIMIT 3";
            
            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, language);
            
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
                course.setNumberOfStudents(rs.getInt("numberOfStudents"));
                course.setImgSrc(rs.getString("img_src"));

                //course.setPopularity(rs.getInt("popularity"));
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

//    public boolean addQuestion(Question question) {
//        boolean resultt = false;
//
//        try {
//            startConnection();
//
//            String sql = "INSERT "
//                    + " INTO Question(test_id, question, correctAnswer, answer1, answer2, answer3)"
//                    + " VALUES (?, ?, ?, ?, ?, ?)";
//
//            PreparedStatement prepared_statement = conn.prepareStatement(sql);
//
//            prepared_statement.setInt(1, question.getTestId());
//            prepared_statement.setString(2, question.getQuestion());
//            prepared_statement.setString(3, question.getCorrectAnswer());
//            prepared_statement.setString(4, question.getAnswer1());
//            prepared_statement.setString(5, question.getAnswer2());
//            prepared_statement.setString(6, question.getAnswer3());
//
//            resultt = prepared_statement.execute();
//
//            closeConnection();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return resultt;
//    }
        public Question getQuestion(int id) {
        Question question = null;

        try {
            startConnection();

            String sql = "  SELECT "
                    + "     * "
                    + "     FROM "
                    + "         Question"
                    + "     WHERE "
                    + "         id = ?"
                    + "     limit 1";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, id);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                question = new Question(id);
                question.setQuestion(rs.getString("question"));
                question.setDescription(rs.getString("description"));
                question.setCorrectAnswer(rs.getString("answer"));
                question.setAnswer1(rs.getString("answer1"));
                question.setAnswer2(rs.getString("answer2"));
                question.setAnswer3(rs.getString("answer3"));
                question.setTestId(rs.getInt("test_id"));

            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return question;
    }
    public ArrayList<Question> getQuestions() {

        ArrayList<Question> questions = new ArrayList<Question>();

        try {
            startConnection();

            String sql = " SELECT question.id, question.description,question.type, question.answer, question.question, "
                    + " question.answer1, question.answer2, question.answer3, question.test_id, question.isActive"
                    + "  , test_vertaling.title "
                    + " FROM Question AS question "
                    + " INNER JOIN TestVertaling AS test_vertaling ON test_vertaling.test_id = question.test_id "
                    + " WHERE question.isActive = 1 ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                Question question = new Question(rs.getInt("id"));
                question.setTestTitle(rs.getString("title"));
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
                    + " WHERE test_id = ? "
                    + " AND isActive = 1 ";

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

    public Map<Integer, String> getAnswers(int user_id, int test_id) {
        Map<Integer, String> answers = new HashMap<Integer, String>();

        try {
            startConnection();

            String sql = "  select"
                    + "   *"
                    + "   from UserAnswer"
                    + "   where"
                    + "   user_id = ?"
                    + "   and "
                    + "   test_id = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, user_id);
            prepared_statement.setInt(2, test_id);

            ResultSet resultSet = prepared_statement.executeQuery();

            while (resultSet.next()) {
                answers.put(resultSet.getInt("question_id"), resultSet.getString("answer"));
            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return answers;

    }

    public boolean areadyMadeTest(int user_id, int test_id) {
        boolean resultt = false;
        try {
            startConnection();

            String sql = "select "
                    + "   1 "
                    + "   from"
                    + "   UserAnswer"
                    + "   where"
                    + "   user_id = ?"
                    + "   and"
                    + "   test_id = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, user_id);
            prepared_statement.setInt(2, test_id);
            ResultSet rs = prepared_statement.executeQuery();

            if (!rs.next()) {
                resultt = true;
            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultt;
    }

    public boolean isTestActive(int test_id) {
        boolean resultt = false;

        try {
            startConnection();

            String sql = "select "
                    + "   isActive"
                    + "   from"
                    + "   Test"
                    + "   where"
                    + "   id = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, test_id);
            ResultSet rs = prepared_statement.executeQuery();

            if (rs.next()) {
                if (rs.getBoolean("isActive")) {
                    resultt = true;
                }
            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultt;
    }

    public boolean submitAnswers(int user_id, int test_id, Map<Integer, String> answers) {
        boolean result = false;

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

            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void disableQuestion(int question_id) {
        String sql = "";
        try {
            startConnection();
            sql = "UPDATE Question "
                    + " SET isActive = 0"
                    + " WHERE questionID = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, question_id);
            prepared_statement.execute();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean submitAnswers(int user_id, Map<Integer, String> answers) {
        return false;
    }

    public int updateTest(Test test) {
        int affected_rows = 0;

        try {
            startConnection();

            String sql = "update Test "
                    + "   set amount_of_questions = ?, time = ?, course_id = ?, chapter_id = ?, start_date = ?, end_date = ?"
                    + "   where Test.id = ?  ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepared_statement.setInt(1, test.getAmount_of_questions());
            prepared_statement.setInt(2, test.getTime());
            prepared_statement.setInt(3, test.getCourse_id());
            if (test.getChapter_id() == 0) {
                prepared_statement.setNull(4, java.sql.Types.INTEGER);
            } else {
                prepared_statement.setInt(4, test.getChapter_id());
            }
            prepared_statement.setString(5, test.getStart_date());
            prepared_statement.setString(6, test.getEnd_date());
            prepared_statement.setInt(7, test.getId());

            affected_rows = prepared_statement.executeUpdate();

            if (affected_rows > 0) {
                //Set the translations
                sql = "     insert into TestVertaling(language_id, test_id, title, description)"
                        + " values(?, ?, ?, ?)"
                        + " on duplicate key "
                        + "     update "
                        + "         title       =   values(title        ),"
                        + "         description =   values(description  )";

                prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                prepared_statement.setInt(1, test.getLanguage());
                prepared_statement.setInt(2, test.getId());
                prepared_statement.setString(3, test.getTitle());
                prepared_statement.setString(4, test.getDescription());

                affected_rows = prepared_statement.executeUpdate();
            }

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

            //Eerst de vertaling verwijderen
            String sql = "DELETE "
                    + " FROM TestVertaling"
                    + " WHERE test_id = ?";
            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, test_id);
            prepared_statement.execute();

            sql = "DELETE "
                    + " FROM Test"
                    + " WHERE id = ?";
            prepared_statement = conn.prepareStatement(sql);
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

            ressult = prepared_statement.execute();

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
    public ArrayList<Test> getCourseTests(int course_id, int language) {

        ArrayList<Test> tests = new ArrayList<Test>();

        try {
            startConnection();

            String sql = "  select "
                    + "         Test.*, TestVertaling.* "
                    + "     from "
                    + "         Test "
                    + "     inner join TestVertaling on TestVertaling.test_id = Test.id"
                    + "     where Test.course_id = ? "
                    + "     and TestVertaling.language_id = ?"
                    + "     order by title asc";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, course_id);
            prepared_statement.setInt(2, language);

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
                //test.setLanguage(rs.getInt("language"));

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

    public void disableCourse(int course_id, int language) {
        ArrayList<Test> tests = getCourseTests(course_id, language);
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

            //Als de course 1 of meerdere bijbehorende testen heeft
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
                    + " into SubbedCourses(courseID, userID)"
                    + " values (?, ?) ";
            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, course_id);
            prepared_statement.setLong(2, user_id);

            prepared_statement.execute();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void amountPlusOne(int course_id) {

        try {
            startConnection();

            String sql = " UPDATE Course SET numberOfStudents = numberOfStudents + 1 WHERE courseID = ? ;";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, course_id);
            prepared_statement.execute();

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void amountMinusOne(int course_id) {

        try {
            startConnection();

            String sql = " UPDATE Course SET numberOfStudents = numberOfStudents - 1 WHERE courseID = ? ;";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, course_id);
            prepared_statement.execute();

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Grade> getGrades(User user) {

        long user_id = user.getId();

        List<Grade> grades = new ArrayList<Grade>();

        try {
            startConnection();

            String sql = "SELECT grade . * , test.title "
                    + "FROM Grade AS grade "
                    + "INNER JOIN TestVertaling AS test ON test.test_id = grade.test_id "
                    + "WHERE user_id = ? ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, (int) user_id);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                Grade grade = new Grade(rs.getInt("id"));
                grade.setUserId(rs.getInt("user_id"));
                grade.setTestId(rs.getInt("test_id"));
                grade.setGrade(rs.getInt("grade"));
                grade.setTestTitle(rs.getString("title"));

                grades.add(grade);

            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grades;
    }

    public Grade getGrade() {
        Grade grade = null;

        try {
            startConnection();

            String sql = "  select "
                    + "         * "
                    + "     from "
                    + "         Grade "
                    + "     where id = ? ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                grade = new Grade(rs.getInt("id"));
                grade.setId(rs.getInt("id"));
                grade.setUserId(rs.getInt("user_id"));
                grade.setTestId(rs.getInt("test_id"));
                grade.setGrade(rs.getInt("grade"));
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grade;
    }
    
    public boolean insertGrade(Grade grade) {
        
         try {
            startConnection();

            String sql = "insert "
                    + " into Grade(user_id, test_id, grade)"
                    + " values (?, ?, ?) ";
            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, grade.getUserId());
            prepared_statement.setInt(2, grade.getTestId());
            prepared_statement.setInt(3, grade.getGrade());

            prepared_statement.execute();

            closeConnection();
            
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean updateQuestion(Question question) {
        int affected_rows = 0;

        try {
            startConnection();

            String sql = " UPDATE Question "
                    + "   SET question = ?, answer = ?, "
                    + "   answer1 = ?, answer2 = ?, answer3 = ?, test_id = ?, type = ?, description = ? "
                    + "   WHERE Question.id = ?  ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            prepared_statement.setString(1, question.getQuestion());
            prepared_statement.setString(2, question.getCorrectAnswer());
            prepared_statement.setString(3, question.getAnswer1());
            prepared_statement.setString(4, question.getAnswer2());
            prepared_statement.setString(5, question.getAnswer3());
            prepared_statement.setInt(6, question.getTestId());

            //Kijkt of een vraag multiple of single choice is.
            if (("").equals(question.getAnswer1()) && ("").equals(question.getAnswer2()) && ("").equals(question.getAnswer3())) {
                prepared_statement.setString(7, "s");
            } else {
                prepared_statement.setString(7, "m");
            }

            //Vult de vraag + mogelijke antwoorden in bij description.
            prepared_statement.setString(8, question.getQuestion() + " " + question.getCorrectAnswer()
                    + " " + question.getAnswer1() + " " + question.getAnswer2() + " " + question.getAnswer3());

            prepared_statement.setInt(9, question.getId());


            prepared_statement.execute();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (affected_rows > 0 ? true : false);
    }
}