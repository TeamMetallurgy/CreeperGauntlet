package rebelkeithy.mods.creepergun.ExplodingCreatures.monsters;

import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;

public class ExplodingCreatureTypePigZombie extends ExplodingCreatureTypeBase {
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getTexture (Entity enity) {
		return "minecraft:textures/entity/zombie_pigman.png";
	}
}
