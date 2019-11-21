package lcm.simulator;

@SuppressWarnings("serial")
public class RandomGaussian extends BandType
{
    private double[][][] classMeanStdev = null;
    
    public RandomGaussian(int seed, double[][][] meanStdev)
    {
        super(seed);
        this.classMeanStdev = meanStdev;
       
    }
    
    public double getBandDouble(int clazz, int band)
    {
        return this.nextGaussian()*classMeanStdev[clazz][band][1] + classMeanStdev[clazz][band][0]; 
    }

  
    public int getBandInt(int clazz, int band)
    {
        return (int) Math.round(getBandDouble(clazz,band));
    }
       

}
