package rebelkeithy.mods.creepergun.particles;

import java.util.List;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.src.ModLoader;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import rebelkeithy.mods.creepergun.entities.EntityExplodingCreature;

import cpw.mods.fml.client.FMLClientHandler;

public class ParticleCreeper2FX extends EntityFX
{
	public EntityExplodingCreature target;

	public ParticleCreeper2FX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
	{
		super(par1World, par2, par4, par6, par8, par10, par12);
        this.motionX = par8;
        this.motionY = par10;
        this.motionZ = par12;
        this.particleMaxAge = 20;
        this.noClip = true;
	}

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
    	if(target == null)
    	{
    		double scale = 5;
    		List<Entity> list = this.worldObj.getEntitiesWithinAABB(EntityExplodingCreature.class, AxisAlignedBB.getAABBPool().getAABB(posX - scale, posY - scale, posZ - scale, posX + scale, posY + scale, posZ + scale));

    		for(Entity entity : list)
    		{
    			if(target != null)
    			{
    				double distance = this.getDistanceSqToEntity(target);
    				double newDist = this.getDistanceSqToEntity(entity);
    				if(newDist < distance)
    				{
    					target = (EntityExplodingCreature) entity;
    				}
    			} else {
    				target = (EntityExplodingCreature) entity;
    			}
    		}
    	}
    	
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;


        double resistance = 0.95;
        this.motionX *= resistance;
        this.motionY *= resistance;
        this.motionZ *= resistance;
        
        if(target != null && target.entity != null && target.entity.boundingBox != null)
        {
        	double force = 0.07;
        	double dx = (((target.entity.boundingBox.minX + target.entity.boundingBox.maxX)/2.0) - posX);
        	double dy = (((target.entity.boundingBox.minY + target.entity.boundingBox.maxY)/2.0) - posY);
        	double dz = (((target.entity.boundingBox.minZ + target.entity.boundingBox.maxZ)/2.0) - posZ);
        	Vec3 accel = Vec3.createVectorHelper(dx, dy, dz);
        	if(accel.lengthVector() < 1)
        		setDead();
        	accel.normalize();
        	motionX += accel.xCoord * force * particleAge/10.0;
        	motionY += accel.yCoord * force * particleAge/10.0;
        	motionZ += accel.zCoord * force * particleAge/10.0;
        }
        
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
       
        if(target != null && target.entity != null && target.entity.boundingBox != null && target.entity.boundingBox.isVecInside(Vec3.createVectorHelper(posX, posY, posZ)))
        {
        	this.setDead();
        }
        /*
        this.motionX *= 1.85;
        this.motionY *= 1.85;
        this.motionZ *= 1.85;
    	*/
    	/*
    	this.posX += this.motionX;
    	this.posY += this.motionY;
    	this.posZ += this.motionZ;
    	*/
        
        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }
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
	    this.particleScale = (float) Math.sqrt(4 * (1.0F - var8 * var8 * 0.5F));

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
