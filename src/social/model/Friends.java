package social.model;

public class Friends
{
    private Integer friends_id;
    private Integer user_request;
    private User user_accepted;
    private Integer request_id;
    /**
     * @return the friends_id
     */
    public Integer getFriends_id()
    {
        return friends_id;
    }
    /**
     * @param friends_id the friends_id to set
     */
    public void setFriends_id(Integer friends_id)
    {
        this.friends_id = friends_id;
    }
    /**
     * @return the user_request
     */
    public Integer getUser_request()
    {
        return user_request;
    }
    /**
     * @param user_request the user_request to set
     */
    public void setUser_request(Integer user_request)
    {
        this.user_request = user_request;
    }
    /**
     * @return the request_id
     */
    public Integer getRequest_id()
    {
        return request_id;
    }
    /**
     * @param request_id the request_id to set
     */
    public void setRequest_id(Integer request_id)
    {
        this.request_id = request_id;
    }
    /**
     * @return the user_accepted
     */
    public User getUser_accepted()
    {
        return user_accepted;
    }
    /**
     * @param user_accepted the user_accepted to set
     */
    public void setUser_accepted(User user_accepted)
    {
        this.user_accepted = user_accepted;
    }
}
