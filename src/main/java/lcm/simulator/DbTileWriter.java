package lcm.simulator;

import java.sql.SQLException;

public class DbTileWriter implements TileWriterInterface
{

    public DbTileWriter() throws SQLException
    {
        ConfigurationDetails cd = ConfigurationDetails.getInstance();
        String outRaster = cd.getOutput();
        String inRaster = cd.getRaster();
        int nBands = cd.getNumBands();

        if (Utils.relationExists(outRaster))
        {
           Utils.drop(outRaster);
        }
        Utils.createRasterFromTemplate(outRaster, inRaster, nBands);

    }

    public void writeTile(Tile t)
    {
        System.out.println(t.toString());
    }

}
