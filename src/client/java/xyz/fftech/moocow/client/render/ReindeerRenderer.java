package xyz.fftech.moocow.client.render;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import xyz.fftech.moocow.MooCowMod;
import xyz.fftech.moocow.client.model.ReindeerModel;
import xyz.fftech.moocow.entity.ReindeerEntity;

public class ReindeerRenderer extends MobRenderer<ReindeerEntity, ReindeerRenderState, ReindeerModel> {
    private static final Identifier TEXTURE = MooCowMod.id("textures/entity/reindeer.png");

    public ReindeerRenderer(EntityRendererProvider.Context context) {
        super(context, new ReindeerModel(context.bakeLayer(ReindeerModel.LAYER_LOCATION)), 0.55F);
    }

    @Override
    public Identifier getTextureLocation(ReindeerRenderState state) {
        return TEXTURE;
    }

    @Override
    public ReindeerRenderState createRenderState() {
        return new ReindeerRenderState();
    }

    @Override
    public void extractRenderState(ReindeerEntity entity, ReindeerRenderState state, float tickProgress) {
        super.extractRenderState(entity, state, tickProgress);
        state.hasRedNose = entity.hasRedNose();
    }
}
