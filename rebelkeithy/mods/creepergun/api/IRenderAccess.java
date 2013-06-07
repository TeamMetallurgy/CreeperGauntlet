package rebelkeithy.mods.creepergun.api;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;

public interface IRenderAccess 
{
    public void loadTexture(String par1Str);
    
    public RenderManager getRenderManager();
    
    public RenderBlocks getRenderBlocks();
    
    public ModelBase getMainModel();
    
    public void setShadowSize(float size);

	public void setRenderPassModel(ModelBase model);
}
