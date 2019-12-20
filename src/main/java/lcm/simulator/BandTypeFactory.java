package lcm.simulator;

/**
 * Gets the required output type.
 * @author User
 *
 */
public final class BandTypeFactory
{
    
    public static BandType getBandType(Config cg)
    {
        
        if ("gaussianint".equals(cg.getPixelType()))
            return new RandomGaussian(cg.getSeed(), cg.getBandSpectra(), true);
        
        if ("gaussianfloat".equals(cg.getPixelType()))
            return new RandomGaussian(cg.getSeed(), cg.getBandSpectra(), false);
        
        return null;
    }

}
