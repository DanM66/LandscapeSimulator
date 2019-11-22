package lcm.simulator;

public final class BandTypeFactory
{
    
    public static BandType getBandType(Config cg)
    {
        
        if ("gaussian".equals(cg.getPixelType()))
            return new RandomGaussian(cg.getSeed(), cg.getBandSpectra());
        
        return null;
    }

}
