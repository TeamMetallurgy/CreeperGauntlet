package rebelkeithy.mods.creepergun.api;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public interface IRenderAccess 
{
    public void loadTexture(String par1Str);
    
    public RenderManager getRenderManager();
    
    public RenderBlocks getRenderBlocks();
    
    public ModelBase getMainModel();
    
    public void setShadowSize(float size);

	public void setRenderPassModel(ModelBase model);
	
	public void bindTexture(ResourceLocation resourceLocation);
}
