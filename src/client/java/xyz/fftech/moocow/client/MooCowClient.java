package xyz.fftech.moocow.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import xyz.fftech.moocow.MooCowMod;
import xyz.fftech.moocow.client.model.ReindeerModel;
import xyz.fftech.moocow.client.render.ReindeerRenderer;

public class MooCowClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(ReindeerModel.LAYER_LOCATION, ReindeerModel::createBodyLayer);
        EntityRendererRegistry.register(MooCowMod.REINDEER, ReindeerRenderer::new);
    }
}
