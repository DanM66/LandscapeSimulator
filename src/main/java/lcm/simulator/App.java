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
        Config cd = Config.getInstance();

        for (int ii = 0; ii < args.length; ++ii)
        {
            System.out.println(args[ii]);

            if ("-db".equals(args[ii])) cd.setUrl(args[ii + 1]);

            if ("-user".equals(args[ii])) cd.setUser(args[ii + 1]);

            if ("-r".equals(args[ii])) cd.setRaster(args[ii + 1]);

            if ("-password".equals(args[ii])) cd.setPassword(args[ii + 1]);

            if ("-debug".equals(args[ii])) cd.setDebug(true);
            
            if ("-writer".equals(args[ii])) cd.setWriter(args[ii+1]);
            
            if ("-output".equals(args[ii])) cd.setOutput(args[ii+1]);
            
            if ("-nbands".equals(args[ii])) cd.setNumBands(Integer.parseInt(args[ii+1]));
            
            if ("-seed".equals(args[ii])) cd.setRandom(new Random(Integer.parseInt(args[ii])));
            
            if ("-nthreads".equals(args[ii])) cd.setNumThreads(Integer.parseInt(args[ii + 1]));

        }

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String sqlString;

        try
        {
            con = ConnectionPool.getConnection(cd.getUrl(), cd.getUser(), cd.getPassword());

            TileWriterInterface tw = TileWriterFactory.getWriter(cd);
            TileSetterInterface ts = TileSetterFactory.getTileSetter(cd);
            tw.setTileSetter(ts);
            
            sqlString = "select distinct rid from " +  cd.getOutput() + " order by rid asc";
            System.out.println(sqlString);
            
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sqlString);
            
            ExecutorService executor = Executors.newFixedThreadPool(cd.getNumThreads());
            while (rs.next())
            {
                executor.execute(tw.getWorker(new Tile(rs.getInt(1), cd.getNumBands(), cd.getTileXdim(), cd.getTileYdim())));
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
