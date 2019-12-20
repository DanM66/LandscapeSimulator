package lcm.simulator;

@SuppressWarnings("serial")
public class RandomUniform extends BandType
{
    int[][] classBandMaxVal = null;;


    public RandomUniform(int seed, int[][] maxVal, boolean _int)
    {
        super(seed);
        this.classBandMaxVal = maxVal;
        this._int = _int;
    }
    
    
    public double getBandDouble(int clazz, int band)
    {
        return nextDouble()*classBandMaxVal[clazz][band];
    }

  
    public int getBandInt(int clazz, int band)
    {
        return nextInt(classBandMaxVal[clazz][band]);
    }
   
}
