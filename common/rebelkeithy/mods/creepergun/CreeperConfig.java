package rebelkeithy.mods.creepergun;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

public class CreeperConfig
{
	public static Map<String, List<ItemStack>> dropTable;
	public static String[] passiveMobs = { EntityBat.class.getName(), 
									EntityChicken.class.getName(),
									EntityCow.class.getName(),
									EntityIronGolem.class.getName(),
									EntityMooshroom.class.getName(),
									EntityOcelot.class.getName(),
									EntityPig.class.getName(),
									EntitySheep.class.getName(),
									EntitySnowman.class.getName(),
									EntitySquid.class.getName(),
									EntityVillager.class.getName(),
									EntityWolf.class.getName() };
	
	public static String[] hostileMobs = { EntityBlaze.class.getName(),
										EntityCaveSpider.class.getName(),
										EntityEnderman.class.getName(),
										EntityGhast.class.getName(),
										EntityMagmaCube.class.getName(),
										EntityPigZombie.class.getName(),
										EntitySilverfish.class.getName(),
										EntitySkeleton.class.getName(),
										EntitySlime.class.getName(),
										EntitySpider.class.getName(),
										EntityWitch.class.getName() ,
										EntityZombie.class.getName() };
	
	public static boolean pvpEnabled = true;
	public static int entityID = 172;
	public static int gauntletID = 1473;
	
	public static void init(File file)
	{
		Configuration config = new Configuration(file);
		config.load();
		
		dropTable = new HashMap<String, List<ItemStack>>();
		
		parseString("EntityBat", config.get("Passive Drops", "Bat", "").getString());
		parseString("EntityChicken", config.get("Passive Drops", "Chicken", "288:20:0, 365:10:0").getString());
		parseString("EntityCow", config.get("Passive Drops", "Cow", "334:10:0, 363:8:0").getString());
		parseString("EntityMooshroom", config.get("Passive Drops", "Mooshroom", "334:10:0, 363:8:0, 39:10:0, 40:10:0").getString());
		parseString("EntityOcelot", config.get("Passive Drops", "Ocelot", "").getString());
		parseString("EntityPig", config.get("Passive Drops", "Pig", "319:20:0").getString());
		parseString("EntitySheep", config.get("Passive Drops", "Sheep", "35:20:0").getString());
		parseString("EntitySquid", config.get("Passive Drops", "Squid", "351:15:0").getString());
		parseString("EntityWolf", config.get("Passive Drops", "Wolf", "").getString());
		parseString("EntityVillager", config.get("Passive Drops", "Villager", "388:8:0").getString());

		parseString("EntityBlaze", config.get("Hostile Drops", "Blaze", "369:10:0").getString());
		parseString("EntityCaveSpider", config.get("Hostile Drops", "CaveSpider", "375:8:0, 287:8:0").getString());
		parseString("EntityEnderman", config.get("Hostile Drops", "Enderman", "368:2:0").getString());
		parseString("EntityGhast", config.get("Hostile Drops", "Ghast", "289:15:0, 370:2:0").getString());
		parseString("EntityIronGolem", config.get("Hostile Drops", "Iron Golem", "265:30:0").getString());
		parseString("EntityMagmaCube", config.get("Hostile Drops", "Magma Cube", "378:2:0, 341:2:0, 377:2:0").getString());
		parseString("EntityPigZombie", config.get("Hostile Drops", "Pig Zombie", "367:30:0, 371:5:0").getString());
		parseString("EntitySilverfish", config.get("Hostile Drops", "Silverfish", "").getString());
		parseString("EntitySkeleton", config.get("Hostile Drops", "Skeleton", "352:15:0, 262:7:0").getString());
		parseString("EntitySlime", config.get("Hostile Drops", "Slime", "341:4:0").getString());
		parseString("EntitySnowman", config.get("Hostile Drops", "Snowman", "332:30:0").getString());
		parseString("EntitySpider", config.get("Hostile Drops", "Spider", "375:8:0, 287:8:0").getString());
		parseString("EntityWitch", config.get("Hostile Drops", "Witch", "374:4:0, 373:4:0, 384:1:0, 372:8:0").getString());
		parseString("EntityZombie", config.get("Hostile Drops", "Zombie", "367:30:0").getString());
		
		pvpEnabled = config.get("Creeper Gauntlet", "Enable PVP", pvpEnabled).getBoolean(pvpEnabled);
		entityID = config.get("Creeper Gauntlet", "Entity ID", entityID).getInt();
		gauntletID = config.get("Creeper Gauntlet", "Gauntlet ID", gauntletID).getInt();
		
		config.save();
	}
	
	public static void parseString(String name, String value)
	{
		ArrayList dropList = new ArrayList<ItemStack>();
		if(!value.equals(""))
		{
			String[] drops = value.split(", ");
			for(String drop : drops)
			{
				String[] dropArray = drop.split(":");
				dropList.add(new ItemStack(Integer.parseInt(dropArray[0]), Integer.parseInt(dropArray[1]), Integer.parseInt(dropArray[2])));
			}
		}
		dropTable.put(name, dropList);
	}
		
}
