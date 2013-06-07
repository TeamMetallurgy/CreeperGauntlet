package rebelkeithy.mods.creepergun.ExplodingCreatures.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityVillager;

import org.lwjgl.opengl.GL11;

import rebelkeithy.mods.creepergun.api.IExplodingCreatureRendererHelper;
import rebelkeithy.mods.creepergun.api.IRenderAccess;

public class ExplodingCreatureRenderGhast implements IExplodingCreatureRendererHelper
{

	@Override
	public void preRenderCallback(IRenderAccess renderer, EntityLiving par1EntityLiving, float par2) 
	{
        float f1 = 0;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        f1 = 1.0F / (f1 * f1 * f1 * f1 * f1 * 2.0F + 1.0F);
        float f2 = (8.0F + f1) / 2.0F;
        float f3 = (8.0F + 1.0F / f1) / 2.0F;
        GL11.glScalef(f3, f2, f3);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
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
