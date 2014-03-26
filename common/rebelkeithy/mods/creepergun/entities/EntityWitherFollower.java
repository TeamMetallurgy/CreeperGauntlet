package rebelkeithy.mods.creepergun.entities;

import rebelkeithy.mods.creepergun.utils.CylindricalCoords;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;


public class EntityWitherFollower extends Entity
{
	int entityWitherID;
	EntityWither wither;
	int timer = 20;
	
	public EntityWitherFollower(World par1World) 
	{
		super(par1World);
		entityWitherID = -1;
		MinecraftForge.EVENT_BUS.register(this);
		timer = 20;
	}
	
	@ForgeSubscribe
	public void fallDamageListener(LivingHurtEvent event)
	{
		if(event.source == DamageSource.fall)
		{
			if(event.entityLiving instanceof EntityCreeper)
			{
				ItemStack stack = event.entityLiving.getHeldItem();
				System.out.println(stack);
				if(stack != null && stack.itemID == Item.feather.itemID)
				{
					event.ammount = 0;
				}
			}
		}
	}
	
	public void link(EntityWither entity)
	{
		wither = entity;
		entityWitherID = wither.entityId;

		this.posX = wither.posX;
		this.posY = wither.posY + 2;
		this.posZ = wither.posZ;
	}
	
	@Override
	public String getTexture()
	{
		return "/mob/zombie.png";
	}
	
	@Override
	public boolean isInvisible()
	{
		return true;
	}
	
	@Override
    public void onUpdate()
    {
		if(wither == null && entityWitherID != -1)
		{
			Entity entity = worldObj.getEntityByID(entityWitherID);
			if(entity instanceof EntityWither)
			{
				wither = (EntityWither) entity;
			}
			
			if(wither != null)
			{
				entityWitherID = wither.entityId;
			}
		}
		
		if(wither == null)
		{
			AxisAlignedBB box = AxisAlignedBB.getBoundingBox(this.posX - 1, this.posY - 1, this.posZ - 1, this.posX + 1, this.posY + 1, this.posZ + 1);
			EntityWither entity = (EntityWither) worldObj.findNearestEntityWithinAABB(EntityWither.class, box.expand(20, 20, 20), this);

			System.out.println("searching " + entity);
			if(entity != null)
			{
				wither = entity;
			}
		}
		
		if(wither.isDead)
		{
			this.setDead();
		}
		
		if(wither != null && !worldObj.isRemote)
		{
			this.posX = wither.posX + wither.motionX;
			this.posY = wither.posY + wither.motionY + 6;
			this.posZ = wither.posZ + wither.motionZ;
			
			timer--;
			if(timer <= 0)
			{
				if(Math.random() > 0.5)	
					timer = 20;
				else
					timer = 35;
				EntityCreeper creeper = new EntityCreeper(worldObj);
				creeper.setPosition(wither.posX + wither.motionX, wither.posY + wither.motionY + 2, wither.posZ + wither.motionZ);
				creeper.setAngles(wither.rotationYaw, wither.rotationPitch);
				
				CylindricalCoords cyl = new CylindricalCoords(1, 0, wither.rotationYaw);
				
				double forwardMotionX = cyl.x;
				double forwardMotionZ = cyl.y;
				
				creeper.motionX = wither.motionX + forwardMotionX * 0.6;
				creeper.motionY = wither.motionY * 2 + 0.2;
				creeper.motionZ = wither.motionZ + forwardMotionZ * 0.6;
				
				ItemStack feather = new ItemStack(Item.feather);

				creeper.setCurrentItemOrArmor(0, feather);
				
				worldObj.spawnEntityInWorld(creeper);
			}
		}
    }

	@Override
	protected void entityInit() 
	{
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) 
	{
		//super.readFromNBT(nbttagcompound);
		
		entityWitherID = nbttagcompound.getInteger("EntityDragonID");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) 
	{
		//super.writeToNBT(nbttagcompound);
		
		nbttagcompound.setInteger("EntityDragonID", entityWitherID);
	}
}
