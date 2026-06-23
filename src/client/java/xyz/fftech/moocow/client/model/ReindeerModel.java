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

    private static final float BODY_X_ROT = 1.5707964F;
    private static final float TAIL_X_ROT = -0.70F;

    private final ModelPart leftAntler;
    private final ModelPart rightAntler;
    private final ModelPart tail;

    public ReindeerModel(ModelPart root) {
        super(root);
        this.leftAntler = this.head.getChild("left_antler");
        this.rightAntler = this.head.getChild("right_antler");
        this.tail = root.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition head = root.addOrReplaceChild(
            "head",
            CubeListBuilder.create()
                .texOffs(0, 0).addBox(-4.0F, -4.0F, -7.0F, 8.0F, 7.0F, 7.0F, CubeDeformation.NONE)
                .texOffs(32, 0).addBox(-2.5F, -1.5F, -11.0F, 5.0F, 3.0F, 4.0F, CubeDeformation.NONE),
            PartPose.offset(0.0F, 8.5F, -8.0F)
        );

        head.addOrReplaceChild(
            "left_ear",
            CubeListBuilder.create()
                .texOffs(56, 18).addBox(0.0F, -2.5F, -1.0F, 2.0F, 5.0F, 1.0F, CubeDeformation.NONE),
            PartPose.offsetAndRotation(3.4F, -3.0F, -3.0F, -0.15F, 0.0F, 0.45F)
        );
        head.addOrReplaceChild(
            "right_ear",
            CubeListBuilder.create().mirror(true)
                .texOffs(56, 18).addBox(-2.0F, -2.5F, -1.0F, 2.0F, 5.0F, 1.0F, CubeDeformation.NONE),
            PartPose.offsetAndRotation(-3.4F, -3.0F, -3.0F, -0.15F, 0.0F, -0.45F)
        );

        head.addOrReplaceChild(
            "left_antler",
            CubeListBuilder.create()
                .texOffs(70, 0).addBox(1.2F, -9.0F, -4.8F, 1.0F, 7.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(76, 0).addBox(1.8F, -9.4F, -4.8F, 4.0F, 1.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(88, 0).addBox(4.8F, -12.0F, -4.8F, 1.0F, 3.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(94, 0).addBox(3.0F, -11.2F, -5.6F, 1.0F, 3.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(100, 0).addBox(1.2F, -10.6F, -3.9F, 1.0F, 3.0F, 1.0F, CubeDeformation.NONE),
            PartPose.ZERO
        );
        head.addOrReplaceChild(
            "right_antler",
            CubeListBuilder.create().mirror(true)
                .texOffs(70, 10).addBox(-2.2F, -9.0F, -4.8F, 1.0F, 7.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(76, 10).addBox(-5.8F, -9.4F, -4.8F, 4.0F, 1.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(88, 10).addBox(-5.8F, -12.0F, -4.8F, 1.0F, 3.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(94, 10).addBox(-4.0F, -11.2F, -5.6F, 1.0F, 3.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(100, 10).addBox(-2.2F, -10.6F, -3.9F, 1.0F, 3.0F, 1.0F, CubeDeformation.NONE),
            PartPose.ZERO
        );

        root.addOrReplaceChild(
            "body",
            CubeListBuilder.create()
                .texOffs(0, 24).addBox(-5.0F, -9.0F, -6.0F, 10.0F, 18.0F, 9.0F, CubeDeformation.NONE)
                .texOffs(40, 28).addBox(-4.0F, -10.0F, -7.5F, 8.0F, 6.0F, 5.0F, CubeDeformation.NONE),
            PartPose.offsetAndRotation(0.0F, 14.0F, 2.0F, BODY_X_ROT, 0.0F, 0.0F)
        );

        root.addOrReplaceChild(
            "right_hind_leg",
            createLegBuilder(false),
            PartPose.offset(-3.1F, 14.0F, 6.0F)
        );
        root.addOrReplaceChild(
            "left_hind_leg",
            createLegBuilder(true),
            PartPose.offset(3.1F, 14.0F, 6.0F)
        );
        root.addOrReplaceChild(
            "right_front_leg",
            createLegBuilder(false),
            PartPose.offset(-3.1F, 14.0F, -4.8F)
        );
        root.addOrReplaceChild(
            "left_front_leg",
            createLegBuilder(true),
            PartPose.offset(3.1F, 14.0F, -4.8F)
        );

        root.addOrReplaceChild(
            "tail",
            CubeListBuilder.create()
                .texOffs(80, 24).addBox(-1.0F, -0.5F, 0.0F, 2.0F, 5.0F, 2.0F, CubeDeformation.NONE)
                .texOffs(90, 24).addBox(-2.0F, 3.0F, -0.5F, 4.0F, 3.0F, 3.0F, CubeDeformation.NONE),
            PartPose.offsetAndRotation(0.0F, 9.5F, 10.0F, TAIL_X_ROT, 0.0F, 0.0F)
        );

        return LayerDefinition.create(mesh, 128, 128);
    }

    private static CubeListBuilder createLegBuilder(boolean mirrored) {
        return CubeListBuilder.create().mirror(mirrored)
            .texOffs(56, 0).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.5F, 3.0F, CubeDeformation.NONE)
            .texOffs(56, 12).addBox(-1.75F, 8.0F, -1.75F, 3.5F, 2.0F, 3.5F, CubeDeformation.NONE);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        float idleBob = Mth.sin(state.ageInTicks * 0.08F) * 0.035F;
        float tailWag = Mth.sin(state.ageInTicks * 0.18F) * 0.16F;
        this.leftAntler.zRot = idleBob;
        this.rightAntler.zRot = -idleBob;
        this.tail.xRot = TAIL_X_ROT;
        this.tail.yRot = tailWag;
    }
}
