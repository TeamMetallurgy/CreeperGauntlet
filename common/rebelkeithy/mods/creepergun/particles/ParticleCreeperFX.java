package rebelkeithy.mods.creepergun.particles;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.src.ModLoader;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ParticleCreeperFX extends EntityFX
{
	public Entity target;

	public ParticleCreeperFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
	{
		super(par1World, par2, par4, par6, par8, par10, par12);
        //this.motionX = par8 + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.4F);
        //this.motionY = par10 + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.4F);
        //this.motionZ = par12 + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.4F);
        this.particleMaxAge = (int)(1.5F / (this.rand.nextFloat() * 0.9F + 0.1F));
        this.noClip = true;
	}
	
	public void setTarget(Entity target)
	{
		this.target = target;
	}

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
    	super.onUpdate();

        this.motionX *= 0.8500000238418579D;
        this.motionY *= 0.9500000238418579D;
        this.motionZ *= 0.8500000238418579D;
        this.motionY -= 0.02;
    	
        if(target != null)
        {
	        double dx = target.posX - this.posX;
	        double dy = target.posY - this.posY;
	        double dz = target.posZ - this.posZ;
	        
	    	motionX += dx * 0.001;
	    	motionY += dy * 0.001;
	    	motionZ += dz * 0.001;
        }
        
    	/*
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY += 0.002D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.8500000238418579D;
        this.motionY *= 0.8500000238418579D;
        this.motionZ *= 0.8500000238418579D;

        if (this.worldObj.getBlockMaterial(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) != Material.water)
        {
            this.setDead();
        }

        if (this.particleMaxAge-- <= 0)
        {
            this.setDead();
        }
        */
    }

	public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
	{
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

	    Tessellator tessellator1 = new Tessellator();
	    tessellator1.startDrawingQuads();
	    tessellator1.setBrightness(getBrightnessForRender(f));

	    float f6 = (((float)particleAge + f) / (float)particleMaxAge) * 32F;
	    if(f6 < 0.0F)
	    {
	        f6 = 0.0F;
	    }
	    if(f6 > 1.0F)
	    {
	        f6 = 1.0F;
	    }

	    float var8 = ((float)this.particleAge + f) / (float)this.particleMaxAge;
	    this.particleScale = 1 * (1.0F - var8 * var8 * 0.5F);

        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, FMLClientHandler.instance().getClient().renderEngine.getTexture("/mods/CreeperGun/textures/particles/creeper.png"));
        
        float f0 = 0;//(float)(getParticleTextureIndex() % 16) / 16F;
	    float f7 = f0 + 1/8F;
	    float f8 = 1;//(float)(getParticleTextureIndex() / 16) / 16F;
	    float f9 = f8 + 1/8F;
	    float f10 = 0.1F * particleScale;
	    float f11 = (float)((prevPosX + (posX - prevPosX) * (double)f) - interpPosX);
	    float f12 = (float)((prevPosY + (posY - prevPosY) * (double)f) - interpPosY);
	    float f13 = (float)((prevPosZ + (posZ - prevPosZ) * (double)f) - interpPosZ);
	    float f14 = 1.0F;
	    //tessellator1.setColorOpaque_F(particleRed * f14, particleGreen * f14, particleBlue * f14);
	    //tessellator1.setColorOpaque_F(this.particleRed, this.particleGreen, this.particleBlue);
	    tessellator1.setColorRGBA(181, 230, 29, 150);
        //tessellator1.
	    tessellator1.addVertexWithUV(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10, f7, f9);
	    tessellator1.addVertexWithUV((f11 - f1 * f10) + f4 * f10, f12 + f2 * f10, (f13 - f3 * f10) + f5 * f10, f7, f8);
	    tessellator1.addVertexWithUV(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10, f0, f8);
	    tessellator1.addVertexWithUV((f11 + f1 * f10) - f4 * f10, f12 - f2 * f10, (f13 + f3 * f10) - f5 * f10, f0, f9);

	    tessellator1.draw();
	    GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, ModLoader.getMinecraftInstance().renderEngine.getTexture("/particles.png"));
	}
}
