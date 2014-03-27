package rebelkeithy.mods.creepergun.ExplodingCreatures.passive;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.ItemStack;
import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;
import rebelkeithy.mods.creepergun.ExplodingCreatures.renderers.ExplodingCreatureRenderSheep;
import rebelkeithy.mods.creepergun.api.IExplodingCreatureRendererHelper;
import rebelkeithy.mods.creepergun.models.ModelExplodingSheep2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExplodingCreatureTypeSheep extends ExplodingCreatureTypeBase
{
	private static IExplodingCreatureRendererHelper renderHelper = new ExplodingCreatureRenderSheep();

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBase getModel(Entity entity) 
	{
		return new ModelExplodingSheep2();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IExplodingCreatureRendererHelper getRenderHelper(Entity entity)
	{
		return renderHelper;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTexture(Entity entity) 
	{
		//TODO: Get replacement for getTexture
		//return entity.getTexture();
		return "minecraft:textures/entity/sheep/sheep.png";
	}

	@Override
	public List getDrops(Entity entity) 
	{
		List<ItemStack> drops = super.getDrops(entity);

        for(ItemStack stack : drops)
        {
    		if(stack.itemID == Block.cloth.blockID)
    			stack.setItemDamage(((EntitySheep)entity).getFleeceColor());
        }
        
        return drops;
	}
}
