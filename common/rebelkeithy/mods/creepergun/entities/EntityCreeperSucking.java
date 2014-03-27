package rebelkeithy.mods.creepergun.entities;

import rebelkeithy.mods.particleregistry.ParticleRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityCreeperSucking extends EntityLiving
{
	double targetX;
	double targetY;
	double targetZ;
	int age;
	int shrinkTime;
	
	double stepX;
	double stepY;
	double stepZ;
	private ItemStack itemstack;
	
	public EntityCreeperSucking(World world)
	{
		super(world);
	}
	
	public EntityCreeperSucking(World par1World, double x, double y, double z, double tx, double ty, double tz)
	{
		super(par1World);
		this.setPosition(x, y, z);
		this.setHealth(1);
		targetX = tx;
		targetY = ty;
		targetZ = tz;		
		age = 0;
		shrinkTime = 5;
		
		stepX = (targetX - posX)/(float)shrinkTime;
		stepY = (targetY - posY)/(float)shrinkTime;
		stepZ = (targetZ - posZ)/(float)shrinkTime;
		
	}

    public boolean func_94056_bM()
    {
    	return false;
    }
    
	public boolean func_94062_bN()
    {
    	return false;
    }
    
    public void onUpdate()
    {
    	/*
    	motionX = (targetX - posX)/(float)shrinkTime;
    	motionY = (targetY - posY)/(float)shrinkTime;
    	motionZ = (targetZ - posZ)/(float)shrinkTime;
    	
    	posX += motionX;
    	posY += motionY;
    	posZ += motionZ;*/
    	
    	posX += stepX;
    	posY += stepY;
    	posZ += stepZ;
    	
    	//ParticleRegistry.spawnParticle("Creeper", worldObj, posX, posY+2, posZ, Math.random()*0.1, Math.random()*0.1, Math.random()*0.1);

		//this.spawnCreeperParticles();
		
    	age++;
    	if(age == shrinkTime)
    	{
    		if(itemstack != null)
    		{
    			//itemstack.setItemDamage(itemstack.getItemDamage() & ~3);
    			//itemstack.setItemDamage(itemstack.getItemDamage() | 1);
    		}
			worldObj.playSoundEffect((double)posX, (double)posY, (double)posZ, "fire.ignite", 1.0F, (float) (Math.random() * 0.4F + 0.8F));
    		if(worldObj.isRemote)
    		{
    			this.spawnCreeperParticles();
    		}
    	}
    	
    	if(age > shrinkTime+5)
    	{
    		this.setDead();
    	}
    }

    /**
     * Spawns an explosion particle around the Entity's location
     */
    public void spawnCreeperParticles()
    {
        for (int i = 0; i < 40; ++i)
        {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            double d3 = 10.0D;
        	ParticleRegistry.spawnParticle("Creeper", worldObj, posX - stepX*0.5f, posY+0.75f, posZ - stepZ*0.5f, Math.random()*0.08, Math.random()*0.16-0.08, Math.random()*0.08);
            //ParticleRegistry.spawnParticle("Creeper", worldObj, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width - d0 * d3, this.posY + (double)(this.rand.nextFloat() * this.height) - d1 * d3, this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width - d2 * d3, d0, d1, d2);
        }
    }

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) 
	{
		super.readFromNBT(nbttagcompound);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) 
	{
		super.writeToNBT(nbttagcompound);
	}

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return getScale(0) * this.height / 2.0F;
    }

	public float getScale(float partialTickTime)
	{
		//return ((shrinkTime - (age+partialTickTime)))/((float)shrinkTime * 2f);
		return 1 - (age+partialTickTime)/((float)shrinkTime * 1.25f);
	}

	public double getPosX(double partialTickTime)
	{
		//return (partialTickTime)*(targetX-posX)/shrinkTime;
		//return partialTickTime*stepX;
		return 0;
	}

	public double getPosY(double partialTickTime)
	{
		return 0.8 * (1-getScale((float) partialTickTime));// + (partialTickTime)*(targetY-posY)/shrinkTime;
		//return partialTickTime*stepY;
		//return 0;
	}

	public double getPosZ(double partialTickTime)
	{
		//return (partialTickTime)*(targetZ-posZ)/shrinkTime;
		//return partialTickTime*stepZ;
		return 0;
	}

	public void linkItem(ItemStack itemstack)
	{
		this.itemstack = itemstack; 
		
	}
	
	@Override
	protected void applyEntityAttributes() 
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(1.0D);
		
	}

}
