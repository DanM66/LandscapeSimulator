package lcm.simulator;

public class DbTileWriter implements TileWriterInterface
{

    public DbTileWriter()
    {

    }

    public void writeTile(Tile t)
    {
        System.out.println(t.toString());
    }

}
