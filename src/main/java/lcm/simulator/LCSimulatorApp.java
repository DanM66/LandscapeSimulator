package lcm.simulator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class LCSimulatorApp 
{
    public static void main( String[] args ) throws SQLException
    {
        
        ConfigurationDetails c = ConfigurationDetails.getInstance();
        for (int ii = 0; ii < args.length; ++ii)
        {
           
            if ("-db".equals(args[ii]))
                c.setUrl(args[ii + 1]);

            if ("-user".equals(args[ii]))
                c.setUser(args[ii + 1]);
           
            if ("-password".equals(args[ii]))
                c.setPassword(args[ii + 1]);
            
        }
        
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try
        {
            con = ConnectionPool.getConnection(c.getUrl(), c.getUser(), c.getPassword());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (con != null)
                con.close();

        }
    }
}
