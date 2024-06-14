package DAO;

import db.MyConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static boolean isExists(String email) throws SQLException {
        Connection con = MyConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement("select email from user");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            String mailID = rs.getString(1);
            if (mailID.equals(email))
                return true;
        }
        return false;
    }
    public static int saveUser(User user) throws SQLException{
        Connection connection = MyConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement("insert into user values(default,?,?)");
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        return stmt.executeUpdate();
    }
}
