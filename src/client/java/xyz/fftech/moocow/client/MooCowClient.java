package xyz.fftech.moocow.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import xyz.fftech.moocow.MooCowMod;
import xyz.fftech.moocow.client.model.ReindeerModel;
import xyz.fftech.moocow.client.render.MooCodRenderer;
import xyz.fftech.moocow.client.render.MooCowRenderer;
import xyz.fftech.moocow.client.render.ReindeerRenderer;
import xyz.fftech.moocow.client.render.ZomBeeRenderer;

public class MooCowClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(ReindeerModel.LAYER_LOCATION, ReindeerModel::createBodyLayer);
        EntityRendererRegistry.register(MooCowMod.REINDEER, ReindeerRenderer::new);
        EntityRendererRegistry.register(MooCowMod.ZOM_BEE, ZomBeeRenderer::new);
        EntityRendererRegistry.register(MooCowMod.MOO_COW, MooCowRenderer::new);
        EntityRendererRegistry.register(MooCowMod.MOO_COD, MooCodRenderer::new);
    }
}
