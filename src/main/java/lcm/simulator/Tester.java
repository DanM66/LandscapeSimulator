package lcm.simulator;

import java.util.Random;

public class Tester
{
    double[][][] bandStuff = null;
    Random r = null;
    
    public Tester(int nclass, int nbands)
    {
        bandStuff = new double[nclass][nbands][2];
        r = new Random(1);
        for (int ii = 0; ii < nclass; ++ii)
        {
            for (int jj = 0;  jj < nbands; ++jj)
            {
                bandStuff[ii][jj][0] = r.nextInt(100) + 100;
                bandStuff[ii][jj][1] = r.nextInt(10);
            }
        }
    }
    
    public double[][][] getBandStuff()
    {
        return this.bandStuff;
    }
    
    public static void main(String[] args)
    {
        (new Tester(25,36)).run(args);
    }
    
    private void run(String[] args)
    {
        double[][][] ddd = this.getBandStuff();
        
        for (int ii = 0; ii < ddd.length; ++ii)
        {
            System.out.println("<class number = \"" + (ii+1) + "\">");
            for (int jj = 0; jj < ddd[ii].length; ++jj)
            {   double mean = ddd[ii][jj][0];
                double stdev = ddd[ii][jj][1];
                System.out.println("    <band number = \"" + (jj+1) + "\" mean = \"" + mean + "\" stdev = \"" + stdev + "\"/>");
            }
            System.out.println("</class>");
        }
    }
}
