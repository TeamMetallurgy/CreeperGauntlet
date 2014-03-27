package rebelkeithy.mods.creepergun.renderers;

import org.lwjgl.opengl.GL11;

import rebelkeithy.mods.creepergun.entities.EntityCreeperSucking;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityCreeperSuckingRenderer extends RenderLiving
{
    /** The creeper model. */
    private ModelBase creeperModel = new ModelCreeper(2.0F);
    
    /** Creeper texture */
	private static final ResourceLocation textureLocation =  new ResourceLocation("minecraft:textures/entity/creeper/creeper.png");

    public EntityCreeperSuckingRenderer()
    {
        super(new ModelCreeper(), 0.5F);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2)
    {
        this.updateCreeperScale((EntityCreeperSucking)par1EntityLiving, par2);
    }

    private void updateCreeperScale(EntityCreeperSucking par1EntityLiving, float par2)
	{
        float f1 = par1EntityLiving.getScale(par2);
        float f2 = 1.0F + MathHelper.sin(f1 * 100.0F) * f1 * 0.01F;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }

        //f1 *= f1;
        //f1 *= f1;
        float f3 = (1.0F + f1 * 0.4F) * f2;
        float f4 = (1.0F + f1 * 0.1F) / f2;
        //GL11.glScalef(f3, f4, f3);
        GL11.glScalef(f1, f1, f1);
		
	}

	public void renderCreeper(EntityCreeperSucking entity, double par2, double par4, double par6, float par8, float par9)
    {
		double posX = par2 + entity.getPosX(par9);
		double posY = par4 + entity.getPosY(par9);
		double posZ = par6 + entity.getPosZ(par9);
		
        super.doRenderLiving(entity, posX, posY, posZ, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderCreeper((EntityCreeperSucking)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderCreeper((EntityCreeperSucking)par1Entity, par2, par4, par6, par8, par9);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {		
		return textureLocation;
	}
}
