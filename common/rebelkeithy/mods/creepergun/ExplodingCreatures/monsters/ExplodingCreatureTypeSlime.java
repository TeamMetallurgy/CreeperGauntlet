package rebelkeithy.mods.creepergun.ExplodingCreatures.monsters;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.item.ItemStack;
import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;
import rebelkeithy.mods.creepergun.ExplodingCreatures.renderers.ExplodingCreatureRenderSlime;
import rebelkeithy.mods.creepergun.api.IExplodingCreatureRendererHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExplodingCreatureTypeSlime extends ExplodingCreatureTypeBase
{	
	private static IExplodingCreatureRendererHelper renderHelper = new ExplodingCreatureRenderSlime();

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
