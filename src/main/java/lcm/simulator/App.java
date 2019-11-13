package lcm.simulator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        ConfigurationDetails cd = ConfigurationDetails.getInstance();

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

        }

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try
        {

            con = ConnectionPool.getConnection(cd.getUrl(), cd.getUser(), cd.getPassword());
            
            TileWriterInterface tw = WriterFactory.getWriter(cd.getWriter());

        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            if (con!=null) con.close();
        }

        return 1;
    }
}
