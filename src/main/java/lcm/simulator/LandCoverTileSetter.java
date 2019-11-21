package lcm.simulator;

public class LandCoverTileSetter implements TileSetterInterface
{
    AbstractPixel pixel = null;
    String lcTable = null;
    LandCoverTileSetter(AbstractPixel pixel, String landCoverTable)
    {
        this.pixel = pixel;
        this.lcTable = landCoverTable;
        
    }

    @Override
    public void set(Tile t)
    {
        // TODO Auto-generated method stub

    }

}
