package rebelkeithy.mods.creepergun.ExplodingCreatures.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySlime;

import org.lwjgl.opengl.GL11;

import rebelkeithy.mods.creepergun.api.IExplodingCreatureRendererHelper;
import rebelkeithy.mods.creepergun.api.IRenderAccess;

public class ExplodingCreatureRenderSlime implements IExplodingCreatureRendererHelper
{

	@Override
	public void preRenderCallback(IRenderAccess renderer, EntityLiving par1EntityLiving, float par2) 
	{
        float f1 = (float)((EntitySlime)par1EntityLiving).getSlimeSize();
        //float f2 = (par1EntitySlime.field_70812_c + (par1EntitySlime.field_70811_b - par1EntitySlime.field_70812_c) * par2) / (f1 * 0.5F + 1.0F);
        float f2 = 0;
        float f3 = 1.0F / (f2 + 1.0F);
        GL11.glScalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	@Override
	public int shouldRenderPass(IRenderAccess renderer, EntityLiving par1EntityLiving, int par2, float par3) 
	{
		EntitySlime slime = (EntitySlime) par1EntityLiving;
        if (slime.isInvisible())
        {
            return 0;
        }
        else if (par2 == 0)
        {
        	renderer.setRenderPassModel(new ModelSlime((int) slime.getSlimeSize()));
            GL11.glEnable(GL11.GL_NORMALIZE);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            return 1;
        }
        else
        {
            if (par2 == 1)
            {
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            return -1;
        }
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
