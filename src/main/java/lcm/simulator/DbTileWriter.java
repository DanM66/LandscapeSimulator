package lcm.simulator;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 * If you want to write your results to a postgis database use this tilewriter.
 * 
 * @author User
 *
 */
public class DbTileWriter implements TileWriterInterface
{
    BandType bt = null;
    Integer[] bands = null;

    public DbTileWriter() throws SQLException
    {
        Config cd        = Config.getInstance();
        String outRaster = cd.getOutput();
        String inRaster  = cd.getInput();
        bands  = cd.getTheBands();

        if (Utils.relationExists(outRaster))
        {
            Utils.drop(outRaster);
        }
        Utils.createRasterFromTemplate(outRaster, inRaster, bands.length);

    }

    public void writeTile(Tile theTile) throws Exception
    {

        Number[][][] d = theTile.getTile();

        for (int ii = 0; ii < d.length; ++ii)
        {

            StringBuffer strBuff = new StringBuffer();
            strBuff.append("[ " + Arrays.toString(d[ii][0]));
            for (int jj = 1; jj < d[0].length; ++jj)
            {
                strBuff.append("," + Arrays.toString(d[ii][jj]));
            }
            strBuff.append("]");

            String     sqlStr = "update " + Config.getInstance().getOutput() + " set rast = st_setvalues(rast, "
                    + (ii + 1) + ", 1, 1, ARRAY" + strBuff.toString().replaceAll("null", "NULL")
                    + "::int[][]) where rid = " + theTile.getId();

            Connection con    = null;
            Statement  st     = null;

            try
            {
                con = ConnectionPool.getConnection();
                st = con.createStatement();
                st.execute(sqlStr);
            }
            finally
            {
                if (st != null) st.close();
                if (con != null) con.close();
            }
        }
        System.out.println("finished: " + theTile.getId());
    }

    public Runnable getWorker(Tile in, Tile out) throws Exception
    {
        return new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Tile t = set(in, out);
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

    public Tile set(Tile in, Tile out)
    {
        int xx = in.getWidth();
        int yy = in.getHeight();

        for (int x = 0; x < xx; ++x)
        {
            for (int y = 0; y < yy; ++y)
            {
                // This is a single band
                int      n = in.getCell(x, y)[0].intValue();

                Number[] d = new Number[out.getNumBands()];

                for (int ii = 0; ii < d.length; ++ii)
                {
                    d[ii] = bt.getBandValue(n, bands[ii]);
                }

                out.setPixel(d, x, y);

            }
        }

        return out;
    }

    @Override
    public void setBandType(BandType r)
    {
        this.bt = r;
    }
}
