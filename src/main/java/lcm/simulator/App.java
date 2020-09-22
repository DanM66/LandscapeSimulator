package lcm.simulator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Reverse classifier raster simulator. Given a set of land cover classes this
 * application will create multi-layer raster with simulated reflectance values.
 * This is a multithreaded application. App behaviour is determined by an xml
 * configuration file. It is self-explanory.
 *
 */
public class App
{
    private static final App app = new App();

    public static App getInstance()
    {
        return app;
    }

    public static void main(String[] args)
    {
        int result = -1;
        try
        {
            result = getInstance().run(args);
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Execution status: " + result);
    }

    /**
     * This is where stuff happens.
     * 
     * @param args
     * @return
     * @throws SQLException
     */
    private int run(String[] args) throws SQLException
    {
        Config cg = Config.getInstance();
        cg.init(args[0]);

        Connection con = null;
        Statement  st  = null;
        ResultSet  rs  = null;
        String     sqlString;

        try
        {
            con = ConnectionPool.getConnection(cg.getUrl(), cg.getUser(), cg.getPassword());

            TileWriterInterface tw = TileWriterFactory.getWriter(cg);
            BandType            bt = BandTypeFactory.getBandType(cg);
            tw.setBandType(bt);

            sqlString = "select distinct rid from " + cg.getInput() + " order by rid asc";
            System.out.println(sqlString);

            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sqlString);

            ExecutorService executor = Executors.newFixedThreadPool(cg.getNumThreads());
            while (rs.next())
            {
                Tile inTile  = new RasterTile(rs.getInt(1), cg.getInput());
                Tile outTile = new Tile(inTile.getId(), cg.getNumBands(), inTile.getWidth(), inTile.getHeight());
                executor.execute(tw.getWorker(inTile, outTile));
            }
            executor.shutdown();
            while (!executor.isTerminated())
            {
            }

            System.out.println("\nFinished all threads");

        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

        return 1;
    }
}
