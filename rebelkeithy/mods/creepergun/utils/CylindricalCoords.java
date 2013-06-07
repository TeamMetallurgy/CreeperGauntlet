package rebelkeithy.mods.creepergun.utils;

public class CylindricalCoords
{
	public double x;
	public double y;
	public double z;
	
	public CylindricalCoords(double rho, double height, double theta)
	{
    	z = rho * Math.cos(theta);
    	y = height;
    	x = -rho * Math.sin(theta);
	}
	
}
