package lcm.simulator;

public class TextTileWriter implements TileWriterInterface
{

    public TextTileWriter()
    {

    }

    public void writeTile(Tile t) throws Exception
    {
        
            System.out.println(t.toString());
    }

    public Runnable getWorker(Tile t)
    {
        return new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    writeTile(t);
                }
                catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        };

    }

    @Override
    public void setTileSetter(TileSetterInterface t)
    {
        // TODO Auto-generated method stub
        
    }

}
