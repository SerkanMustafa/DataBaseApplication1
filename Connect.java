package app;

import java.sql.*;
import java.util.*;

/**
 *
 * @author sqlitetutorial.net
 */
public class Connect {

    public static ArrayList<String> connect(String term) {
        ArrayList<String> danni = new ArrayList<String>();

        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:chinook.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

            String sql = "SELECT FirstName, LastName FROM employees WHERE "
                    + "FirstName LIKE'%" + term + "%' OR" + " LastName LIKE '%"
                    + term + "%'";
            System.out.println(sql);
            try (Connection openConn = conn;
                    Statement stmt = openConn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {

                // loop through the result set
                while (rs.next()) {
                    danni.add(rs.getString("FirstName") + " "
                            + rs.getString("LastName"));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return danni;
    }
}
