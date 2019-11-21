package lcm.simulator;


public interface TileWriterInterface
{
    public void writeTile(Tile t) throws Exception;
    public Runnable getWorker(Tile in, Tile out) throws Exception;
    public void setBandType(BandType r);
}