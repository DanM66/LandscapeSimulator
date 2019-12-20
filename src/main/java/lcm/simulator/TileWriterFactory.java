package lcm.simulator;

/**
 * Gets a tile writer.  Currently only a dbwriter is fully implemented. 
 * @author User
 *
 */
public final class TileWriterFactory
{
    public static TileWriterInterface getWriter(Config cd) throws Exception
    {
     
        String writer = cd.getWriter();
        
        if ("dbwriter".equalsIgnoreCase(writer))
        {        
            return new DbTileWriter();
        }
        
        if ("txt".equals(writer))
        {
            return new TextTileWriter();
        }
        
        if (writer == null)
            return null;
        
        throw new WriterFactoryException("Writer type not found");
    }
}

@SuppressWarnings("serial")
class WriterFactoryException extends Exception
{
    WriterFactoryException(String str)
    {
        super(str);
    }
}