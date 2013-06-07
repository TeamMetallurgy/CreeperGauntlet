package rebelkeithy.mods.creepergun.ExplodingCreatures;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLiving;
import rebelkeithy.mods.creepergun.api.IExplodingCreatureRendererHelper;
import rebelkeithy.mods.creepergun.api.IRenderAccess;

public class ExplodingCreatureRendererBase implements IExplodingCreatureRendererHelper
{

	@Override
	public void preRenderCallback(IRenderAccess renderer, EntityLiving par1EntityLiving, float par2) 
	{
		
	}

	@Override
	public int shouldRenderPass(IRenderAccess renderer, EntityLiving par1EntityLiving, int par2, float par3) 
	{
		return 0;
	}

	@Override
	public void renderEquippedItems(IRenderAccess renderer, EntityLiving par1EntityLiving, float par2) 
	{
		
	}

	@Override
	public ModelBase getRenderPassModel() 
	{
		return null;
	}
}
