package rebelkeithy.mods.creepergun.ExplodingCreatures.monsters;

import net.minecraft.entity.Entity;
import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;
import rebelkeithy.mods.creepergun.ExplodingCreatures.renderers.ExplodingCreatureRenderMooshroom;
import rebelkeithy.mods.creepergun.api.IExplodingCreatureRendererHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExplodingCreatureTypeMooshroom extends ExplodingCreatureTypeBase
{
	private static IExplodingCreatureRendererHelper renderHelper = new ExplodingCreatureRenderMooshroom();

	@Override
	@SideOnly(Side.CLIENT)
	public IExplodingCreatureRendererHelper getRenderHelper(Entity entity)
	{
		return renderHelper;
	}
}
