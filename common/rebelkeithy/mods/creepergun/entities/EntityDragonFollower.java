package rebelkeithy.mods.creepergun.entities;

import rebelkeithy.mods.creepergun.utils.CylindricalCoords;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;


public class EntityDragonFollower extends Entity
{
	int entityDragonID;
	EntityDragon dragon;
	int timer = 20;
	
	public EntityDragonFollower(World par1World) 
	{
		super(par1World);
		entityDragonID = -1;
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
	
	public void link(EntityDragon entity)
	{
		dragon = entity;
		entityDragonID = dragon.entityId;

		this.posX = dragon.dragonPartHead.posX;
		this.posY = dragon.dragonPartHead.posY;
		this.posZ = dragon.dragonPartHead.posZ;
	}
	
	//TODO: Find replacement if required
	/*@Override
	public String getTexture()
	{
		return "/mob/zombie.png";
	}*/
	
	@Override
	public boolean isInvisible()
	{
		return true;
	}
	
	@Override
    public void onUpdate()
    {
		if(dragon == null && entityDragonID != -1)
		{
			Entity entity = worldObj.getEntityByID(entityDragonID);
			if(entity instanceof EntityDragon)
			{
				dragon = (EntityDragon) entity;
			}
			
			if(dragon != null)
			{
				entityDragonID = dragon.entityId;
			}
		}
		
		if(dragon == null)
		{
			AxisAlignedBB box = AxisAlignedBB.getBoundingBox(this.posX - 1, this.posY - 1, this.posZ - 1, this.posX + 1, this.posY + 1, this.posZ + 1);
			EntityDragonPart entity = (EntityDragonPart) worldObj.findNearestEntityWithinAABB(EntityDragonPart.class, box.expand(20, 20, 20), this);

			System.out.println("searching " + entity);
			if(entity != null)
			{
				dragon = (EntityDragon) entity.entityDragonObj;
			}
		}

		
		if(dragon == null)
		{
			AxisAlignedBB box = AxisAlignedBB.getBoundingBox(this.posX - 1, this.posY - 1, this.posZ - 1, this.posX + 1, this.posY + 1, this.posZ + 1);
			EntityDragon entity = (EntityDragon) worldObj.findNearestEntityWithinAABB(EntityDragon.class, box.expand(20, 20, 20), this);

			System.out.println("searching " + entity);
			if(entity != null)
			{
				dragon = entity;
			}
		}
		
		if(dragon != null && !worldObj.isRemote)
		{
			this.posX = dragon.dragonPartHead.posX + dragon.motionX*2;
			this.posY = dragon.dragonPartHead.posY + dragon.motionY*2 + 4;
			this.posZ = dragon.dragonPartHead.posZ + dragon.motionZ*2;
			
			timer--;
			if(timer <= 0)
			{
				timer = 12;
				EntityCreeper creeper = new EntityCreeper(worldObj);
				
				CylindricalCoords cyl = new CylindricalCoords(1, 0, dragon.dragonPartHead.rotationYaw);
				
				double forwardMotionX = cyl.x;
				double forwardMotionZ = cyl.y;
				
				creeper.setPosition(dragon.dragonPartHead.posX + forwardMotionX, dragon.dragonPartHead.posY, dragon.dragonPartHead.posZ + forwardMotionZ);
				creeper.setAngles(dragon.rotationYaw, dragon.rotationPitch);
				creeper.motionX = (dragon.motionX * 2) + forwardMotionX * 0.6;
				creeper.motionY = dragon.motionY;
				creeper.motionZ = (dragon.motionZ * 2) + forwardMotionZ * 0.6;
				
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
		
		entityDragonID = nbttagcompound.getInteger("EntityDragonID");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) 
	{
		//super.writeToNBT(nbttagcompound);
		
		nbttagcompound.setInteger("EntityDragonID", entityDragonID);
	}
}
