package connection;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import models.Course;
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

    ;
    
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
                    + "     limit 1";

            PreparedStatement prepared_statement = conn.prepareStatement(sql);
            prepared_statement.setString(1, email);

            ResultSet rs = prepared_statement.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setFirstname(rs.getString("firstname"));
                user.setSurname(rs.getString("surname"));
                user.setAddress(rs.getString("address"));
                user.setZipcode(rs.getString("zipcode"));
                user.setGender(rs.getString("gender").charAt(0));
                user.setEmail(rs.getString("email"));
                user.setIsBanned(rs.getBoolean("banned"));
                user.setPassword(rs.getString("password"));
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
            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }
}
