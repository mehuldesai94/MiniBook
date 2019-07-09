package social.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import social.model.Friends;
import social.model.User;

public class FriendsDAO
{
    public List<Friends> getAllUserFriends(Integer user_id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        List<Friends> userFriends = null;
        String userF = "SELECT u.user_id, u.first_name, u.last_name, u.email, "
                + "u.about_me, u.img, f.friends_id, "
                + "CASE WHEN (f.user_request != u.user_id AND u.user_id = f.USER_ACCEPTED) "
                + "THEN f.user_request "
                + "WHEN (f.user_request = u.user_id AND u.user_id != f.USER_ACCEPTED) "
                + "THEN f.USER_ACCEPTED "
                + "END AS USER_REQUEST "
                + "FROM FRIENDS f "
                + "JOIN USER_T u ON u.USER_ID = f.USER_REQUEST OR u.USER_ID = f.USER_ACCEPTED "
                + "WHERE (u.user_id != f.USER_ACCEPTED AND f.USER_ACCEPTED = ?) "
                + "OR (u.user_id != f.USER_REQUEST AND f.USER_REQUEST = ?)";
        try
        {
            conn = OracleConnection.getConnection();
            stmt = conn.prepareStatement(userF);
            stmt.setInt(1, user_id);
            stmt.setInt(2, user_id);
            
            result = stmt.executeQuery();
            userFriends = new ArrayList<Friends>();
            while(result.next()) {
                User u = new User(result.getInt(1), result.getString(2), result.getString(3),
                        result.getString(4), result.getString(5), result.getString(6));
                Friends f = new Friends();
                f.setFriends_id(result.getInt(7));
                f.setUser_accepted(u);
                System.out.println("user id" + result.getInt(8));
                f.setUser_request(result.getInt(8));
                userFriends.add(f);
            }
        }
        catch (ClassNotFoundException | IOException | SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return userFriends;        
    }
    
    public List<Friends> getAllNonFriends(Integer user_id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        List<Friends> nonFriends = null;
        String nonF = "SELECT u.user_id, u.first_name, u.last_name, u.email, "
                + "u.about_me, u.IMG "
                + "FROM USER_T u "
                + "WHERE u.USER_ID NOT IN (SELECT u.user_id "
                + "FROM FRIENDS f "
                + "JOIN USER_T u ON u.USER_ID = f.USER_REQUEST OR u.USER_ID = f.USER_ACCEPTED "
                + "WHERE (u.user_id != f.USER_ACCEPTED AND f.USER_ACCEPTED = ?) "
                + "OR (u.user_id != f.USER_REQUEST AND f.USER_REQUEST = ?)) "
                + "AND u.USER_ID != ?";
        try
        {
            conn = OracleConnection.getConnection();
            stmt = conn.prepareStatement(nonF);
            stmt.setInt(1, user_id);
            stmt.setInt(2, user_id);
            stmt.setInt(3, user_id);
            result = stmt.executeQuery();
            nonFriends = new ArrayList<Friends>();
            while(result.next()) {
                User u = new User(result.getInt(1), result.getString(2), result.getString(3),
                        result.getString(4), result.getString(5), result.getString(6));
                Friends f = new Friends();
                f.setUser_accepted(u);
                nonFriends.add(f);
            }
        }
        catch (ClassNotFoundException | IOException | SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return nonFriends;        
    }
    
    public Integer confirmFriendShip(Friends f) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        String[] COL = {"FRIENDS_ID"};
        Integer ID = null;
        String myFriend = "INSERT INTO FRIENDS(USER_REQUEST, USER_ACCEPTED, REQUEST_ID) "
                + "VALUES(?, ?, ?)";
        
        try
        {
            conn = OracleConnection.getConnection();
            stmt = conn.prepareStatement(myFriend, COL);
            stmt.setInt(1, f.getUser_request());
            stmt.setInt(2, f.getUser_accepted().getUser_id());
            stmt.setInt(3, f.getRequest_id());
            
            stmt.executeUpdate();
            
            result = stmt.getGeneratedKeys();
            
            if(result.next()) {
                ID = result.getInt(1);
            }
        }
        catch (ClassNotFoundException | IOException | SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ID;
    }
}
