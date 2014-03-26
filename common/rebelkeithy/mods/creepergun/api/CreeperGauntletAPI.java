package rebelkeithy.mods.creepergun.api;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.entity.EntityLiving;

public class CreeperGauntletAPI 
{
	/*
	 * To register you mob with Creeper Gauntlet you need at least an IExplodingCreatureType to register with the
	 * entity. If your entity requires special rendering, such as rescaling or a renderPassModel, create an
	 * IExplodingCreatureRenderHelper and have your IExplodingCreatureType return it in getRenderHelper().
	 */
	public static void registerExplodingCreature(Class entity, IExplodingCreatureType type)
	{
		try {
			Class clazz = Class.forName("rebelkeithy.mods.creepergun.CreeperGauntletMod");
			Field instance = clazz.getField("instance");
			Method register = clazz.getDeclaredMethod("registerExplodingCreature", Class.class, IExplodingCreatureType.class);
			register.invoke(null, entity, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
