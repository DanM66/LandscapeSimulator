package lcm.simulator;

/**
 * Not yet implemented. Maybe one day we'll want to write an ascii raster.
 * Although I'm not quite sure how I'd make this work using the current tile
 * structure. Perhaps I'll delete this one day.
 * 
 * @author User
 *
 */
public class TextTileWriter implements TileWriterInterface
{

    public TextTileWriter()
    {

    }

    public void writeTile(Tile t) throws Exception
    {

        System.out.println(t.toString());
    }

    public Runnable getWorker(Tile in, Tile out)
    {
        return new Runnable()
        {
            public void run()
            {
                try
                {
                    // writeTile(t);
                }
                catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        };

    }

    public void setBandType(BandType r)
    {
        // TODO Auto-generated method stub

    }

}
