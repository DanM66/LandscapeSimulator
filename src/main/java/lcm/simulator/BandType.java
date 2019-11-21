package lcm.simulator;

import java.util.Random;

@SuppressWarnings("serial")
public abstract class BandType extends Random
{
    
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
}
