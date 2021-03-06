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
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;
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
    public int insertCourse(Course course, int teacher_id) {
        int id = -1;
        
        try {
            startConnection();

            String sql = "insert "
                    + "   into Course(category, endDate, startDate, maximumStudents, teacher_id)"
                    + "   values (?, ?, ?, ?, ?)  ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepared_statement.setString(1, course.getCategory());
            prepared_statement.setString(2, course.getStartDate());
            prepared_statement.setString(3, course.getEndDate());
            prepared_statement.setInt(4, course.getMaximumStudents());
            prepared_statement.setInt(5, teacher_id);

            prepared_statement.execute();

            ResultSet generatedKeys = prepared_statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = (int) generatedKeys.getLong(1);
            }
            generatedKeys.close();

            if (id > 0) {
                sql = "  insert"
                        + "     into CourseVertaling(language_id, name, description) "
                        + "     values (?, ?, ?) ";
                prepared_statement = conn.prepareStatement(sql);
                prepared_statement.setInt(1, course.getLanguage());
                prepared_statement.setString(2, course.getName());
                prepared_statement.setString(3, course.getDescription());

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
                course.setEndDate(rs.getString("endDate"));
                course.setStartDate(rs.getString("startDate"));
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
    
   

    public List<Course> searchCourses(String criteria, int language) {

        List<Course> courses = new ArrayList<Course>();

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from "
                    + "         CourseVertaling"
                    + "     where "
                    + "         (name LIKE ?"
                    + "     OR  description LIKE ?)"
                    + "     AND "
                    + "         language_id = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setString(1, "%" + criteria + "%");
            prepared_statement.setString(2, "%" + criteria + "%");
            prepared_statement.setInt(3, language);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                
                courses.add(getCourse(rs.getInt("course_id"), language));
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
                    + "   set category = ?, maximumStudents = ?, startDate = ?, endDate = ?"
                    + "   where Course.courseID = ?  ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepared_statement.setString(1, course.getCategory());
            prepared_statement.setInt(2, course.getMaximumStudents());
            prepared_statement.setString(3, course.getStartDate());
            prepared_statement.setString(4, course.getEndDate());
            prepared_statement.setInt(5, course.getId());

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
    public Chapter getChapter(int id, int language) {
        Chapter chapter = null;

        try {
            startConnection();

            String sql = "  select "
                    + "         Chapter.*, ChapterVertaling.* "
                    + "     from"
                    + "         Chapter"
                    + "     inner join"
                    + "         ChapterVertaling on ChapterVertaling.chapter_id = Chapter.id"
                    + "     where"
                    + "         Chapter.id = ?"
                    + "     and"
                    + "         ChapterVertaling.language_id = ?"
                    + "     limit 1";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, id);
            prepared_statement.setInt(2, language);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                chapter = new Chapter(id);
                chapter.setCourse_id(rs.getInt("courseID"));
                chapter.setChapterName(rs.getString("chapterName"));
                chapter.setChapter_description(rs.getString("chapter_description"));
                chapter.setChapter_content(rs.getString("chapter_content"));
                chapter.setVideoUrl(rs.getString("videoUrl"));
                chapter.setLanguage(language);
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chapter;
    }

    public Chapter getChapterCourse(int id, int language) {
        Chapter chapter = null;

        try {
            startConnection();

            String sql = "select Course.*, CourseVertaling.* , Chapter.*, ChapterVertaling.* "
                    + "from Chapter "
                    + " inner join Course on Course.courseID = Chapter.courseID "
                    + " inner join CourseVertaling on CourseVertaling.course_id = Chapter.courseID "
                    + " inner join ChapterVertaling on ChapterVertaling.chapter_id = Chapter.id"
                    + " where Chapter.id = ? "
                    + " and CourseVertaling.language_id = ? "
                    + " and ChapterVertaling.language_id = ? "
                    + " limit 1";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, id);
            prepared_statement.setInt(2, language);
            prepared_statement.setInt(3, language);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                chapter = new Chapter(id);

                //Chapter
                chapter.setCourse_id(rs.getInt("courseID"));
                chapter.setChapter_description(rs.getString("chapter_description"));
                chapter.setChapterName(rs.getString("chapterName"));
                chapter.setChapter_content(rs.getString("chapter_content"));
                chapter.setVideoUrl(rs.getString("videoUrl"));

                //Course vertaling
                chapter.setDescription(rs.getString("description"));
                chapter.setTitle(rs.getString("name"));

                //Course
                chapter.setImgSrc(rs.getString("img_src"));

            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chapter;
    }

    public List<Chapter> getCourseChapters(int id, int language) {

        List<Chapter> chapters = new ArrayList<Chapter>();

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from"
                    + "         Chapter"
                    + "     where"
                    + "         courseID = ?"
                    + "     and isActive = 1";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, id);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                chapters.add(getChapter(rs.getInt("id"), language));
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
                    + "         userID = ?";

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

    public List<Test> getTests(int language) {

        List<Test> tests = new ArrayList<Test>();

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
     * Gebruik deze methode om alle tests op te halen, onafhankelijk van taal.
     *
     * @return
     */
    public List<Test> getTestsIncludingNoTranslations(int language) {
        List<Test> tests = new ArrayList<Test>();

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from "
                    + "         Test "
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
                test.setIsActive(rs.getBoolean("isActive"));

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

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();

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

    public List<User> getCourseUsers(int courseID) {
        List<User> users = new ArrayList<User>();

        try {
            startConnection();

            String sql = "SELECT User.* "
                    + "FROM SubbedCourses "
                    + "INNER JOIN User "
                    + "ON User.user_id = SubbedCourses.userID "
                    + "WHERE SubbedCourses.courseID = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            prepared_statement.setInt(1, courseID);

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

    public List<Course> getCourses(int language) {
        List<Course> courses = new ArrayList<Course>();

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
                course.setEndDate(rs.getString("endDate"));
//                course.setHeadTeacher(this.getTeacher());
                course.setIsActive(rs.getBoolean("isActive"));
                course.setMaximumStudents(rs.getInt("maximumStudents"));
                course.setName(rs.getString("name"));
                course.setStartDate(rs.getString("startDate"));
                course.setNumberOfStudents(rs.getInt("numberOfStudents"));
                course.setImgSrc(rs.getString("img_src"));
                course.setLanguage(language);
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
    public List<Course> getCoursesIncludingNoTranslations(int language) {
        List<Course> courses = new ArrayList<Course>();

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from "
                    + "         Course "
                    + "     order by CourseID asc";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                Course course = new Course(rs.getInt("courseID"));
                course.setCategory(rs.getString("category"));
                course.setEndDate(rs.getString("endDate"));
                course.setIsActive(rs.getBoolean("isActive"));
                course.setMaximumStudents(rs.getInt("maximumStudents"));
                course.setStartDate(rs.getString("startDate"));
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

    public List<Course> getPopularCourses(int language) {
        List<Course> courses = new ArrayList<Course>();

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
                course.setEndDate(rs.getString("endDate"));
//                course.setHeadTeacher(this.getTeacher());
                course.setIsActive(rs.getBoolean("isActive"));
                course.setMaximumStudents(rs.getInt("maximumStudents"));
                course.setName(rs.getString("name"));
                course.setStartDate(rs.getString("startDate"));
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

    public List<Question> getQuestions() {

        List<Question> questions = new ArrayList<Question>();

        try {
            startConnection();

            String sql = " SELECT question.id, question.description,question.type, question.answer, question.question, "
                    + " question.answer1, question.answer2, question.answer3, question.test_id, question.isActive"
                    + "  , test_vertaling.title "
                    + " FROM Question AS question "
                    + " INNER JOIN TestVertaling AS test_vertaling ON test_vertaling.test_id = question.test_id ";

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
                question.setIsActive(rs.getBoolean("isActive"));

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

    public Map<Integer,Set<Integer>> getMadedTests() {
       Map<Integer,Set<Integer>> madedTests = new HashMap<Integer,Set<Integer>>();

        try {
            startConnection();

            String sql = "  select"
                    + "   test_id, user_id"
                    + "   from UserAnswer";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            ResultSet resultSet = prepared_statement.executeQuery();

            while (resultSet.next()) {
                int testID = resultSet.getInt("test_id");
                int userID = resultSet.getInt("user_id");
                if(madedTests.get(userID) == null) madedTests.put(userID, new HashSet<Integer>());
                madedTests.get(userID).add(testID);
            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return madedTests;
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
                    + " WHERE id = ?";

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

            closeConnection();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
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
    public List<Test> getCourseTests(int course_id, int language) {

        List<Test> tests = new ArrayList<Test>();

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
        List<Test> tests = getCourseTests(course_id, language);
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

    public boolean enrollCourse(int course_id, long user_id) {
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
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } return false;
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

    public List<Grade> getGrades() {
        List<Grade> grades = new ArrayList<Grade>();

        try {
            startConnection();

            String sql = "SELECT * FROM Grade";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                Grade grade = new Grade(rs.getInt("id"));
                grade.setUserId(rs.getInt("user_id"));
                grade.setTestId(rs.getInt("test_id"));
                grade.setGrade(rs.getInt("grade"));
                grades.add(grade);

            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grades;
    }
    
    public Grade getGrade(int studentID, int testID) {
        Grade grade = null;

        try {
            startConnection();

            String sql = "  select "
                    + "         * "
                    + "     from "
                    + "         Grade "
                    + "     where user_id = ? and test_id = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            prepared_statement.setInt(1, studentID);
            prepared_statement.setInt(2, testID);

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

    public boolean updateGrade(Grade grade) {
        int affected_rows = 0;

        try {
            startConnection();

            String sql = " UPDATE Grade "
                    + "   SET grade = ?"
                    + "   WHERE user_id = ? and test_id = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            prepared_statement.setInt(1, grade.getGrade());
            prepared_statement.setInt(2, grade.getUserId());
            prepared_statement.setInt(3, grade.getTestId());

            affected_rows = prepared_statement.executeUpdate();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (affected_rows > 0 ? true : false);
    }

    public boolean updateQuestion(Question question) {
        int affected_rows = 0;

        try {
            startConnection();

            String sql = " UPDATE Question "
                    + "   SET question = ?, answer = ?, "
                    + "   answer1 = ?, answer2 = ?, answer3 = ?, test_id = ?, type = ?, description = ? "
                    + "   WHERE Question.id = ?  ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

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

            affected_rows = prepared_statement.executeUpdate();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (affected_rows > 0 ? true : false);
    }

    public int insertChapter(Chapter chapter) {
        int id = -1;

        try {
            startConnection();

            String sql = "insert "
                    + "   into Chapter(courseID)"
                    + "   values (?)";

            PreparedStatement prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepared_statement.setInt(1, chapter.getCourse_id());

            prepared_statement.execute();

            ResultSet generatedKeys = prepared_statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = (int) generatedKeys.getLong(1);
            }
            generatedKeys.close();

            //Plaats vertaling in de DB als chapter succesvol in DB is geplaatst.
            if (id > 0) {
                sql = "  insert"
                        + "     into ChapterVertaling(chapter_id, language_id, chapterName, chapter_description, chapter_content, videoUrl) "
                        + "     values (?, ?, ?, ?, ?, ?) ";
                prepared_statement = conn.prepareStatement(sql);

                prepared_statement.setInt(1, id);
                prepared_statement.setInt(2, chapter.getLanguage());
                prepared_statement.setString(3, chapter.getChapterName());
                prepared_statement.setString(4, chapter.getChapter_description());
                prepared_statement.setString(5, chapter.getChapter_content());
                prepared_statement.setString(6, chapter.getVideoUrl());

                prepared_statement.execute();
            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public List<Chapter> getChapters(int language) {
        List<Chapter> chapters = new ArrayList<Chapter>();

        try {
            startConnection();

            String sql = "  select Chapter.*, ChapterVertaling.* "
                    + "     from Chapter"
                    + "     inner join ChapterVertaling on ChapterVertaling.chapter_id = Chapter.id"
                    + "     where Chapter.isActive = 1 "
                    + "     and ChapterVertaling.language_id = ?";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            prepared_statement.setInt(1, language);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                Chapter chapter = new Chapter(rs.getInt("id"));
                chapter.setCourse_id(rs.getInt("courseID"));
                chapter.setChapter_description(rs.getString("chapter_description"));
                chapter.setChapterName(rs.getString("chapterName"));
                chapter.setChapter_content(rs.getString("chapter_content"));
                chapter.setIsActive(rs.getBoolean("isActive"));

                chapters.add(chapter);
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chapters;
    }

    /**
     * Gebruik deze methode om alle chapters op te halen, onafhankelijk van
     * taal.
     *
     * @return
     */
    public List<Chapter> getChaptersIncludingNoTranslations(int language) {
        List<Chapter> chapters = new ArrayList<Chapter>();

        try {
            startConnection();

            String sql = "  select "
                    + "         *"
                    + "     from "
                    + "         Chapter "
                    + "     order by id asc";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                Chapter chapter = new Chapter(rs.getInt("id"));
                chapter.setCourse_id(rs.getInt("courseID"));
                chapter.setIsActive(rs.getBoolean("isActive"));

                //test.setLanguage(language);
                sql = "     select "
                        + "     chapterName, chapter_description, chapter_content"
                        + " from"
                        + "     ChapterVertaling"
                        + " where language_id = ?"
                        + " and chapter_id = ?";

                prepared_statement = conn.prepareStatement(sql);
                prepared_statement.setInt(1, language);
                prepared_statement.setInt(2, chapter.getId());

                ResultSet rs2 = prepared_statement.executeQuery();

                while (rs2.next()) {
                    chapter.setChapterName(rs2.getString("chapterName"));
                    chapter.setChapter_description(rs2.getString("chapter_description"));
                    chapter.setChapter_content(rs2.getString("chapter_content"));
                }

                if (chapter.getChapterName() == null) {
                    chapter.setChapterName("No translation");
                }
                if (chapter.getChapter_description() == null) {
                    chapter.setChapter_description("No translation");
                }
                if (chapter.getChapter_content() == null) {
                    chapter.setChapter_content("No translation");
                }

                chapters.add(chapter);
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chapters;
    }

    public int updateChapter(Chapter chapter) {
        int affected_rows = 0;

        try {
            startConnection();

            String sql = "update Chapter "
                    + "   set courseID = ? "
                    + "   where Chapter.id = ? ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepared_statement.setInt(1, chapter.getCourse_id());
            prepared_statement.setInt(2, chapter.getId());

            affected_rows = prepared_statement.executeUpdate();

            if (affected_rows > 0) {
                //Set the translations
                sql = "     insert into ChapterVertaling(chapter_id, language_id, chapterName, chapter_description, chapter_content, videoUrl)"
                        + " values(?, ?, ?, ?, ?, ?)"
                        + " on duplicate key "
                        + "     update "
                        + "         chapterName         =   values(chapterName          ), "
                        + "         chapter_description =   values(chapter_description  ), "
                        + "         chapter_content     =   values(chapter_content      ), "
                        + "         videoUrl     =   values(videoUrl      ) ";

                prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                prepared_statement.setInt(1, chapter.getId());
                prepared_statement.setInt(2, chapter.getLanguage());
                prepared_statement.setString(3, chapter.getChapterName());
                prepared_statement.setString(4, chapter.getChapter_description());
                prepared_statement.setString(5, chapter.getChapter_content());
                prepared_statement.setString(6, chapter.getVideoUrl());

                affected_rows = prepared_statement.executeUpdate();
            }
            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affected_rows;
    }

    public void disableChapter(int chapter_id) {
        try {
            startConnection();

            String sql = "UPDATE Chapter  "
                    + " set isActive = 0"
                    + " WHERE id = ?";
            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setInt(1, chapter_id);
            prepared_statement.execute();

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
