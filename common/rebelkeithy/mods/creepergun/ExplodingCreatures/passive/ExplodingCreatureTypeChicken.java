package rebelkeithy.mods.creepergun.ExplodingCreatures.passive;

import java.util.ArrayList;
import java.util.List;

import rebelkeithy.mods.creepergun.CreeperConfig;
import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExplodingCreatureTypeChicken extends ExplodingCreatureTypeBase
{
	@SideOnly(Side.CLIENT)
	@Override
	public String getTexture (Entity enity) {
		return "minecraft:textures/entity/chicken.png";
	}
}
