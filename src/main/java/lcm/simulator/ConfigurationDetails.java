package lcm.simulator;

public final class ConfigurationDetails
{
    private String url;
    private String password;
    private String user;
    
    private static final ConfigurationDetails c = new ConfigurationDetails();
    
    public static final ConfigurationDetails getInstance()
    {
        return c;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    
}
