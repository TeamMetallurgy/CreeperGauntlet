package rebelkeithy.mods.creepergun.utils;

public class SphericalCoords
{
	public double x;
	public double y;
	public double z;
	
	public SphericalCoords(double rho, double phi, double theta)
	{
		double S = rho*Math.sin(phi);
    	z = S * Math.cos(theta);
    	y = rho * Math.cos(phi);
    	x = -S * Math.sin(theta);
	}
	
}
