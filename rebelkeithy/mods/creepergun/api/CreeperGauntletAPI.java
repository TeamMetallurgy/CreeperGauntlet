package rebelkeithy.mods.creepergun.api;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.entity.EntityLiving;

public class CreeperGauntletAPI 
{
	public static void registerExplodingCreature(Class entity, IExplodingCreatureType type)
	{
		try {
			Class clazz = Class.forName("rebelkeithy.mods.creepergun.CreeperGauntletMod");
			Field instance = clazz.getField("instance");
			Method register = clazz.getDeclaredMethod("registerExplodingCreature", Class.class, IExplodingCreatureType.class);
			register.invoke(null, entity, type);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
