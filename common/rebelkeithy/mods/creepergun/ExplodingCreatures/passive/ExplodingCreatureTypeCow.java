package rebelkeithy.mods.creepergun.ExplodingCreatures.passive;

import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;

public class ExplodingCreatureTypeCow extends ExplodingCreatureTypeBase {
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getTexture (Entity enity) {
		return "textures/entity/cow/cow.png";
	}

}
