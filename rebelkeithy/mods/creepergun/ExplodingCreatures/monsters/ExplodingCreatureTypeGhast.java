package rebelkeithy.mods.creepergun.ExplodingCreatures.monsters;

import net.minecraft.entity.Entity;
import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;
import rebelkeithy.mods.creepergun.ExplodingCreatures.renderers.ExplodingCreatureRenderGhast;
import rebelkeithy.mods.creepergun.api.IExplodingCreatureRendererHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExplodingCreatureTypeGhast extends ExplodingCreatureTypeBase
{
	private static IExplodingCreatureRendererHelper renderHelper = new ExplodingCreatureRenderGhast();

	@Override
	@SideOnly(Side.CLIENT)
	public IExplodingCreatureRendererHelper getRenderHelper(Entity entity)
	{
		return renderHelper;
	}
	
	@Override
	public int getExplosionSize(Entity entity) 
	{
		return 7;
	}

	@Override
	public boolean isFlying(Entity entity) 
	{
		return true;
	}
}
