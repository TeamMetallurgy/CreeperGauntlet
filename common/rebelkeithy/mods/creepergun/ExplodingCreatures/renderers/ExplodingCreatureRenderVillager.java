package rebelkeithy.mods.creepergun.ExplodingCreatures.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityVillager;

import org.lwjgl.opengl.GL11;

import rebelkeithy.mods.creepergun.api.IExplodingCreatureRendererHelper;
import rebelkeithy.mods.creepergun.api.IRenderAccess;

public class ExplodingCreatureRenderVillager implements IExplodingCreatureRendererHelper
{

	@Override
	public void preRenderCallback(IRenderAccess renderer, EntityLiving par1EntityLiving, float par2) 
	{
        float f1 = 0.9375F;

        if (((EntityVillager) par1EntityLiving).getGrowingAge() < 0)
        {
            f1 = (float)((double)f1 * 0.5D);
            //renderer.shadowSize = 0.25F;
        }
        else
        {
            //this.shadowSize = 0.5F;
        }

        GL11.glScalef(f1, f1, f1);
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
