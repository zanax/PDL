package connection;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import models.Chapter;
import java.util.ArrayList;
import java.util.List;
import models.Chapter;
import java.util.ArrayList;
import java.util.List;
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
                    + "   into User(firstname, surname, address, zipcode, gender, email, banned, password)"
                    + "   values (?, ?, ?, ?, ?, ?, ?, ?)  ";

            PreparedStatement prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepared_statement.setString(1, user.getFirstname());
            prepared_statement.setString(2, user.getSurname());
            prepared_statement.setString(3, user.getAddress());
            prepared_statement.setString(4, user.getZipcode());
            prepared_statement.setString(5, String.valueOf(user.getGender()));
            prepared_statement.setString(6, user.getEmail());
            prepared_statement.setBoolean(7, false);
            prepared_statement.setString(8, user.getPassword());

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
                    user = new Student();
                } else {
                    user = new Teacher();
                    if (rs.getBoolean("is_teacher") == false) {
                        user = new Student(rs.getLong("user_id"));
                    } else {
                        user = new Teacher(rs.getLong("user_id"));
                    }
                    user.setFirstname(rs.getString("firstname"));
                    user.setSurname(rs.getString("surname"));
                    user.setAddress(rs.getString("address"));
                    user.setZipcode(rs.getString("zipcode"));
                    user.setGender(rs.getString("gender").charAt(0));
                    user.setEmail(rs.getString("email"));
                    user.setIsBanned(rs.getBoolean("banned"));
                    user.setPassword(rs.getString("password"));
                }
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
                chapter.setTitle(rs.getString("title"));
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chapter;
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
}
