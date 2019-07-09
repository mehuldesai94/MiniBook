package social.model;

public class User
{
    private Integer user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String pass;
    private String aboutMe;
    private String img;
    /**
     * 
     */
    public User() {
    }
    /**
     * @param user_id
     * @param first_name
     * @param last_name
     * @param email
     * @param aboutMe
     */
    public User(Integer user_id, String first_name, String last_name, String email, String aboutMe, String img) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.aboutMe = aboutMe;
        this.img = img;
    }
    /**
     * @param user_id
     * @param first_name
     * @param last_name
     * @param email
     * @param pass
     * @param aboutMe
     */
    public User(Integer user_id, String first_name, String last_name, String email, String pass, String aboutMe, String img) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.pass = pass;
        this.aboutMe = aboutMe;
        this.img = img;
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
     * @return the first_name
     */
    public String getFirst_name()
    {
        return first_name;
    }
    /**
     * @param first_name the first_name to set
     */
    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }
    /**
     * @return the last_name
     */
    public String getLast_name()
    {
        return last_name;
    }
    /**
     * @param last_name the last_name to set
     */
    public void setLast_name(String last_name)
    {
        this.last_name = last_name;
    }
    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
    /**
     * @return the pass
     */
    public String getPass()
    {
        return pass;
    }
    /**
     * @param pass the pass to set
     */
    public void setPass(String pass)
    {
        this.pass = pass;
    }
    /**
     * @return the aboutMe
     */
    public String getAboutMe()
    {
        return aboutMe;
    }
    /**
     * @param aboutMe the aboutMe to set
     */
    public void setAboutMe(String aboutMe)
    {
        this.aboutMe = aboutMe;
    }
    /**
     * @return the img
     */
    public String getImg()
    {
        return img;
    }
    /**
     * @param img the img to set
     */
    public void setImg(String img)
    {
        this.img = img;
    }
}
