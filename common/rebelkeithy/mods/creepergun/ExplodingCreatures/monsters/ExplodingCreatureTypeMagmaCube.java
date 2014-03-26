package rebelkeithy.mods.creepergun.ExplodingCreatures.monsters;

import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.item.ItemStack;
import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;
import rebelkeithy.mods.creepergun.ExplodingCreatures.renderers.ExplodingCreatureRenderMagmaCube;
import rebelkeithy.mods.creepergun.api.IExplodingCreatureRendererHelper;
import rebelkeithy.mods.creepergun.models.ModelExplodingMagmaCube;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExplodingCreatureTypeMagmaCube extends ExplodingCreatureTypeBase
{
	private static IExplodingCreatureRendererHelper renderHelper = new ExplodingCreatureRenderMagmaCube();
	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBase getModel(Entity entity) 
	{
		return new ModelExplodingMagmaCube();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IExplodingCreatureRendererHelper getRenderHelper(Entity entity)
	{
		return renderHelper;
	}
	
	@Override
	public List getDrops(Entity entity) 
	{
		List<ItemStack> drops = super.getDrops(entity);

        for(ItemStack stack : drops)
        {
    		stack.stackSize = (int) (stack.stackSize * ((EntitySlime)entity).getSlimeSize() * 2);
        }
        
        return drops;
	}
	
	@Override
	public int getExplosionSize(Entity entity) 
	{
		return (int) (2 * ((EntitySlime)entity).getSlimeSize()/2.0);
	}
}
