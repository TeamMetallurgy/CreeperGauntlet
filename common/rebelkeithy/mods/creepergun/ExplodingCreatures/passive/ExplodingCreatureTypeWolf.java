package rebelkeithy.mods.creepergun.ExplodingCreatures.passive;

import java.util.ArrayList;
import java.util.List;

import rebelkeithy.mods.creepergun.CreeperConfig;
import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;
import rebelkeithy.mods.creepergun.models.ModelExplodingBat;
import rebelkeithy.mods.creepergun.models.ModelExplodingOcelot;
import rebelkeithy.mods.creepergun.models.ModelExplodingWolf;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExplodingCreatureTypeWolf extends ExplodingCreatureTypeBase
{
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBase getModel(Entity entity) 
	{
		return new ModelExplodingWolf();
	}
}
