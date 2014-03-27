package rebelkeithy.mods.creepergun.ExplodingCreatures.passive;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;
import rebelkeithy.mods.creepergun.ExplodingCreatures.renderers.ExplodingCreatureRenderBat;
import rebelkeithy.mods.creepergun.api.IExplodingCreatureRendererHelper;
import rebelkeithy.mods.creepergun.models.ModelExplodingBat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExplodingCreatureTypeBat extends ExplodingCreatureTypeBase
{
	private static IExplodingCreatureRendererHelper renderHelper = new ExplodingCreatureRenderBat();

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBase getModel(Entity entity) 
	{
		return new ModelExplodingBat();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IExplodingCreatureRendererHelper getRenderHelper(Entity entity)
	{
		return renderHelper;
	}

	@Override
	public int getExplosionSize(Entity entity) 
	{
		return 1;
	}

	@Override
	public boolean isFlying(Entity entity) 
	{
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getTexture (Entity enity) {
		return "minecraft:textures/entity/bat.png";
	}
}
