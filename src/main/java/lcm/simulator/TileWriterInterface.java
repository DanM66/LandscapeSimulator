package lcm.simulator;


public interface TileWriterInterface
{
    public void writeTile(Tile t) throws Exception;
    public Runnable getWorker(Tile t) throws Exception;
    public void setTileSetter(TileSetterInterface t);
}