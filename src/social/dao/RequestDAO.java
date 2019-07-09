package social.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import social.model.Request;
import social.model.User;

public class RequestDAO
{
    public List<Request> getRequest_To(Integer request_to) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        String getrequestById = "SELECT u.user_id, u.first_name, u.last_name, u.email, "
                + "u.about_me, u.img, r.request_id, r.date_col, r.responded from REQUEST r "
                + "JOIN USER_T u ON u.USER_ID = r.USER_REQUEST "
                + "WHERE r.REQUEST_TO = ? "
                + "ORDER BY r.DATE_COL DESC";
        List<Request> rList = null;
        try
        {
            conn = OracleConnection.getConnection();
            stmt = conn.prepareStatement(getrequestById);
            stmt.setInt(1, request_to);
            
            result = stmt.executeQuery();
            rList = new ArrayList<Request>();
            while(result.next()) {
                User u = new User(result.getInt(1), result.getString(2), result.getString(3),
                        result.getString(4), result.getString(5), result.getString(6));
                Request r = new Request();
                r.setRequest_id(result.getInt(7));
                java.sql.Date tempD = result.getDate(8);
                Date actualD = new Date(tempD.getTime());
                r.setDate_col(actualD);
                r.setUser_request(u);
                r.setResponded((result.getInt(9) == 1 || result.getInt(9) == -1) ? true : false);
                rList.add(r);
            }
        }
        catch (ClassNotFoundException | IOException | SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rList;
    }
    
    public boolean checkRequest(Integer request_from, Integer request_to) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        String checkRequestQuery = "SELECT r.user_request, r.request_id from REQUEST r "
                + "WHERE r.USER_REQUEST = ? AND r.REQUEST_TO = ? ";
        boolean requestExists = false;
        try
        {
            conn = OracleConnection.getConnection();
            stmt = conn.prepareStatement(checkRequestQuery);
            stmt.setInt(1, request_from);
            stmt.setInt(2, request_to);
            
            result = stmt.executeQuery();
            while(result.next()) {
                requestExists = true;
            }
        }
        catch (ClassNotFoundException | IOException | SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return requestExists;
    }
    
    public Integer requestFriendShip(Integer user_request, Integer request_to) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        Integer ID = null;
        String[] COL = {"REQUEST_ID"};
        
        String requestFriend = "INSERT INTO REQUEST(USER_REQUEST, REQUEST_TO, DATE_COL) "
                + "VALUES(?, ?, ?)";
        
        try
        {
            conn = OracleConnection.getConnection();
            stmt = conn.prepareStatement(requestFriend, COL);
            stmt.setInt(1, user_request);
            stmt.setInt(2, request_to);
            stmt.setDate(3, new java.sql.Date(new Date().getTime()));
            
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
    
    public void requestUpdate(Integer request_id, Integer reply) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        String requestFriend = "UPDATE REQUEST SET responded = ? "
                + "WHERE request_id = ?";
        
        try
        {
            conn = OracleConnection.getConnection();
            stmt = conn.prepareStatement(requestFriend);
            stmt.setInt(1, reply);
            stmt.setInt(2, request_id);
            
            stmt.executeUpdate();
            
        }
        catch (ClassNotFoundException | IOException | SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
