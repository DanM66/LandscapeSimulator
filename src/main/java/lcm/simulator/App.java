package lcm.simulator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
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

    public int run(String[] args) throws SQLException
    {
        Config cg = Config.getInstance();

        for (int ii = 0; ii < args.length; ++ii)
        {
            System.out.println(args[ii]);

            if ("-db".equals(args[ii])) cg.setUrl(args[ii + 1]);

            if ("-user".equals(args[ii])) cg.setUser(args[ii + 1]);

            if ("-r".equals(args[ii])) cg.setRaster(args[ii + 1]);

            if ("-password".equals(args[ii])) cg.setPassword(args[ii + 1]);

            if ("-debug".equals(args[ii])) cg.setDebug(true);
            
            if ("-writer".equals(args[ii])) cg.setWriter(args[ii+1]);
            
            if ("-output".equals(args[ii])) cg.setOutput(args[ii+1]);
            
            if ("-pixeltype".equals(args[ii])) cg.setPixelType((args[ii+1]));
            
            if ("-landcover".equals(args[ii])) cg.setLandCover((args[ii+1]));
            
            if ("-nbands".equals(args[ii])) cg.setNumBands(Integer.parseInt(args[ii+1]));
            
            if ("-seed".equals(args[ii])) cg.setSeed(Integer.parseInt(args[ii]));
            
            if ("-nthreads".equals(args[ii])) cg.setNumThreads(Integer.parseInt(args[ii + 1]));

        }

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String sqlString;

        try
        {
            con = ConnectionPool.getConnection(cg.getUrl(), cg.getUser(), cg.getPassword());

            TileWriterInterface tw = TileWriterFactory.getWriter(cg);
            BandType bt = BandTypeFactory.getBandType(cg);
            tw.setBandType(bt);
            
            sqlString = "select distinct rid from " +  cg.getRaster() + " order by rid asc";
            System.out.println(sqlString);
            
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sqlString);
            
            ExecutorService executor = Executors.newFixedThreadPool(cg.getNumThreads());
            while (rs.next())
            {
                Tile inTile = new RasterTile(rs.getInt(1), cg.getRaster());
                Tile outTile = new Tile(inTile.getId(), cg.getNumBands(), inTile.getWidth(), inTile.getHeight());
                executor.execute(tw.getWorker(inTile,outTile));
                //executor.execute(tw.getWorker(new Tile(rs.getInt(1), cd.getNumBands(), cd.getTileXdim(), cd.getTileYdim())));
            }
            executor.shutdown();
            while (!executor.isTerminated()) {}
            
            System.out.println("\nFinished all threads");
            
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            if (rs != null)  rs.close();
            if (con != null) con.close();
        }

        return 1;
    }
}
