package rebelkeithy.mods.creepergun.utils;

import net.minecraft.item.ItemStack;

public class Pair
{
	public int amount;
	public ItemStack drop;
	Pair(int amount, ItemStack drop)
	{
		this.amount = amount;
		this.drop = drop;
	}
}
