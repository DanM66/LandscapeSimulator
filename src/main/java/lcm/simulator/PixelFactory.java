package lcm.simulator;

public final class PixelFactory
{

    public static final PixelInterface getPixel(String ptype, Number[] n) throws PixelFactoryException
    {
       
        if ("blankPixel".equals(ptype))
        {
            return new BlankPixel(n);
        }

        if (ptype == null) return null;

        throw new PixelFactoryException("Pixel type not found");
    }

}

@SuppressWarnings("serial")
class PixelFactoryException extends Exception
{
    PixelFactoryException(String str)
    {
        super(str);
    }
}