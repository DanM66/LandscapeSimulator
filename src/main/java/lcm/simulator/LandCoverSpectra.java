package lcm.simulator;

public class LandCoverSpectra
{
    public LandCoverSpectra(String initStr)
    {
        
    }

    int id;
    Tuple[] bandParams;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}

class Tuple
{
    double mean;
    double stdev;

    public double getMean()
    {
        return mean;
    }

    public void setMean(double mean)
    {
        this.mean = mean;
    }

    public double getStdev()
    {
        return stdev;
    }

    public void setStdev(double stdev)
    {
        this.stdev = stdev;
    }

}
