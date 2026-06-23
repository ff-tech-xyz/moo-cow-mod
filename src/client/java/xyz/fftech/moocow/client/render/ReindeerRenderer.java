package xyz.fftech.moocow.client.render;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import xyz.fftech.moocow.MooCowMod;
import xyz.fftech.moocow.client.model.ReindeerModel;
import xyz.fftech.moocow.entity.ReindeerEntity;

public class ReindeerRenderer extends MobRenderer<ReindeerEntity, LivingEntityRenderState, ReindeerModel> {
    private static final Identifier TEXTURE = MooCowMod.id("textures/entity/reindeer.png");

    public ReindeerRenderer(EntityRendererProvider.Context context) {
        super(context, new ReindeerModel(context.bakeLayer(ReindeerModel.LAYER_LOCATION)), 0.55F);
    }

    @Override
    public Identifier getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
