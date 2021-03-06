package lcm.simulator;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RasterTile extends Tile
{


    /**
     * Constructs a tile based on a postgis raster
     * 
     * @param c
     * @param rid
     * @param raster
     * @param nbands
     * @param width
     * @param height
     * @throws SQLException
     */
    RasterTile(int rid, String raster) throws SQLException
    {
        this.mRid = rid;
        
        Connection c = null;

        try
        {
            c = ConnectionPool.getConnection();
            mTile = setTile(c, raster, rid);
        }
        finally
        {
            if (c != null)
                c.close();
        }

    }

    private Number[][][] setTile(Connection c, String rname, int rid) throws SQLException
    {

        Statement st = null;
        ResultSet rs = null;
        Number[][][] tile;
        try
        {
              
            tile = new Number[1][][];

            String queryString = "select (st_dumpvalues(rast)).* from " + rname + " where rid = " + rid;
            st = c.createStatement();
            rs = st.executeQuery(queryString);


            // If training observations are embedded as the last band in the raster we need to ignore it.
            while (rs.next())
            {
                int band = rs.getInt(1);
                Array a = rs.getArray(2);
               
                tile[band-1] = (Number[][]) a.getArray();
            }

        }
        finally
        {
            if (st != null)
                st.close();
            if (rs != null)
                rs.close();
        }

        return tile;
    }

}
