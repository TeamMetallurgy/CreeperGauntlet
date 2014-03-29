package rebelkeithy.mods.creepergun.ExplodingCreatures.passive;

import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExplodingCreatureTypeSquid extends ExplodingCreatureTypeBase {
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getTexture (Entity enity) {
		return "minecraft:textures/entity/squid.png";
	}
}
