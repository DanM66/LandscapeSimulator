package lcm.simulator;

public final class TileSetterFactory
{

    public static TileSetterInterface getTileSetter(Config cg)
    {
        if ("random".equals(cg.getFileSetterType()))
            return new RandomTileSetter();
        
        else
            return null;
    }
}
