package rebelkeithy.mods.creepergun.ExplodingCreatures.monsters;

import java.util.ArrayList;
import java.util.List;

import rebelkeithy.mods.creepergun.CreeperConfig;
import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;
import rebelkeithy.mods.creepergun.models.ModelExplodingBat;
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

public class ExplodingCreatureTypeEnderman extends ExplodingCreatureTypeBase
{
	@Override
	public void explosionCallback(Entity entity)
	{
		System.out.println("in endermans");
		double scale = 20;
		AxisAlignedBB bb = AxisAlignedBB.getAABBPool().getAABB(entity.posX - scale, entity.posY - scale, entity.posZ - scale, entity.posX + scale, entity.posY + scale, entity.posZ + scale);
        
        for(Object e : entity.worldObj.playerEntities)
        {
        	EntityPlayer player = (EntityPlayer) e;
    		if(bb.intersectsWith(player.boundingBox))
    		{
    			player.addPotionEffect(new PotionEffect(15, 200));
    		}
        }
	}
}
