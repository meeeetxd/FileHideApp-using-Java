package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MyConnection {
    public static Connection con;
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HiddenFiles", "root", "meet21");
        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection established");
        return con;
    }
    public static void closeConnection() {
        if(con != null) {
            try {
                con.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
