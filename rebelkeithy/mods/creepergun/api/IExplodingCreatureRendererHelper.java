package rebelkeithy.mods.creepergun.api;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLiving;

public interface IExplodingCreatureRendererHelper 
{
    public void preRenderCallback(IRenderAccess renderer, EntityLiving par1EntityLiving, float par2);

    public int shouldRenderPass(IRenderAccess renderer, EntityLiving par1EntityLiving, int par2, float par3);
    
    public void renderEquippedItems(IRenderAccess renderer, EntityLiving par1EntityLiving, float par2);

    // Can be null
	public ModelBase getRenderPassModel();
}
