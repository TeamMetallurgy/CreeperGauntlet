package rebelkeithy.mods.creepergun.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import rebelkeithy.mods.creepergun.api.IExplodingCreatureRendererHelper;
import rebelkeithy.mods.creepergun.api.IRenderAccess;
import rebelkeithy.mods.creepergun.entities.EntityExplodingCreature;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityExplodingCreatureRenderer extends RenderLiving implements IRenderAccess
{
    /** The creeper model. */
    private ModelBase creeperModel = new ModelCreeper(2.0F);
    
    // Slime Field
	private ModelBase scaleAmount;

    public EntityExplodingCreatureRenderer()
    {
        super(new ModelCreeper(), 0.5F);
    }

    // PLAYER SKIN
    // TODO: Find replacement of player skin
    /*
    protected void func_98190_a(EntityLiving par1EntityLiving)
    {
    	EntityExplodingCreature entity = (EntityExplodingCreature) par1EntityLiving;
    	if(entity.entity instanceof EntityPlayer)
    		this.func_98191_a((EntityPlayer)(entity.entity));
    	else
    		super.func_98190_a(par1EntityLiving);
    }

    protected void func_98191_a(EntityPlayer par1EntityPlayer)
    {
        this.loadDownloadableImageTexture(par1EntityPlayer.skinUrl, par1EntityPlayer.getTexture());
    }
    */

    /**
     * Updates creeper scale in prerender callback
     */
    protected void updateCreeperScale(EntityExplodingCreature par1EntityCreeper, float par2)
    {
        float f1 = par1EntityCreeper.getCreeperFlashIntensity(par2);
        float f2 = 1.0F + MathHelper.sin(f1 * 100.0F) * f1 * 0.01F;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }

        f1 *= f1;
        f1 *= f1;
        float f3 = (1.0F + f1 * 0.4F) * f2;
        float f4 = (1.0F + f1 * 0.1F) / f2;
        GL11.glScalef(f3, f4, f3);
    }

    /**
     * Updates color multiplier based on creeper state called by getColorMultiplier
     */
    protected int updateCreeperColorMultiplier(EntityExplodingCreature par1EntityCreeper, float par2, float par3)
    {
        float f2 = par1EntityCreeper.getCreeperFlashIntensity(par3);

        if ((int)(f2 * 10.0F) % 2 == 0)
        {
            return 0;
        }
        else
        {
            int i = (int)(f2 * 0.2F * 255.0F);

            if (i < 0)
            {
                i = 0;
            }

            if (i > 255)
            {
                i = 255;
            }

            short short1 = 255;
            short short2 = 255;
            short short3 = 255;
            return i << 24 | short1 << 16 | short2 << 8 | short3;
        }
    }

    public void loadTexture(String par1Str)
    {
    	//TODO: Find Replacement for loadTexture;
    	//super.loadTexture(par1Str);
    }
    
    public RenderManager getRenderManager()
    {
    	return renderManager;
    }
    
    public RenderBlocks getRenderBlocks()
    {
    	return renderBlocks;
    }
    
    public ModelBase getMainModel()
    {
    	return mainModel;
    }
    
    public void setShadowSize(float size)
    {
    	this.shadowSize = size;
    }

    /**
     * Returns an ARGB int color back. Args: entityLiving, lightBrightness, partialTickTime
     */
    protected int getColorMultiplier(EntityLiving par1EntityLiving, float par2, float par3)
    {
        return this.updateCreeperColorMultiplier((EntityExplodingCreature)par1EntityLiving, par2, par3);
    }

    protected int inheritRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return -1;
    }
	public void renderCreeper(EntityExplodingCreature entity, double par2, double par4, double par6, float par8, float par9)
    {
		if(entity.entity == null)
			return;

		entity.entity.rotationYaw = entity.rotationYaw;
		((EntityLiving)entity.entity).rotationYawHead = entity.getRotationYawHead();

		this.mainModel = entity.getModel();
		renderPassModel = mainModel;
		
		if(mainModel == null)
			mainModel = new ModelBiped();

    	IExplodingCreatureRendererHelper renderHelper = entity.creatureType.getRenderHelper(entity.entity);
    	
    	if(renderHelper != null)
    		this.setRenderPassModel(renderHelper.getRenderPassModel());
		
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
        
        // Render the mob (which is invisible) to render it's equipment
        if(mainModel instanceof ModelBiped)
        {
        	Render renderer = RenderManager.instance.getEntityRenderObject(entity.entity);
        	renderer.doRender(entity.entity, par2, par4, par6, par8, par9);
        }
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
    {
    	IExplodingCreatureRendererHelper renderHelper = ((EntityExplodingCreature)par1EntityLiving).creatureType.getRenderHelper(((EntityExplodingCreature)par1EntityLiving).entity);
    	
    	if(renderHelper != null)
    		renderHelper.preRenderCallback(this, (EntityLiving) ((EntityExplodingCreature)par1EntityLiving).entity, par2);
    		
    	if(((EntityExplodingCreature)par1EntityLiving).entity.getClass().getSimpleName().equals("EntityOtherPlayerMP"))
            GL11.glScalef(15/16F, 15/16F, 15/16F);
    	
        this.updateCreeperScale((EntityExplodingCreature)par1EntityLiving, par2);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
    	IExplodingCreatureRendererHelper renderHelper = ((EntityExplodingCreature)par1EntityLiving).creatureType.getRenderHelper(((EntityExplodingCreature)par1EntityLiving).entity);
    	
    	if(renderHelper != null)
    		return renderHelper.shouldRenderPass(this, (EntityLiving) ((EntityExplodingCreature)par1EntityLiving).entity, par2, par3);
    	
    	return super.shouldRenderPass(par1EntityLiving, par2, par3);
    }

   

    protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2)
    {
    	IExplodingCreatureRendererHelper renderHelper = ((EntityExplodingCreature)par1EntityLiving).creatureType.getRenderHelper(par1EntityLiving);
    	
    	if(renderHelper != null)
    		renderHelper.renderEquippedItems(this, (EntityLiving) ((EntityExplodingCreature)par1EntityLiving).entity, par2);
    	
    	super.renderEquippedItems(par1EntityLiving, par2);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderCreeper((EntityExplodingCreature)par1Entity, par2, par4, par6, par8, par9);
    }

	@Override
	public void setRenderPassModel(ModelBase model) 
	{
		super.setRenderPassModel(model);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO: Send texture
		return null;
	}
}
