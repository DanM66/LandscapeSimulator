package lcm.simulator;

import java.util.Random;

public class RandomUniformPixel extends AbstractPixel
{

    int maxVal;
    Config cd;
    Random r = null;
    public RandomUniformPixel(int maxVal, int n)
    {

        super(n);
        this.maxVal = maxVal;
        r = Config.getInstance().getRandom();
        
    }
    
    public Number[] next()
    {
        
        for (int ii = 0; ii < values.length; ++ii)
        {
            values[ii] = r.nextInt(maxVal) + 1;
        }
        
        return values;
    }

}
