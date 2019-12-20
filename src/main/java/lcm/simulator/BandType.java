package lcm.simulator;

import java.util.Random;

@SuppressWarnings("serial")
/**
 * @author User
 *
 */
public abstract class BandType extends Random
{
    boolean _int = true;
    
    BandType(int seed)
    {
        super(seed);
    }
    public double getBandDouble(int clazz, int band)
    {
        return 0;
    }
    
    public int getBandInt(int clazz, int band)
    {
        return 0;
    }
    
    public Number getBandValue(int clazz, int band)
    {
        if (_int)
        {
            return getBandInt(clazz, band);
        }
        
        return getBandDouble(clazz, band);
    }
}
