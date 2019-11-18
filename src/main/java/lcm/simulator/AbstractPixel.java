package lcm.simulator;

public abstract class AbstractPixel
{
    int nBands;
    int x, y;
    Number[] values;
    
    public AbstractPixel(int n)
    {
        values = new Number[n];
    }
    
    public int getnBands()
    {
        return nBands;
    }
    public void setnBands(int nBands)
    {
        this.nBands = nBands;
    }
    public int getX()
    {
        return x;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public int getY()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public Number[] getValues()
    {
        return values;
    }
    public void setValues(Number[] values)
    {
        this.values = values;
    }
   
    public int getNumBands() 
    {
        return nBands;
    }
    public Number getBand(int ii)
    {
        return values[ii];
    }
    
    public Number[] next()
    {
        return getValues();
    }

}
