package rebelkeithy.mods.creepergun.entities;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import rebelkeithy.mods.creepergun.CreeperConfig;
import rebelkeithy.mods.creepergun.CreeperGauntletMod;
import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;
import rebelkeithy.mods.creepergun.api.IExplodingCreatureType;
import rebelkeithy.mods.creepergun.models.ModelExplodingBat;
import rebelkeithy.mods.creepergun.models.ModelExplodingIronGolem;
import rebelkeithy.mods.creepergun.models.ModelExplodingMagmaCube;
import rebelkeithy.mods.creepergun.models.ModelExplodingOcelot;
import rebelkeithy.mods.creepergun.models.ModelExplodingSheep2;
import rebelkeithy.mods.creepergun.models.ModelExplodingSkeleton;
import rebelkeithy.mods.creepergun.models.ModelExplodingWolf;
import rebelkeithy.mods.creepergun.utils.CustomEntityItemStack;
import rebelkeithy.mods.creepergun.utils.SphericalCoords;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityExplodingCreature extends EntityLiving
{	    
    public Entity entity;
    
    public IExplodingCreatureType creatureType;
    
    private int timeSinceIgnited;
    private int fuseTime = 30;

	private int delay = 10;
	
	public EntityExplodingCreature(EntityLiving entity)
	{
		super(entity.worldObj);
		this.setPositionAndRotation(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);

		this.rotationPitch = entity.rotationPitch;
		this.rotationYaw = entity.rotationYaw;
		this.rotationYawHead = entity.rotationYawHead;

		entity.setInvisible(true);

		for(int i = 0; i <= 4; i++)
			this.setCurrentItemOrArmor(i, entity.getCurrentItemOrArmor(i));
		
		this.entity = entity;		
		initCreatureType();
	}

    @SideOnly(Side.CLIENT)
	public ModelBase getModel()
	{
		this.rotationPitch = entity.rotationPitch;
		this.rotationYaw = entity.rotationYaw;
		this.rotationYawHead = ((EntityLiving)entity).rotationYawHead;
		
    	return creatureType.getModel(entity);
	}
    
    public void initCreatureType()
    {
    	if(entity != null && CreeperGauntletMod.instance.explodingCreatureMap.containsKey(entity.getClass().getSimpleName()))
    	{
    		creatureType = CreeperGauntletMod.instance.explodingCreatureMap.get(entity.getClass().getSimpleName());
    	}
    	else 
    	{
    		creatureType = new ExplodingCreatureTypeBase();
    	}
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
    	if(entity != null)
    	{
			this.rotationPitch = entity.rotationPitch;
			this.rotationYaw = entity.rotationYaw;
			this.rotationYawHead = ((EntityLiving)entity).rotationYawHead;
    	}
    	
    	if(creatureType == null)
    		initCreatureType();
		
        if (this.isEntityAlive())
        {

            delay--;
            if(delay <= 0)
            {
                int i = 1;

                if (i > 0 && this.timeSinceIgnited == 0)
                {
                    this.playSound("random.fuse", 1.0F, 0.5F);
                }
                
	            this.timeSinceIgnited += i;
	
	            if (this.timeSinceIgnited < 0)
	            {
	                this.timeSinceIgnited = 0;
	            }
	
	            if (this.timeSinceIgnited >= this.fuseTime)
	            {
	                this.timeSinceIgnited = this.fuseTime;
	
	                if (!this.worldObj.isRemote)
	                {
	                    boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
	                    boolean flaming = creatureType.causesFlamingExplosion(entity);
	                    int explosionSize = creatureType.getExplosionSize(entity);
	                    this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, explosionSize, flaming, flag);
	                    
	                    creatureType.explosionCallback(entity);
	                    
	                    if(entity instanceof EntityPlayer)
	                    {
	                    	EntityPlayer player = (EntityPlayer)entity;
	                    	for(int n = 0; n < player.inventory.mainInventory.length; n++)
	                    	{
	                    		if(player.inventory.mainInventory[n] != null)
	                    		{
	                    			dropItem(player, player.inventory.mainInventory[n], 1);
	                    			player.inventory.mainInventory[n] = null;
	                    		}
	                    	}
	                    	
	                    	player.attackEntityFrom(DamageSource.magic, 100);
	                    	
	                    } else {
	                    	this.dropItems();
	                    }
	                    
	                    this.setDead();
	                }
	
	                this.setDead();
	            }
	        }
        }

        super.onUpdate();
        
        if(creatureType.isFlying(entity))
        {
            this.motionY *= 0.6000000238418579D;
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);

        par1NBTTagCompound.setShort("Fuse", (short)this.fuseTime);
        
        NBTTagCompound entityNBT = new NBTTagCompound();
        if(entity != null)
        entity.writeToNBT(entityNBT);
        par1NBTTagCompound.setCompoundTag("ContainedEntity", entityNBT);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        
        if (par1NBTTagCompound.hasKey("Fuse"))
        {
            this.fuseTime = par1NBTTagCompound.getShort("Fuse");
        }
        
        NBTTagCompound entityNBT = par1NBTTagCompound.getCompoundTag("ContaindedEntity");
        entity = EntityList.createEntityFromNBT(entityNBT, worldObj);
        initCreatureType();
    }
    
    public void dropItems()
    {
        List<ItemStack> drops1 = creatureType.getDrops(entity);
        
        for(ItemStack stack : drops1)
        {
        	ItemStack dropStack = stack.copy();
        	int amount = stack.stackSize;
        	dropStack.stackSize = 1;
        	
        	for(int j = 0; j < amount; j++)
        		this.dropItem(this, dropStack.copy(), 1);
        }
        
        
        if(entity.getClass() == EntitySilverfish.class && !worldObj.isRemote)
    	{
    		for(int i = 0; i < 10; i++)
    		{
    			Entity newEntity = new EntitySilverfish(entity.worldObj);
    			newEntity.setPosition(entity.posX, entity.posY, entity.posZ);
    			
    			SphericalCoords sph = new SphericalCoords(Math.random(), Math.random(), Math.random());
    			newEntity.motionX = sph.x * 0.2;
    			newEntity.motionY = Math.abs(sph.y) + 0.1;
    			newEntity.motionZ = sph.z * 0.2;
    			
    			entity.worldObj.spawnEntityInWorld(newEntity);
    		}
    	}
        
        if(entity.getClass() == EntityBat.class && !worldObj.isRemote)
    	{
    		for(int i = 0; i < 10; i++)
    		{
    			Entity newEntity = new EntityBat(entity.worldObj);
    			newEntity.setPosition(entity.posX, entity.posY, entity.posZ);
    			
    			SphericalCoords sph = new SphericalCoords(Math.random(), Math.random(), Math.random());
    			newEntity.motionX = sph.x * 0.2;
    			newEntity.motionY = Math.abs(sph.y) + 0.1;
    			newEntity.motionZ = sph.z * 0.2;
    			
    			entity.worldObj.spawnEntityInWorld(newEntity);
    		}
    	}
    }
    
    @Override
    public String getTexture()
    {
    	return creatureType.getTexture(entity);
    }

    /**
     * Drops an item at the position of the entity.
     */
    public void dropItem(Entity entity, ItemStack par1ItemStack, float par2)
    {
    	if(entity.worldObj.isRemote)
    		return;
        CustomEntityItemStack entityitem = new CustomEntityItemStack(entity.worldObj, entity.posX, entity.posY + (double)par2, entity.posZ, par1ItemStack);
        entityitem.setMotion(Math.random()*0.6-0.3, Math.random()*0.6+0.2, Math.random()*0.6-0.3);
        entityitem.delayBeforeCanPickup = 10;
        entity.worldObj.spawnEntityInWorld(entityitem);
    }
	
	public EntityExplodingCreature(World par1World)
	{
		super(par1World);
	}

	@Override
	public int getMaxHealth()
	{
		return 100;
	}

    @SideOnly(Side.CLIENT)

    /**
     * Params: (Float)Render tick. Returns the intensity of the creeper's flash when it is ignited.
     */
    public float getCreeperFlashIntensity(float par1)
    {
        return ((float)(this.timeSinceIgnited) * par1) / (float)(this.fuseTime - 2);
    }

	public float getScale(float par2)
	{
		return 1;
	}

	public boolean getSheared()
	{
		if(entity instanceof EntitySheep)
			return ((EntitySheep) entity).getSheared();
		else
			return false;
	}

	public int getFleeceColor()
	{
		if(entity instanceof EntitySheep)
			return ((EntitySheep) entity).getFleeceColor();
		else
			return 0;
	}

	public int getSkeletonType()
	{
		if(entity instanceof EntitySkeleton)
			return ((EntitySkeleton) entity).getSkeletonType();
		else
			return 0;
	}

	public boolean getIsBatHanging()
	{
		if(entity instanceof EntityBat)
			return ((EntityBat) entity).getIsBatHanging();
		else
			return false;
	}

	public boolean isAngry()
	{
		return true;
	}

	public boolean isSitting()
	{
		if(entity instanceof EntityTameable)
			return ((EntityTameable) entity).isSitting();
		else
			return false;
	}
	
	public boolean isChild()
	{
		if(entity instanceof EntityAgeable)
			return ((EntityAgeable) entity).isChild();
		else
			return false;
	}

	public float getSlimeSize()
	{
		if(entity instanceof EntitySlime)
			return ((EntitySlime) entity).getSlimeSize();
		else
			return 0;
	}

	public int getAttackTimer()
	{
		if(entity instanceof EntityIronGolem)
			return ((EntityIronGolem) entity).getAttackTimer();
		else
			return 0;
	}

	public int getGrowingAge()
	{
		if(entity instanceof EntityAgeable)
			return ((EntityAgeable)entity).getGrowingAge();
		else
			return 0;
	}
	
	public boolean isBurning()
	{
		if(entity != null)
			return entity.isBurning();
		else
			return super.isBurning();
	}
	
    public String getEntityName()
    {
    	return "a Creeper";
    }
}
