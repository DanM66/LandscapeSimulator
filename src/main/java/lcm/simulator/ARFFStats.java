package lcm.simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ARFFStats
{

    /**
     * I am the one and only ARFFStats!!
     */
    private static final ARFFStats theApp = new ARFFStats();

    private Map<Integer, RunningStat[]> m = null;
    private Number noDataValue = -32768;
    private double tol = 1e-8;
    private double reduce =    1.0;

    public void printMap()
    {
        for (Integer key : m.keySet())
        {
            System.out.println("<class number = \"" + key + "\">");
            RunningStat[] rs = m.get(key);
            for (int ii = 0; ii < rs.length; ++ii)
            {
                System.out.println(" <band number = \"" + (ii+1) + "\" mean = \"" +
                rs[ii].mean() + "\" stdev = \"" + rs[ii].standardDeviationSample()*reduce + "\"/>");
                //System.out.println(" <band number = \"" + (ii+1) + "\" mean = \"" +
                //rs[ii].mean() + "\" stdev = \"" + 1.0 + "\"/>");
            }
            System.out.println("</class>");

            // <band number = "1" mean = "185.0" stdev = "8.0"/>
        }
    }

    public void addToMap(String str)
    {
        if (m == null) m = new HashMap<Integer, RunningStat[]>();

        String[] split = str.split(",");
        int length = split.length;

        Integer key = Integer.parseInt(split[length - 1]);

        RunningStat[] rsArray = m.get(key);
        if (rsArray == null)
        {
            rsArray = new RunningStat[length - 1];
            for (int ii = 0; ii < rsArray.length; ++ii)
            {
                rsArray[ii] = new RunningStat();
            }
        }

        for (int ii = 0; ii < length - 1; ++ii)
        {
            RunningStat rs = rsArray[ii];
            double d = Double.parseDouble(split[ii]);
            if (d > (noDataValue.doubleValue() + tol)) rs.push(d);
        }

        m.put(key, rsArray);

    }

    private ARFFStats()
    {
    };

    /**
     * Singleton App.
     * 
     * @return
     */
    public static ARFFStats getApp()
    {
        return theApp;
    }

    /**
     * Moves the reader to the first line following theString.
     * 
     * @param br
     * @param theString
     * @return
     * @throws IOException
     */
    private BufferedReader moveToLine(BufferedReader br, String theString) throws IOException
    {
        for (; (br.readLine()).indexOf(theString) < 0;)
            ;
        return br;
    }

    /**
     * Where everything gets done
     * 
     * @param args
     * @throws IOException
     */
    public void run(String[] args) throws IOException
    {
        String fileName = null;
        for (int ii = 0; ii < args.length; ++ii)
        {
            if ("-f".equals(args[ii])) fileName = args[ii+1];
            if ("-r".equals(args[ii])) setReduce(Double.parseDouble(args[ii+1]));
            if ("-t".equals(args[ii])) setTol(Double.parseDouble(args[ii+1]));
            if ("-ndata".equals(args[ii])) setNoDataValue(Integer.parseInt(args[ii+1]));
            
        }
        
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        br = moveToLine(br, "@DATA");

        for (String line; (line = br.readLine()) != null;)
        {
            if (line.isEmpty() || line.trim() == "") continue;
            addToMap(line);
        }

        printMap();

    }

    public Number getNoDataValue()
    {
        return noDataValue;
    }

    public void setNoDataValue(Number noDataValue)
    {
        this.noDataValue = noDataValue;
    }

    public double getTol()
    {
        return tol;
    }

    public void setTol(double tol)
    {
        this.tol = tol;
    }

    public double getReduce()
    {
        return reduce;
    }

    public void setReduce(double reduce)
    {
        this.reduce = reduce;
    }

    public static void main(String[] args) throws IOException
    {
        getApp().run(args);
    }
}