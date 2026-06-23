package xyz.fftech.moocow.client.model;

import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import xyz.fftech.moocow.MooCowMod;

public class ReindeerModel extends QuadrupedModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MooCowMod.id("reindeer"), "main");

    private final ModelPart leftAntler;
    private final ModelPart rightAntler;

    public ReindeerModel(ModelPart root) {
        super(root);
        this.leftAntler = this.head.getChild("left_antler");
        this.rightAntler = this.head.getChild("right_antler");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = QuadrupedModel.createBodyMesh(12, false, false, CubeDeformation.NONE);
        PartDefinition root = mesh.getRoot();
        PartDefinition head = root.getChild("head");

        head.addOrReplaceChild(
            "left_antler",
            CubeListBuilder.create()
                .texOffs(0, 32).addBox(1.5F, -10.0F, -2.0F, 2.0F, 8.0F, 1.0F)
                .texOffs(6, 32).addBox(3.0F, -11.0F, -2.0F, 1.0F, 4.0F, 1.0F)
                .texOffs(10, 32).addBox(0.5F, -9.0F, -2.0F, 1.0F, 3.0F, 1.0F)
                .texOffs(14, 32).addBox(2.0F, -8.5F, -3.0F, 1.0F, 3.0F, 1.0F),
            PartPose.ZERO
        );
        head.addOrReplaceChild(
            "right_antler",
            CubeListBuilder.create()
                .texOffs(0, 41).addBox(-3.5F, -10.0F, -2.0F, 2.0F, 8.0F, 1.0F)
                .texOffs(6, 41).addBox(-4.0F, -11.0F, -2.0F, 1.0F, 4.0F, 1.0F)
                .texOffs(10, 41).addBox(-1.5F, -9.0F, -2.0F, 1.0F, 3.0F, 1.0F)
                .texOffs(14, 41).addBox(-3.0F, -8.5F, -3.0F, 1.0F, 3.0F, 1.0F),
            PartPose.ZERO
        );

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        float idleBob = Mth.sin(state.ageInTicks * 0.08F) * 0.035F;
        this.leftAntler.zRot = idleBob;
        this.rightAntler.zRot = -idleBob;
    }
}
