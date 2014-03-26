package rebelkeithy.mods.creepergun;

import rebelkeithy.mods.creepergun.entities.EntityCreeperSucking;
import rebelkeithy.mods.creepergun.entities.EntityDragonFollower;
import rebelkeithy.mods.creepergun.entities.EntityExplodingCreature;
import rebelkeithy.mods.creepergun.entities.EntityFollowerRenderer;
import rebelkeithy.mods.creepergun.entities.EntityWitherFollower;
import rebelkeithy.mods.creepergun.particles.ParticleCreeper2FX;
import rebelkeithy.mods.creepergun.particles.ParticleCreeperFX;
import rebelkeithy.mods.creepergun.renderers.EntityCreeperSuckingRenderer;
import rebelkeithy.mods.creepergun.renderers.EntityExplodingCreatureRenderer;
import rebelkeithy.mods.particleregistry.ParticleRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	public void registerParticles()
	{
		ParticleRegistry.registerParticle("Creeper", ParticleCreeperFX.class);
		ParticleRegistry.registerParticle("Creeper2", ParticleCreeper2FX.class);
	}
	
	public void registerModelRenderers() 
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityCreeperSucking.class, new EntityCreeperSuckingRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityExplodingCreature.class, new EntityExplodingCreatureRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityDragonFollower.class, new EntityFollowerRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityWitherFollower.class, new EntityFollowerRenderer());
	}
}
