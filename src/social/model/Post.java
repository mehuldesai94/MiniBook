package social.model;

import java.util.Date;

public class Post
{
    private Integer post_id;
    private String post_text;
    private Integer user_id;
    private Date date_col;
    //display purposes
    private User user;
    /**
     * @param post_text
     * @param user_id
     * @param user
     */
    public Post(String post_text, Integer post_id) {
        this.post_text = post_text;
        this.post_id = post_id;
    }
    /**
     * @param post_id
     * @param post_text
     * @param user_id
     * @param user
     */
    public Post(Integer post_id, String post_text, Integer user_id, Date date_col) {
        this.post_id = post_id;
        this.post_text = post_text;
        this.user_id = user_id;
        this.date_col = date_col;
    }
    /**
     * @param post_id
     * @param post_text
     * @param user
     */
    public Post(Integer post_id, String post_text, Date date_col, User user) {
        this.post_id = post_id;
        this.post_text = post_text;
        this.date_col = date_col;
        this.user = user;
    }
    public Post() {
  
    }
    /**
     * @return the post_id
     */
    public Integer getPost_id()
    {
        return post_id;
    }
    /**
     * @param post_id the post_id to set
     */
    public void setPost_id(Integer post_id)
    {
        this.post_id = post_id;
    }
    /**
     * @return the post_text
     */
    public String getPost_text()
    {
        return post_text;
    }
    /**
     * @param post_text the post_text to set
     */
    public void setPost_text(String post_text)
    {
        this.post_text = post_text;
    }
    /**
     * @return the user_id
     */
    public Integer getUser_id()
    {
        return user_id;
    }
    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(Integer user_id)
    {
        this.user_id = user_id;
    }
    /**
     * @return the date_col
     */
    public Date getDate_col()
    {
        return date_col;
    }
    /**
     * @param date_col the date_col to set
     */
    public void setDate_col(Date date_col)
    {
        this.date_col = date_col;
    }
    /**
     * @return the user
     */
    public User getUser()
    {
        return user;
    }
    /**
     * @param user the user to set
     */
    public void setUser(User user)
    {
        this.user = user;
    }
}
