package lcm.simulator;

public class RandomTileSetter implements TileSetterInterface
{

    @Override
    public void set(Tile t)
    {
        int xx = t.getWidth();
        int yy = t.getHeight();
        int n = t.getNumBands();
        AbstractPixel pixel = new RandomUniformPixel(254, n);
        
        
        for (int x = 0; x < xx; ++x)
        {
            for (int y = 0; y < yy; ++y)
            {
                t.setPixel(pixel.next(),x,y);
            }
        }
            
       
        
    }

}
