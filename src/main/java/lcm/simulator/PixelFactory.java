package lcm.simulator;

public final class PixelFactory
{

    public static final PixelWriterInterface getWriter(String w) throws PixelFactoryException
    {
        if ("randomPixel".equalsIgnoreCase(w)) {
            return new RandomPixelWriter();
        }

        if ("simPixel".equals(w)) {
            return new SimPixelWriter();
        }

        if (w == null)
            return null;

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