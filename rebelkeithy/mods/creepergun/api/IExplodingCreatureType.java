package rebelkeithy.mods.creepergun.api;

import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IExplodingCreatureType 
{
    @SideOnly(Side.CLIENT)
    public ModelBase getModel(Entity entity);
    
    @SideOnly(Side.CLIENT)
    public String getTexture(Entity entity);
    
    @SideOnly(Side.CLIENT)
    // If the mob needs any special rendering, return the helper, otherwise return null
    public IExplodingCreatureRendererHelper getRenderHelper(Entity entity);
    
    // Return a list of everything the mob should drop when it explodes
    public List<ItemStack> getDrops(Entity entity);
    
    public int getExplosionSize(Entity entity);
    
    // Return true if this is a flying mob, otherwise return false
    public boolean isFlying(Entity entity);
    
    // Return true if the explosion should light the area on fire
    public boolean causesFlamingExplosion(Entity entity);
    
    // Called after the mob explodes
    public void explosionCallback(Entity entity);   
    
}
