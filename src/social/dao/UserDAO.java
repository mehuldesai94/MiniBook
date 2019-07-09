package social.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import social.model.User;

public class UserDAO
{
    public User getUserByEmail(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        User u = null;
        String query = "SELECT * FROM USER_T WHERE email = ?";
        try
        {
            conn = OracleConnection.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            result = stmt.executeQuery();
            
            if(result.next()) {
                System.out.println(result.getInt(1)+" "+ result.getString(2)+" "+
                        result.getString(3)+" "+ result.getString(4)+" "+ 
                        result.getString(5)+" "+ result.getString(6));
                u = new User(result.getInt(1), result.getString(2),
                        result.getString(3), result.getString(4), 
                        result.getString(5), result.getString(6), result.getString(7));
            }
        }
        catch (ClassNotFoundException | IOException | SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            result.close();
            if(stmt != null) {
                stmt.close();
            }
            if(conn != null) {
                conn.close();
            }
        }
        return u;
    }
    
    public List<User> getAllUsers() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        User u = null;
        List<User> uList = null;
        String query = "SELECT * FROM USER_T";
        try
        {
            conn = OracleConnection.getConnection();
            stmt = conn.prepareStatement(query);
            result = stmt.executeQuery();
            uList = new ArrayList<User>();
            if(result.next()) {
                u = new User(result.getInt(1), result.getString(2),
                        result.getString(3), result.getString(4), 
                        result.getString(6), result.getString(7));
                uList.add(u);
            }
        }
        catch (ClassNotFoundException | IOException | SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            result.close();
            if(stmt != null) {
                stmt.close();
            }
            if(conn != null) {
                conn.close();
            }
        }
        return uList;
    }
}
