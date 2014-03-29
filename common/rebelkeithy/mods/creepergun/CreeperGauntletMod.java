package rebelkeithy.mods.creepergun;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rebelkeithy.mods.creepergun.ExplodingCreatures.ExplodingCreatureTypeBase;
import rebelkeithy.mods.creepergun.ExplodingCreatures.monsters.ExplodingCreatureTypeBlaze;
import rebelkeithy.mods.creepergun.ExplodingCreatures.monsters.ExplodingCreatureTypeCaveSpider;
import rebelkeithy.mods.creepergun.ExplodingCreatures.monsters.ExplodingCreatureTypeEnderman;
import rebelkeithy.mods.creepergun.ExplodingCreatures.monsters.ExplodingCreatureTypeGhast;
import rebelkeithy.mods.creepergun.ExplodingCreatures.monsters.ExplodingCreatureTypeIronGolem;
import rebelkeithy.mods.creepergun.ExplodingCreatures.monsters.ExplodingCreatureTypeMagmaCube;
import rebelkeithy.mods.creepergun.ExplodingCreatures.monsters.ExplodingCreatureTypeMooshroom;
import rebelkeithy.mods.creepergun.ExplodingCreatures.monsters.ExplodingCreatureTypePigZombie;
import rebelkeithy.mods.creepergun.ExplodingCreatures.monsters.ExplodingCreatureTypeSilverfish;
import rebelkeithy.mods.creepergun.ExplodingCreatures.monsters.ExplodingCreatureTypeSkeleton;
import rebelkeithy.mods.creepergun.ExplodingCreatures.monsters.ExplodingCreatureTypeSlime;
import rebelkeithy.mods.creepergun.ExplodingCreatures.monsters.ExplodingCreatureTypeSnowman;
import rebelkeithy.mods.creepergun.ExplodingCreatures.monsters.ExplodingCreatureTypeSpider;
import rebelkeithy.mods.creepergun.ExplodingCreatures.monsters.ExplodingCreatureTypeWitch;
import rebelkeithy.mods.creepergun.ExplodingCreatures.monsters.ExplodingCreatureTypeZombie;
import rebelkeithy.mods.creepergun.ExplodingCreatures.passive.ExplodingCreatureTypeBat;
import rebelkeithy.mods.creepergun.ExplodingCreatures.passive.ExplodingCreatureTypeChicken;
import rebelkeithy.mods.creepergun.ExplodingCreatures.passive.ExplodingCreatureTypeCow;
import rebelkeithy.mods.creepergun.ExplodingCreatures.passive.ExplodingCreatureTypeOcelot;
import rebelkeithy.mods.creepergun.ExplodingCreatures.passive.ExplodingCreatureTypePig;
import rebelkeithy.mods.creepergun.ExplodingCreatures.passive.ExplodingCreatureTypeSheep;
import rebelkeithy.mods.creepergun.ExplodingCreatures.passive.ExplodingCreatureTypeSquid;
import rebelkeithy.mods.creepergun.ExplodingCreatures.passive.ExplodingCreatureTypeVillager;
import rebelkeithy.mods.creepergun.ExplodingCreatures.passive.ExplodingCreatureTypeWolf;
import rebelkeithy.mods.creepergun.api.CreeperGauntletAPI;
import rebelkeithy.mods.creepergun.api.IExplodingCreatureType;
import rebelkeithy.mods.creepergun.entities.EntityCreeperSucking;
import rebelkeithy.mods.creepergun.entities.EntityDragonFollower;
import rebelkeithy.mods.creepergun.entities.EntityExplodingCreature;
import rebelkeithy.mods.creepergun.entities.EntityWitherFollower;
import rebelkeithy.mods.creepergun.utils.CustomEntityItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = CreeperGauntletMod.MOD_ID, name = CreeperGauntletMod.MOD_NAME, version = CreeperGauntletMod.MOD_VERSION, dependencies = "required-after:Forge@[7.7.2.682,)")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class CreeperGauntletMod
{
	public final static String MOD_ID = "CreeperGauntlet";
	public final static String MOD_NAME = "Creeper Gauntlet";
	public final static String MOD_VERSION = "1.0.0";

	@Instance(MOD_ID)
	public static CreeperGauntletMod instance;

	@SidedProxy(clientSide = "rebelkeithy.mods.creepergun.ClientProxy", serverSide = "rebelkeithy.mods.creepergun.CommonProxy")
	public static CommonProxy proxy;
	
	public static Item gauntlet;
	
	public Map<String, IExplodingCreatureType> explodingCreatureMap;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		CreeperConfig.init(event.getSuggestedConfigurationFile());
		
		gauntlet = new ItemGauntlet(CreeperConfig.gauntletID).setCreativeTab(CreativeTabs.tabCombat);
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		EntityRegistry.registerGlobalEntityID(EntityCreeperSucking.class, "CreeperSucking", CreeperConfig.entityID);
		EntityRegistry.registerModEntity(EntityExplodingCreature.class, "ExplodingCreature", 1, this, 64, 1, true);
		
		EntityRegistry.registerModEntity(CustomEntityItemStack.class, "CustomStack", 2, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityDragonFollower.class, "DragonFollower", 3, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityWitherFollower.class, "WitherFollower", 4, this, 64, 1, true);
		
		GameRegistry.addRecipe(new ItemStack(gauntlet), "E E", "EGE", " E ", 'E', Item.enderPearl, 'G', Item.gunpowder);
		LanguageRegistry.addName(gauntlet, "Creeper Gauntlet");
		
		explodingCreatureMap = new HashMap<String, IExplodingCreatureType>();
		
		explodingCreatureMap.put("EntityBat", new ExplodingCreatureTypeBat());
		explodingCreatureMap.put("EntityChicken", new ExplodingCreatureTypeChicken());
		explodingCreatureMap.put("EntitySheep", new ExplodingCreatureTypeSheep());
		explodingCreatureMap.put("EntityCow", new ExplodingCreatureTypeCow());
		explodingCreatureMap.put("EntityMooshroom", new ExplodingCreatureTypeMooshroom());
		explodingCreatureMap.put("EntityOcelot", new ExplodingCreatureTypeOcelot());
		explodingCreatureMap.put("EntityPig", new ExplodingCreatureTypePig());
		explodingCreatureMap.put("EntitySquid", new ExplodingCreatureTypeSquid());
		explodingCreatureMap.put("EntityVillager", new ExplodingCreatureTypeVillager());
		explodingCreatureMap.put("EntityWolf", new ExplodingCreatureTypeWolf());

		explodingCreatureMap.put("EntityBlaze", new ExplodingCreatureTypeBlaze());
		explodingCreatureMap.put("EntityCaveSpider", new ExplodingCreatureTypeCaveSpider());
		explodingCreatureMap.put("EntityEnderman", new ExplodingCreatureTypeEnderman());
		explodingCreatureMap.put("EntityGhast", new ExplodingCreatureTypeGhast());
		explodingCreatureMap.put("EntityIronGolem", new ExplodingCreatureTypeIronGolem());
		explodingCreatureMap.put("EntityMagmaCube", new ExplodingCreatureTypeMagmaCube());
		explodingCreatureMap.put("EntityPigZombie", new ExplodingCreatureTypePigZombie());
		explodingCreatureMap.put("EntitySilverfish", new ExplodingCreatureTypeSilverfish());
		explodingCreatureMap.put("EntitySkeleton", new ExplodingCreatureTypeSkeleton());
		explodingCreatureMap.put("EntitySlime", new ExplodingCreatureTypeSlime());
		explodingCreatureMap.put("EntitySnowman", new ExplodingCreatureTypeSnowman());
		explodingCreatureMap.put("EntitySpider", new ExplodingCreatureTypeSpider());
		explodingCreatureMap.put("EntityWitch", new ExplodingCreatureTypeWitch());
		explodingCreatureMap.put("EntityZombie", new ExplodingCreatureTypeZombie());
		
		
		proxy.registerModelRenderers();
		proxy.registerParticles();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	}
	
	public static void registerExplodingCreature(Class entity, IExplodingCreatureType type)
	{
		CreeperGauntletMod.instance.explodingCreatureMap.put(entity.getSimpleName(), type);
	}
}