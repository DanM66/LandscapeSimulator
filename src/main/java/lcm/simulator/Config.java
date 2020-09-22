package lcm.simulator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This is a helper class. It carries around all the configuration parameters so
 * that they can be easily accessed within any part of the source.
 * 
 * @author User
 *
 */
public final class Config
{
    private String url;
    private String password;
    private String user;
    private String input;
    private String output;
    private String writer;
    private String landCover;
    private int tileXdim = 100;
    private int tileYdim = 100;
    private int numThreads = 1;
    private int randUniformMax = 255;
    private String pixelType = "random";
    private int seed = 1;
    private boolean debug;
    private Integer[] theBands;
    HashMap<Integer, double[][]> bandSpectra = null;

    public HashMap<Integer, double[][]>getBandSpectra()
    {
        return bandSpectra;
    }

    public void setBandSpectra(HashMap<Integer, double[][]> m)
    {
        this.bandSpectra = m;
    }

    public void init(String inputFileName)
    {

        try
        {
            File                   inputFile = new File(inputFileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder        dBuilder  = dbFactory.newDocumentBuilder();
            Document               doc       = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // System.out.println("Root element :" +
            // doc.getDocumentElement().getNodeName());
            Element e = (Element) doc.getElementsByTagName("debug").item(0);
            if (e != null)
            {
                setDebug("true".equals(e.getAttribute("value").toLowerCase()));
            }

            e = (Element) doc.getElementsByTagName("threads").item(0);
            if (debug)
            {
                setNumThreads(1);
            }
            else if (e != null)
            {
                setNumThreads(Integer.parseInt(e.getAttribute("value")));
            }

            e = (Element) doc.getElementsByTagName("seed").item(0);
            if (e != null)
            {
                setSeed(Integer.parseInt(e.getAttribute("value")));
            }

            e = (Element) doc.getElementsByTagName("inraster").item(0);
            if (e != null)
            {
                setInput(e.getAttribute("name"));
            }

            e = (Element) doc.getElementsByTagName("outraster").item(0);

            if (e != null)
            {
                setOutput(e.getAttribute("name"));
                //setNumBands(Integer.parseInt(e.getAttribute("nbands")));
                setBands(e.getAttribute("bands"));
                setPixelType(e.getAttribute("pixeltype"));
                setWriter(e.getAttribute("writer"));
            }

            e = (Element) doc.getElementsByTagName("database").item(0);
            {
                setUrl(e.getAttribute("url"));
                setUser(e.getAttribute("user"));
                setPassword(e.getAttribute("password"));
            }

            System.out.println("url = " + getUrl());
            System.out.println("user = " + getUser());
            System.out.println("password = " + getPassword());
            System.out.println("debug = " + isDebug());
            System.out.println("threads = " + getNumThreads());
            System.out.println("seed = " + getSeed());
            System.out.println("inraster = " + getInput());
            System.out.println("outraster = " + getOutput());
            System.out.println("numBands = " + getNumBands());
            for (int ii = 0; ii < this.theBands.length; ++ii)
                System.out.print(theBands[ii] + " ");
            System.out.println();
            System.out.println("pixelType = " + getPixelType());
            System.out.println("writer = " + getWriter());

            NodeList     nlOuter        = doc.getElementsByTagName("class");
            HashMap<Integer, double[][]> spectralParams = new HashMap<Integer, double[][]>();

            for (int ii = 0; ii < nlOuter.getLength(); ii++)
            {
                
                Node nodeOuter = nlOuter.item(ii);
                System.out.println("\nCurrent Element :" + nodeOuter.getNodeName());
                Element  eElement = (Element) nodeOuter;
                int clazz = Integer.parseInt(eElement.getAttribute("number"));
                //Element eElement = (Element) 

                if (nodeOuter.getNodeType() == Node.ELEMENT_NODE)
                {
                    
                    NodeList nlInner  = eElement.getElementsByTagName("band");
                    double[][] dd = new double[nlInner.getLength()][2];
                    for (int jj = 0; jj < nlInner.getLength(); ++jj)
                    {

                        Node nodeInner = nlInner.item(jj);
                        e = (Element) nodeInner;
                        // System.out.println("\nNested Element :" + nodeInner.getNodeName());
                        int    band  = Integer.parseInt(e.getAttribute("number"));
                        double mean  = Double.parseDouble(e.getAttribute("mean"));
                        double stdev = Double.parseDouble(e.getAttribute("stdev"));
                        dd[band - 1][0] = mean;
                        dd[band - 1][1] = stdev;
                    }
                    spectralParams.put(clazz, dd);
                }
            }
            setBandSpectra(spectralParams);
            
            for (Integer key: getBandSpectra().keySet())
            {
                for (int jj = 0; jj < getBandSpectra().get(key).length; ++jj)
                {
                    System.out.println("class = " + key + " band = " + (jj + 1) + " mean = "
                            + getBandSpectra().get(key)[jj][0] + " stdev = " + getBandSpectra().get(key)[jj][1]);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

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



    public int getNumBands()
    {
        return this.theBands.length;
    }
    
    public Integer[] getTheBands()
    {
        return this.theBands;
    }

    public void setBands(String bList)
    {
        ArrayList<Integer> al = new ArrayList<Integer>();
        String[] splitStr = bList.split(",");
        
        for (int ii = 0; ii < splitStr.length; ++ii)
        {
            String[] sp = splitStr[ii].split("[-]");

            if  (sp.length == 1)
            {
                al.add(Integer.parseInt(splitStr[ii]));
            }
            if (sp.length == 2)
            {
                for (int jj = Integer.parseInt(sp[0]); jj <= Integer.parseInt(sp[1]); ++jj)
                {
                    al.add(jj);
                }
                    
            }
        }
        
        this.theBands = al.toArray(new Integer[al.size()]);
        
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

    public String getInput()
    {
        return this.input;
    }

    public void setInput(String r)
    {
        this.input = r;
    }

    public static void main(String[] args)
    {
        (new Config()).init("config4.xml");
    }

}
