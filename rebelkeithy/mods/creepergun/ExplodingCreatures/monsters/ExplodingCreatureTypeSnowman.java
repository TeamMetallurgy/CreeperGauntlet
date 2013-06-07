package rebelkeithy.mods.creepergun.ExplodingCreatures.monsters;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;
import rebelkeithy.mods.creepergun.ExplodingCreatures.renderers.ExplodingCreatureRenderSnowman;
import rebelkeithy.mods.creepergun.api.IExplodingCreatureRendererHelper;
import rebelkeithy.mods.creepergun.utils.CustomEntityItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExplodingCreatureTypeSnowman extends ExplodingCreatureTypeBase
{
	private static IExplodingCreatureRendererHelper renderHelper = new ExplodingCreatureRenderSnowman();

	@Override
	@SideOnly(Side.CLIENT)
	public IExplodingCreatureRendererHelper getRenderHelper(Entity entity)
	{
		return renderHelper;
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
