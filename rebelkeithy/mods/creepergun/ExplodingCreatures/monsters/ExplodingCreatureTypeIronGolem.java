package rebelkeithy.mods.creepergun.ExplodingCreatures.monsters;

import java.util.ArrayList;
import java.util.List;

import rebelkeithy.mods.creepergun.CreeperConfig;
import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;
import rebelkeithy.mods.creepergun.models.ModelExplodingBat;
import rebelkeithy.mods.creepergun.models.ModelExplodingIronGolem;
import rebelkeithy.mods.creepergun.models.ModelExplodingOcelot;
import rebelkeithy.mods.creepergun.models.ModelExplodingWolf;
import rebelkeithy.mods.creepergun.utils.CustomEntityItemStack;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExplodingCreatureTypeIronGolem extends ExplodingCreatureTypeBase
{
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBase getModel(Entity entity) 
	{
		return new ModelExplodingIronGolem();
	}
	
	@Override
	public int getExplosionSize(Entity entity) 
	{
		return 10;
	}

	@Override
	public void explosionCallback(Entity entity)
	{
        CustomEntityItemStack entityitem = new CustomEntityItemStack(entity.worldObj, entity.posX, entity.posY + 1.0, entity.posZ, new ItemStack(Block.pumpkin));
        entityitem.setMotion(Math.random()*0.0, Math.random()*1+1, Math.random()*0.0-0.0);
        entityitem.delayBeforeCanPickup = 10;
        entity.worldObj.spawnEntityInWorld(entityitem);
	}
}
