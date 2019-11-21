package lcm.simulator;

public final class BandTypeFactory
{
    
    public static BandType getBandType(Config cg)
    {
        double [][][] ddd = (new Tester(25,3)).getBandStuff();
        
        if ("".equals(cg.getPixelType()))
            return null;
        
        return new RandomGaussian(1, ddd);
    }

}
