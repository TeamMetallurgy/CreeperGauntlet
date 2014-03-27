package rebelkeithy.mods.creepergun;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import rebelkeithy.mods.creepergun.entities.EntityCreeperSucking;
import rebelkeithy.mods.creepergun.entities.EntityDragonFollower;
import rebelkeithy.mods.creepergun.entities.EntityExplodingCreature;
import rebelkeithy.mods.creepergun.entities.EntityWitherFollower;
import rebelkeithy.mods.creepergun.utils.CylindricalCoords;
import rebelkeithy.mods.creepergun.utils.SphericalCoords;
import rebelkeithy.mods.particleregistry.ParticleRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGauntlet extends Item
{

	private Icon unchargedIcon;
	private Icon chargedIcon;

	public ItemGauntlet(int par1)
	{
		super(par1);
		this.setMaxStackSize(1);
        this.setMaxDamage(32 << 2);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) 
    {
    	if(!par2World.isRemote || Math.random() > 0.15)
    		return;
    	
    	if(par3Entity instanceof EntityPlayer)
    	{
    		EntityPlayer player = (EntityPlayer) par3Entity;
    		ItemStack held = player.getCurrentEquippedItem();
    		if(held != null && held.itemID == this.itemID && held.getItemDamage() == 1)
    		{
    			double rho = 0;
    			double phi = (Math.PI*(player.rotationPitch+90))/180;
    			double theta = (Math.PI*(player.rotationYawHead))/180;
    			SphericalCoords pOffset = new SphericalCoords(0.25, phi, theta);
    			CylindricalCoords pOffset2 = new CylindricalCoords(0.2, phi, theta+(Math.PI/2.0f));
    			ParticleRegistry.spawnParticle("Creeper", player.worldObj, player.posX + player.motionX*0.5 + pOffset.x + pOffset2.x, player.posY+pOffset.y-0.15f, player.posZ +player.motionZ*0.5 + pOffset.z + pOffset2.z, player.motionX+Math.random()*0.08, player.motionY+Math.random()*0.16-0.08, player.motionZ+Math.random()*0.08);
    		}
    	}
    }
    
    public void spawnParticles(EntityPlayer player, int amount, String name)
    {
		double rho = 0;
		double phi = (Math.PI*(player.rotationPitch+90))/180;
		double theta = (Math.PI*(player.rotationYawHead))/180;
		SphericalCoords pOffset = new SphericalCoords(0.25, phi, theta);
		CylindricalCoords pOffset2 = new CylindricalCoords(0.2, phi, theta+(Math.PI/2.0f));
		for(int i = 0; i < amount; i++)
			ParticleRegistry.spawnParticle(name, player.worldObj, player.posX + player.motionX*0.5 + pOffset.x + pOffset2.x, player.posY+pOffset.y-0.15f, player.posZ +player.motionZ*0.5 + pOffset.z + pOffset2.z, player.motionX+Math.random()*0.4-0.2, player.motionY+Math.random()*0.5-0.15, player.motionZ+Math.random()*0.4-0.2);
    }

	@ForgeSubscribe
	public boolean interact(EntityInteractEvent event)
	{
		System.out.println(event.target);
		if(event.entityPlayer.getDistanceSqToEntity(event.entityLiving) > 4)
			return false;
		
		ItemStack itemstack = event.entityPlayer.getCurrentEquippedItem();
				
		if(itemstack != null)
		{
			if(itemstack.itemID != this.itemID)
				return false;
			
			
			if(isGauntletActive(itemstack))
			{
				if(event.target instanceof EntityCreeper)
				{
					EntityLiving entity = (EntityLiving) event.target;
					itemstack.setItemDamage(itemstack.getItemDamage() & ~3);
					boolean flag = entity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
					entity.worldObj.createExplosion(entity, entity.posX, entity.posY, entity.posZ, 6.0f, flag);
					entity.setDead();
					return true;
				} 
				else if(event.target instanceof EntityDragonPart) 
				{	
					itemstack.damageItem((1 << 2), event.entityPlayer);
					
					EntityDragonFollower entityFollower = new EntityDragonFollower(event.entityPlayer.worldObj);
					entityFollower.link((EntityDragon) ((EntityDragonPart) event.target).entityDragonObj);
					event.target.worldObj.spawnEntityInWorld(entityFollower);
					itemstack.setItemDamage(itemstack.getItemDamage() & ~3);

					if(event.target.worldObj.isRemote)
						this.spawnParticles(event.entityPlayer, 10, "Creeper2");
				} 
				else if(event.target instanceof EntityWither)
				{
					EntityWither wither = (EntityWither) event.target;
					
					EntityWitherFollower entityFollower = new EntityWitherFollower(event.entityPlayer.worldObj);
					entityFollower.link(wither);
					event.target.worldObj.spawnEntityInWorld(entityFollower);
					itemstack.setItemDamage(itemstack.getItemDamage() & ~3);

					if(event.target.worldObj.isRemote)
						this.spawnParticles(event.entityPlayer, 10, "Creeper2");
				}
				else 
				{
					if(event.target instanceof EntityLivingBase)
					{
						EntityLivingBase entity = (EntityLivingBase) event.target;
						itemstack.damageItem((1 << 2), event.entityPlayer);
						//itemstack.setItemDamage(itemstack.getItemDamage() + (1 << 2));
						
						if(entity instanceof EntityPlayer || CreeperGauntletMod.instance.explodingCreatureMap.containsKey(entity.getClass().getSimpleName()))
						{
							EntityExplodingCreature newEntity = new EntityExplodingCreature((EntityLiving) entity);
							entity.worldObj.spawnEntityInWorld(newEntity);
							itemstack.setItemDamage(itemstack.getItemDamage() & ~3);
						
							if(entity.worldObj.isRemote)
								this.spawnParticles(event.entityPlayer, 10, "Creeper2");
							entity.addPotionEffect(new PotionEffect(Potion.invisibility.id, 80));
							
							if(entity instanceof EntityPlayer)
								entity.addPotionEffect(new PotionEffect(21, 80));
							else
								entity.setDead();
							
							return true;
						}

						return false;
					}
				}
			} else {
		    	if(event.target instanceof EntityCreeper)
		    	{
		    		EntityCreeper entity = (EntityCreeper) event.target;
		    		itemstack.setItemDamage(itemstack.getItemDamage() | 1);
		    		double xOffset = -Math.cos(Math.PI*(event.entityPlayer.rotationYawHead+0)/180) * 0.3;
		    		double zOffset = -Math.sin(Math.PI*(event.entityPlayer.rotationYawHead+0)/180) * 0.3;
					EntityCreeperSucking newCreeper = new EntityCreeperSucking( entity.worldObj, entity.posX, entity.posY, entity.posZ, event.entityPlayer.posX+xOffset, event.entityPlayer.posY-1, event.entityPlayer.posZ+zOffset);
		    		newCreeper.rotationPitch = entity.rotationPitch;
		    		newCreeper.rotationYaw = entity.rotationYaw;
		    		newCreeper.rotationYawHead = entity.rotationYawHead;
		    		newCreeper.linkItem(itemstack);
		    		//newCreeper.setPosition(entity.posX, entity.posY, entity.posZ);
					//newCreeper.setPosition(event.entityPlayer.posX+xOffset, event.entityPlayer.posY-1, event.entityPlayer.posZ+zOffset);
		    		newCreeper.onSpawnWithEgg((EntityLivingData)null);
					entity.worldObj.spawnEntityInWorld(newCreeper);
					entity.setDead();
		    		return true;
		    	}
			}
		}
		
		return false;
	}
	
	public boolean isGauntletActive(ItemStack gauntlet)
	{
		return (gauntlet.getItemDamage() & 3) == 1;
	}
	
	public void setGauntletActiveState(int state)
	{
		
	}

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if this item should be rotated by 180 degrees around the Y axis when being held in an entities
     * hands.
     */
    public boolean shouldRotateAroundWhenRendering()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int par1)
    {
        if((par1 & 7) == 0)// || par1 == 2)
        	return unchargedIcon;
        else
        	return chargedIcon;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.unchargedIcon = par1IconRegister.registerIcon("creepergun:uncharged");
        this.chargedIcon = par1IconRegister.registerIcon("creepergun:charged");
    }
}
