package rebelkeithy.mods.creepergun.utils;

import java.util.Iterator;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CustomEntityItemStack extends EntityItem
{
	public CustomEntityItemStack(World worldObj)
	{
		super(worldObj);
	}
	
	public CustomEntityItemStack(World worldObj, double posX, double d, double posZ, ItemStack par1ItemStack)
	{
		super(worldObj, posX, d, posZ, par1ItemStack);
	}

    /**
     * Looks for other itemstacks nearby and tries to stack them together
     */
    private void searchForOtherItemsNearby()
    {
    }
    public boolean combineItems(EntityItem par1EntityItem)
    {
    	return false;
    }

	public void setMotion(double x, double y, double z)
	{
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
		
	}

}
