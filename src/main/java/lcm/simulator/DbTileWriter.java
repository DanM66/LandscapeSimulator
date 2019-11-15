package lcm.simulator;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;


public class DbTileWriter implements TileWriterInterface, Runnable
{

    public DbTileWriter() throws SQLException
    {
        Config cd = Config.getInstance();
        String outRaster = cd.getOutput();
        String inRaster = cd.getRaster();
        int nBands = cd.getNumBands();

        if (Utils.relationExists(outRaster))
        {
           Utils.drop(outRaster);
        }
        Utils.createRasterFromTemplate(outRaster, inRaster, nBands);

    }

    public void writeTile(Tile t) throws SQLException
    {
        
        Number[][][] d = t.getTile();
        
        for (int band = 0; band < d.length; ++band)
        {
            
            StringBuffer strBuff = new StringBuffer();
            strBuff.append("[ " + Arrays.toString(d[band][0]));
            for (int jj = 1; jj < d[0].length; ++jj)
            {
                strBuff.append("," + Arrays.toString(d[band][jj]));
            }
            strBuff.append("]");
            
            String sqlStr = "update " + Config.getInstance().getOutput()
                    + " set rast = st_setvalues(rast, "
                    + band + ", 1, 1, ARRAY" + strBuff.toString().replaceAll("null", "NULL")
                    + "::int[][]) where rid = " + t.getId();
            
            Connection con = null;
            Statement st = null;
            
            try
            {
                con = ConnectionPool.getConnection();
                st = con.createStatement();
                st.execute(sqlStr);
                //System.out.println(sqlStr);
            }
            finally
            {
                if (st!=null) st.close();
                if (con!=null) con.close();
            }
        }
    }

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        
    }

}
