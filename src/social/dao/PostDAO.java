package social.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import social.model.Post;
import social.model.User;

public class PostDAO
{
    public Integer postOnWall(Post p) {
        Integer ID = null;
        String[] COL = {"post_id"};
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        String postQuery = "INSERT INTO POSTS(user_id, post_text, date_col) "
                + "VALUES(?, ?, ?)";
        try
        {
            conn = OracleConnection.getConnection();
            stmt = conn.prepareStatement(postQuery, COL);
            stmt.setInt(1, p.getUser_id());
            stmt.setString(2, p.getPost_text());
            stmt.setDate(3, new java.sql.Date(new Date().getTime()));
            stmt.executeUpdate();
            result = stmt.getGeneratedKeys();
            if(result.next()) {
                ID = result.getInt(1);
            }
        }
        catch (ClassNotFoundException | IOException | SQLException e)
        {
            e.printStackTrace();
        }
        return ID;
    }
    
    public void editPostOnWall(Post p) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String postQuery = "UPDATE POSTS SET "
                + "post_text = ? "
                + "WHERE POST_ID = ?";
        try
        {
            conn = OracleConnection.getConnection();
            stmt = conn.prepareStatement(postQuery);
            stmt.setString(1, p.getPost_text());
            stmt.setInt(2, p.getPost_id());
            
            stmt.executeUpdate();
        }
        catch (ClassNotFoundException | IOException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void deletePostOnWall(Integer post_id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String postQuery = "DELETE FROM POSTS "
                + "WHERE POST_ID = ?";
        try
        {
            conn = OracleConnection.getConnection();
            stmt = conn.prepareStatement(postQuery);
            stmt.setInt(1, post_id);
            
            stmt.executeUpdate();
        }
        catch (ClassNotFoundException | IOException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public List<Post> getPostRelatedToUser(Integer user_id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        List<Post> pList = null;
        
        String getPost = "SELECT u.user_id, u.first_name, u.last_name, u.email, "
                + "u.about_me, u.img, p.post_id, p.post_text, p.DATE_COL FROM POSTS p "
                + "JOIN USER_T u ON u.USER_ID = p.USER_ID "
                + "WHERE u.USER_ID = ? "
                + "UNION ALL "
                + "SELECT u.user_id, u.first_name, u.last_name, u.email, "
                + "u.about_me, u.img, p.post_id, p.post_text, p.DATE_COL FROM POSTS p "
                + "JOIN FRIENDS f ON f.USER_ACCEPTED = p.USER_ID OR f.USER_REQUEST = p.USER_ID "
                + "JOIN USER_T u ON u.USER_ID = f.USER_ACCEPTED OR u.USER_ID = f.USER_REQUEST "
                + "WHERE (f.USER_REQUEST = ? AND u.USER_ID != f.USER_REQUEST AND f.USER_ACCEPTED = p.USER_ID) "
                + "OR (f.USER_ACCEPTED = ? AND u.USER_ID != f.USER_ACCEPTED AND f.USER_REQUEST = p.USER_ID) "
                + "ORDER BY DATE_COL DESC";
        try
        {
            conn = OracleConnection.getConnection();
            stmt = conn.prepareStatement(getPost);
            stmt.setInt(1, user_id);
            stmt.setInt(2, user_id);
            stmt.setInt(3, user_id);
            
            result = stmt.executeQuery();
            pList = new ArrayList<Post>();
            while(result.next()) {
                User u = new User(result.getInt(1), result.getString(2), result.getString(3),
                        result.getString(4), result.getString(5), result.getString(6));
                java.sql.Date tempD = result.getDate(9);
                Date actualD = new Date(tempD.getTime());
                Post p = new Post(result.getInt(7), result.getString(8), actualD, u);
                pList.add(p);
            }
        }
        catch (ClassNotFoundException | IOException | SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pList;
    }
    
    public Post getSinglePost(Integer user_id, Integer post_id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        Post p = null;
        String getPost = "SELECT u.user_id, u.first_name, u.last_name, u.email, "
                + "u.about_me, u.img, p.post_id, p.post_text, p.DATE_COL FROM POSTS p "
                + "JOIN USER_T u ON u.USER_ID = p.USER_ID "
                + "WHERE u.USER_ID = ? AND p.POST_ID = ?";
        try
        {
            conn = OracleConnection.getConnection();
            stmt = conn.prepareStatement(getPost);
            stmt.setInt(1, user_id);
            stmt.setInt(2, post_id);
            
            result = stmt.executeQuery();
            if(result.next()) {
                User u = new User(result.getInt(1), result.getString(2), result.getString(3),
                        result.getString(4), result.getString(5), result.getString(6));
                java.sql.Date tempD = result.getDate(9);
                Date actualD = new Date(tempD.getTime());
                p = new Post(result.getInt(7), result.getString(8), actualD, u);      
            }
        }
        catch (ClassNotFoundException | IOException | SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return p;  
    }
//    public static void main(String[] args)
//    {
//        PostDAO w = new PostDAO();
//        Post p = new Post("yayyyyy", 1);
////        w.postOnWall(p);
//        w.deletePostOnWall(1);
//    }
}
