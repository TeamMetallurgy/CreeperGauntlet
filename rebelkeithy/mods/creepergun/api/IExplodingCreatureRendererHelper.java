package rebelkeithy.mods.creepergun.api;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLiving;

public interface IExplodingCreatureRendererHelper 
{
    public void preRenderCallback(IRenderAccess renderer, EntityLiving par1EntityLiving, float par2);

    public int shouldRenderPass(IRenderAccess renderer, EntityLiving par1EntityLiving, int par2, float par3);
    
    // Used for any extra equipment, such as the snow golems head. Biped should have their equipment render automatically
    public void renderEquippedItems(IRenderAccess renderer, EntityLiving par1EntityLiving, float par2);

    // Return null if not needed
	public ModelBase getRenderPassModel();
}
