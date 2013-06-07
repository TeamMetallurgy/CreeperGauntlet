package rebelkeithy.mods.creepergun.ExplodingCreatures.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.EntityLiving;

import org.lwjgl.opengl.GL11;

import rebelkeithy.mods.creepergun.api.IExplodingCreatureRendererHelper;
import rebelkeithy.mods.creepergun.api.IRenderAccess;

public class ExplodingCreatureRenderCaveSpider implements IExplodingCreatureRendererHelper
{

	@Override
	public void preRenderCallback(IRenderAccess renderer, EntityLiving par1EntityLiving, float par2) 
	{
        GL11.glScalef(0.7f, 0.7f, 0.7f);
	}

	@Override
	public int shouldRenderPass(IRenderAccess renderer, EntityLiving par1EntityLiving, int par2, float par3) 
	{
        if (par2 != 0)
        {
            return -1;
        }
        else
        {
        	renderer.loadTexture("/mob/spider_eyes.png");
            float f1 = 1.0F;
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

            if (par1EntityLiving.isInvisible())
            {
                GL11.glDepthMask(false);
            }
            else
            {
                GL11.glDepthMask(true);
            }

            char c0 = 61680;
            int j = c0 % 65536;
            int k = c0 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
            return 1;
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
