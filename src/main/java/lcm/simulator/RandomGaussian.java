package lcm.simulator;

import java.util.Map;

@SuppressWarnings("serial")
/**
 * Returns a random gaussian according to specified band parameters.
 * @author User
 *
 */
public class RandomGaussian extends BandType
{
    private Map<Integer,double[][]> classMeanStdev = null;
    
    public RandomGaussian(int seed, Map<Integer,double[][]> m, boolean _int)
    {
        super(seed);
        this.classMeanStdev = m;
        this._int = _int;
        
    }
    
    public double getBandDouble(int clazz, int band)
    {
        double d = 0;
        try
        {
            d = this.nextGaussian()*classMeanStdev.get(clazz)[band][1] + classMeanStdev.get(clazz)[band][0];
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return d; 
    }

  
    public int getBandInt(int clazz, int band)
    {
        return (int) Math.round(getBandDouble(clazz,band));
    }
       
}
