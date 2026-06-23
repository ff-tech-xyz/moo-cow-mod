package xyz.fftech.moocow.client.render;

import net.minecraft.client.renderer.entity.CowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CowRenderState;
import net.minecraft.resources.Identifier;
import xyz.fftech.moocow.MooCowMod;

public class MooCowRenderer extends CowRenderer {
    private static final Identifier TEXTURE = MooCowMod.id("textures/entity/moo_cow.png");

    public MooCowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public Identifier getTextureLocation(CowRenderState state) {
        return TEXTURE;
    }
}
