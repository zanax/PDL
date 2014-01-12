/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import static connection.DB.DBURL;
import static connection.DB.DRIVER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.User;

/**
 *
 * @author Niels
 */
public class ForumDB {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://145.92.6.85:3306/Forum";
    private static final String DBUSER = "pdlteam15";
    private static final String DBPASS = "89!3Ab.toor";
    private ResultSet result = null;
    private int affectedRows = -1;
    Connection conn = null;
    private static ForumDB db = null;

    private ForumDB() {
    }

    public static ForumDB getInstance() {
        if (db == null) {
            db = new ForumDB();
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
    
    
    public int insertForum(String name, String desc) {
        int id = -1;

        try {
            startConnection();

            String sql = "insert "
                    + "   into jforum_forums(forum_name, forum_desc)"
                    + "   values (?, ?)  ";
            PreparedStatement prepared_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepared_statement.setString(1, name);
            prepared_statement.setString(1, desc);
            

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

    
}
