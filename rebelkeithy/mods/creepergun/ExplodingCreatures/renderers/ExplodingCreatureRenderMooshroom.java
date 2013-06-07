package rebelkeithy.mods.creepergun.ExplodingCreatures.renderers;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityVillager;

import org.lwjgl.opengl.GL11;

import rebelkeithy.mods.creepergun.api.IExplodingCreatureRendererHelper;
import rebelkeithy.mods.creepergun.api.IRenderAccess;

public class ExplodingCreatureRenderMooshroom implements IExplodingCreatureRendererHelper
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
        if (!par1EntityLiving.isChild())
        {
            renderer.loadTexture("/terrain.png");
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glPushMatrix();
            GL11.glScalef(1.0F, -1.0F, 1.0F);
            GL11.glTranslatef(0.2F, 0.4F, 0.5F);
            GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
            renderer.getRenderBlocks().renderBlockAsItem(Block.mushroomRed, 0, 1.0F);
            GL11.glTranslatef(0.1F, 0.0F, -0.6F);
            GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
            renderer.getRenderBlocks().renderBlockAsItem(Block.mushroomRed, 0, 1.0F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            ((ModelQuadruped)renderer.getMainModel()).head.postRender(0.0625F);
            GL11.glScalef(1.0F, -1.0F, 1.0F);
            GL11.glTranslatef(0.0F, 0.75F, -0.2F);
            GL11.glRotatef(12.0F, 0.0F, 1.0F, 0.0F);
            renderer.getRenderBlocks().renderBlockAsItem(Block.mushroomRed, 0, 1.0F);
            GL11.glPopMatrix();
            GL11.glDisable(GL11.GL_CULL_FACE);
        }
	}

	@Override
	public ModelBase getRenderPassModel() 
	{
		return null;
	}
	
}
