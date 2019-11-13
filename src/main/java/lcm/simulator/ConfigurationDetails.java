package lcm.simulator;

public final class ConfigurationDetails
{
    private String url;
    private String password;
    private String user;
    private String raster;
    private String output;
    private String writer;
    public String getWriter()
    {
        return writer;
    }

    public void setWriter(String writer)
    {
        this.writer = writer;
    }

    private boolean debug;
    private int numBands;
    
    public int getNumBands()
    {
        return numBands;
    }

    public void setNumBands(int numBands)
    {
        this.numBands = numBands;
    }

    public boolean isDebug()
    {
        return debug;
    }

    public void setDebug(boolean debug)
    {
        this.debug = debug;
    }

    public String getOutput()
    {
        return output;
    }

    public void setOutput(String output)
    {
        this.output = output;
    }

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
    
    public String getRaster()
    {
        return this.raster;
    }
    
    public void setRaster(String r)
    {
        this.raster = r;
    }

}
