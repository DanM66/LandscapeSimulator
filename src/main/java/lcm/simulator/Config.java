package lcm.simulator;

public final class Config
{
    private String url;
    private String password;
    private String user;
    private String raster;
    private String output;
    private String writer;
    private String landCover;
    private int tileXdim = 100;
    private int tileYdim = 100;
    private int numThreads = 1;
    private int randUniformMax = 255;
    private String pixelType = "random";
    private int seed = 1;
    
    public int getSeed()
    {
        return seed;
    }


    public void setSeed(int seed)
    {
        this.seed = seed;
    }

    
    public String getLandCover()
    {
        return landCover;
    }


    public void setLandCover(String landCover)
    {
        this.landCover = landCover;
    }


    public String getPixelType()
    {
        return pixelType;
    }


    public void setPixelType(String pixelType)
    {
        this.pixelType = pixelType;
    }
    
    public int getNumThreads()
    {
        return numThreads;
    }

    
    public int getRandUniformMax()
    {
        return randUniformMax;
    }


    public void setRandUniformMax(int randUniformMax)
    {
        this.randUniformMax = randUniformMax;
    }


    public void setNumThreads(int nThreads)
    {
        this.numThreads = nThreads;
    }

    
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

}
