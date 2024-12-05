package dao;

import db.MyConnection;
import model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    // checks if user already exists
    public static boolean isExists(String email) throws SQLException{

        Connection connection = MyConnection.getConnection();
        PreparedStatement p = connection.prepareStatement("select email from users");
        ResultSet r = p.executeQuery();
        while(r.next()){
            String e = r.getString(1);
            if(e.equals(email)){
                System.out.println("User with this email already exits");

                return true;
            }
        }
        return false;
    }
    // to insert the user
    public static int saveUser(User user) throws SQLException{
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps= connection.prepareStatement("insert into users values(default,?,?)");
        ps.setString(1,user.getName());
        ps.setString(2,user.getEmail());

        return ps.executeUpdate();

    }
}
