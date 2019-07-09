package social.model;

import java.util.Date;

public class Request
{
    private Integer request_id;
    private Integer request_to;
    private User user_request;
    private Date date_col;
    private boolean responded;
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
     * @return the request_to
     */
    public Integer getRequest_to()
    {
        return request_to;
    }
    /**
     * @param request_to the request_to to set
     */
    public void setRequest_to(Integer request_to)
    {
        this.request_to = request_to;
    }
    /**
     * @return the user_request
     */
    public User getUser_request()
    {
        return user_request;
    }
    /**
     * @param user_request the user_request to set
     */
    public void setUser_request(User user_request)
    {
        this.user_request = user_request;
    }
    /**
     * @return the responded
     */
    public boolean isResponded()
    {
        return responded;
    }
    /**
     * @param responded the responded to set
     */
    public void setResponded(boolean responded)
    {
        this.responded = responded;
    } 
}
