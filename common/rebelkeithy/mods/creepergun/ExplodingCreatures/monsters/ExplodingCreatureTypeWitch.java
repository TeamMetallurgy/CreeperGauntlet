package rebelkeithy.mods.creepergun.ExplodingCreatures.monsters;

import net.minecraft.entity.Entity;
import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;
import rebelkeithy.mods.creepergun.ExplodingCreatures.renderers.ExplodingCreatureRenderWitch;
import rebelkeithy.mods.creepergun.api.IExplodingCreatureRendererHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExplodingCreatureTypeWitch extends ExplodingCreatureTypeBase
{
	private static IExplodingCreatureRendererHelper renderHelper = new ExplodingCreatureRenderWitch();

	@Override
	@SideOnly(Side.CLIENT)
	public IExplodingCreatureRendererHelper getRenderHelper(Entity entity)
	{
		return renderHelper;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getTexture (Entity enity) {
		return "minecraft:textures/entity/witch.png";
	}
}
