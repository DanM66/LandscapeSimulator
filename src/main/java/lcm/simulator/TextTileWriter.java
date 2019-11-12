package lcm.simulator;

public class TextTileWriter implements TileWriterInterface
{
    public TextTileWriter()
    {
        
    }

    public void writeTile(Tile t)
    {
        System.out.println(t.toString());
    }

}
