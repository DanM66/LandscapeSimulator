package lcm.simulator;

import java.sql.Connection;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final App app = new App();
    
    public static void main (String[] args)
    {
        int result = app.run(args);
        System.out.println("Execution status: " + result);
    }
    
    public int run( String[] args )
    {
        for(int ii = 0; ii< args.length; ++ii)
        {
            System.out.println(args[ii]);
        }
        
        Connection c = null;
        try
        {
            
        }
        finally
        {
            
        }
        
        return 1;
    }
}
