package xyz.fftech.moocow.client.render;

import net.minecraft.client.renderer.entity.BeeRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.BeeRenderState;
import net.minecraft.resources.Identifier;
import xyz.fftech.moocow.MooCowMod;

public class ZomBeeRenderer extends BeeRenderer {
    private static final Identifier TEXTURE = MooCowMod.id("textures/entity/zom_bee.png");

    public ZomBeeRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public Identifier getTextureLocation(BeeRenderState state) {
        return TEXTURE;
    }
}
