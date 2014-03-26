package rebelkeithy.mods.creepergun.ExplodingCreatures;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import rebelkeithy.mods.creepergun.CreeperConfig;
import rebelkeithy.mods.creepergun.api.IExplodingCreatureRendererHelper;
import rebelkeithy.mods.creepergun.api.IExplodingCreatureType;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExplodingCreatureTypeBase implements IExplodingCreatureType 
{

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBase getModel(Entity entity) 
	{
		Render render = RenderManager.instance.getEntityClassRenderObject(entity.getClass());
		return ReflectionHelper.getPrivateValue(RenderLiving.class, (RenderLiving)render, "mainModel", "field_77045_g", "i");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTexture(Entity entity) 
	{
		return entity.getTexture();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IExplodingCreatureRendererHelper getRenderHelper(Entity entity) 
	{
		return null;
	}

	@Override
	public List getDrops(Entity entity) 
	{
		if(entity != null)
		{
			List<ItemStack> drops = CreeperConfig.dropTable.get(entity.getClass().getSimpleName());
			List<ItemStack> returnDrops = new ArrayList<ItemStack>();
	
	        for(ItemStack stack : drops)
	        {
	        	ItemStack dropStack = stack.copy();       	
	        	returnDrops.add(dropStack);
	        }
			
	        randomizeDropAmounts(returnDrops, 0.4f);
			
	        return returnDrops;
		}
		
		return new ArrayList<ItemStack>();
	}
	
	public void randomizeDropAmounts(List<ItemStack> drops, float randomness)
	{
        for(ItemStack stack : drops)
        {
        	ItemStack dropStack = stack.copy();
        	int amount = (int) (stack.stackSize * ((Math.random()*randomness) + (1-randomness/2.0f)));
        	amount = MathHelper.clamp_int(amount, 0, stack.getMaxStackSize());
        	
        	dropStack.stackSize = amount;        	
        }
	}

	@Override
	public int getExplosionSize(Entity entity) 
	{
		return 2;
	}

	@Override
	public boolean isFlying(Entity entity) 
	{
		return false;
	}

	@Override
	public boolean causesFlamingExplosion(Entity entity) 
	{
		return false;
	}

	@Override
	public void explosionCallback(Entity entity) {}
}
