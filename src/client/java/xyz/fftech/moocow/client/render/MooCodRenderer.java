package xyz.fftech.moocow.client.render;

import net.minecraft.client.renderer.entity.CodRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import xyz.fftech.moocow.MooCowMod;

public class MooCodRenderer extends CodRenderer {
    private static final Identifier TEXTURE = MooCowMod.id("textures/entity/moo_cod.png");

    public MooCodRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public Identifier getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }
}
