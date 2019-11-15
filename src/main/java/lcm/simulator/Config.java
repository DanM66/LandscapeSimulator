package lcm.simulator;

import java.util.Random;

public final class Config
{
    private String url;
    private String password;
    private String user;
    private String raster;
    private String output;
    private String writer;
    private int tileXdim = 100;
    private int tileYdim = 100;
    private Random random = new Random();
    
    public int getTileXdim()
    {
        return tileXdim;
    }

    public void setTileXdim(int tileXdim)
    {
        this.tileXdim = tileXdim;
    }

    public int getTileYdim()
    {
        return tileYdim;
    }

    public void setTileYdim(int tileYdim)
    {
        this.tileYdim = tileYdim;
    }

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

    private static final Config c = new Config();
    
    public static final Config getInstance()
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

    public Random getRandom()
    {
        return random;
    }

    public void setRandom(Random random)
    {
        this.random = random;
    }

}
