package rebelkeithy.mods.creepergun.ExplodingCreatures.monsters;

import net.minecraft.entity.Entity;
import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;
import rebelkeithy.mods.creepergun.ExplodingCreatures.renderers.ExplodingCreatureRenderSpider;
import rebelkeithy.mods.creepergun.api.IExplodingCreatureRendererHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExplodingCreatureTypeSpider extends ExplodingCreatureTypeBase
{
	private static IExplodingCreatureRendererHelper renderHelper = new ExplodingCreatureRenderSpider();

	@Override
	@SideOnly(Side.CLIENT)
	public IExplodingCreatureRendererHelper getRenderHelper(Entity entity)
	{
		return renderHelper;
	}
}
