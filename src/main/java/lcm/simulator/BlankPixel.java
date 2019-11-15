package lcm.simulator;

public class BlankPixel implements PixelInterface
{
    private Number p[] ;
    private int m_nbands = -1;
    
    BlankPixel(Number[] n)
    {
        p = n;
        m_nbands = p.length;
    }
    
    public int getNumBands()
    {
        return m_nbands;
    }
    
    /**
     * Bands from 1 to n
     * @param ii
     * @return
     */
    public Number getBand(int ii)
    {
        if (ii <= m_nbands) return p[ii-1];
        return -999999;
    }

}