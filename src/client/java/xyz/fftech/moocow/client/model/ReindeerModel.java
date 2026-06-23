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
    private static final float LEFT_EAR_X_ROT = -0.15F;
    private static final float LEFT_EAR_Z_ROT = 0.45F;
    private static final float RIGHT_EAR_Z_ROT = -0.45F;

    private final ModelPart leftAntler;
    private final ModelPart rightAntler;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart tail;

    public ReindeerModel(ModelPart root) {
        super(root);
        this.leftAntler = this.head.getChild("left_antler");
        this.rightAntler = this.head.getChild("right_antler");
        this.leftEar = this.head.getChild("left_ear");
        this.rightEar = this.head.getChild("right_ear");
        this.tail = root.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition head = root.addOrReplaceChild(
            "head",
            CubeListBuilder.create()
                .texOffs(0, 0).addBox(-4.0F, -4.0F, -7.0F, 8.0F, 7.0F, 7.0F, CubeDeformation.NONE)
                .texOffs(32, 0).addBox(-2.5F, -1.5F, -11.0F, 5.0F, 3.0F, 4.0F, CubeDeformation.NONE)
                .texOffs(104, 32).addBox(-2.5F, -5.2F, -6.4F, 5.0F, 2.0F, 3.0F, CubeDeformation.NONE),
            PartPose.offset(0.0F, 8.5F, -8.0F)
        );

        head.addOrReplaceChild(
            "red_nose",
            CubeListBuilder.create()
                .texOffs(104, 24).addBox(-1.5F, -1.0F, -12.0F, 3.0F, 2.0F, 1.0F, CubeDeformation.NONE),
            PartPose.ZERO
        );

        head.addOrReplaceChild(
            "left_ear",
            CubeListBuilder.create()
                .texOffs(56, 18).addBox(0.0F, -2.5F, -1.0F, 2.0F, 5.0F, 1.0F, CubeDeformation.NONE),
            PartPose.offsetAndRotation(3.4F, -3.0F, -3.0F, LEFT_EAR_X_ROT, 0.0F, LEFT_EAR_Z_ROT)
        );
        head.addOrReplaceChild(
            "right_ear",
            CubeListBuilder.create().mirror(true)
                .texOffs(56, 18).addBox(-2.0F, -2.5F, -1.0F, 2.0F, 5.0F, 1.0F, CubeDeformation.NONE),
            PartPose.offsetAndRotation(-3.4F, -3.0F, -3.0F, LEFT_EAR_X_ROT, 0.0F, RIGHT_EAR_Z_ROT)
        );

        head.addOrReplaceChild(
            "left_antler",
            CubeListBuilder.create()
                .texOffs(70, 0).addBox(1.2F, -9.2F, -4.8F, 1.0F, 7.4F, 1.0F, CubeDeformation.NONE)
                .texOffs(76, 0).addBox(1.8F, -9.8F, -4.8F, 5.0F, 1.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(88, 0).addBox(5.8F, -13.0F, -4.8F, 1.0F, 4.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(94, 0).addBox(4.2F, -12.0F, -5.8F, 1.0F, 3.6F, 1.0F, CubeDeformation.NONE)
                .texOffs(100, 0).addBox(2.7F, -11.2F, -5.9F, 1.0F, 3.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(106, 0).addBox(1.2F, -11.0F, -3.5F, 1.0F, 3.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(112, 0).addBox(4.8F, -11.2F, -3.2F, 1.0F, 2.8F, 1.0F, CubeDeformation.NONE),
            PartPose.ZERO
        );
        head.addOrReplaceChild(
            "right_antler",
            CubeListBuilder.create().mirror(true)
                .texOffs(70, 10).addBox(-2.2F, -9.2F, -4.8F, 1.0F, 7.4F, 1.0F, CubeDeformation.NONE)
                .texOffs(76, 10).addBox(-6.8F, -9.8F, -4.8F, 5.0F, 1.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(88, 10).addBox(-6.8F, -13.0F, -4.8F, 1.0F, 4.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(94, 10).addBox(-5.2F, -12.0F, -5.8F, 1.0F, 3.6F, 1.0F, CubeDeformation.NONE)
                .texOffs(100, 10).addBox(-3.7F, -11.2F, -5.9F, 1.0F, 3.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(106, 10).addBox(-2.2F, -11.0F, -3.5F, 1.0F, 3.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(112, 10).addBox(-5.8F, -11.2F, -3.2F, 1.0F, 2.8F, 1.0F, CubeDeformation.NONE),
            PartPose.ZERO
        );

        root.addOrReplaceChild(
            "body",
            CubeListBuilder.create()
                .texOffs(0, 24).addBox(-5.5F, -9.5F, -6.5F, 11.0F, 19.0F, 9.0F, CubeDeformation.NONE)
                .texOffs(42, 28).addBox(-4.5F, -11.0F, -8.2F, 9.0F, 5.0F, 5.0F, CubeDeformation.NONE)
                .texOffs(42, 44).addBox(-3.5F, -11.4F, -5.2F, 7.0F, 3.0F, 4.0F, CubeDeformation.NONE),
            PartPose.offsetAndRotation(0.0F, 14.0F, 2.0F, BODY_X_ROT, 0.0F, 0.0F)
        );

        root.addOrReplaceChild(
            "right_hind_leg",
            createLegBuilder(false),
            PartPose.offset(-3.2F, 14.0F, 6.2F)
        );
        root.addOrReplaceChild(
            "left_hind_leg",
            createLegBuilder(true),
            PartPose.offset(3.2F, 14.0F, 6.2F)
        );
        root.addOrReplaceChild(
            "right_front_leg",
            createLegBuilder(false),
            PartPose.offset(-3.3F, 14.0F, -4.8F)
        );
        root.addOrReplaceChild(
            "left_front_leg",
            createLegBuilder(true),
            PartPose.offset(3.3F, 14.0F, -4.8F)
        );

        root.addOrReplaceChild(
            "tail",
            CubeListBuilder.create()
                .texOffs(80, 24).addBox(-1.0F, -0.5F, 0.0F, 2.0F, 5.0F, 2.0F, CubeDeformation.NONE)
                .texOffs(90, 24).addBox(-2.0F, 3.0F, -0.5F, 4.0F, 3.0F, 3.0F, CubeDeformation.NONE),
            PartPose.offsetAndRotation(0.0F, 9.5F, 10.2F, TAIL_X_ROT, 0.0F, 0.0F)
        );

        return LayerDefinition.create(mesh, 128, 128);
    }

    private static CubeListBuilder createLegBuilder(boolean mirrored) {
        return CubeListBuilder.create().mirror(mirrored)
            .texOffs(56, 0).addBox(-1.25F, 0.0F, -1.25F, 2.5F, 7.0F, 2.5F, CubeDeformation.NONE)
            .texOffs(56, 12).addBox(-1.5F, 6.5F, -1.5F, 3.0F, 2.4F, 3.0F, CubeDeformation.NONE)
            .texOffs(56, 24).addBox(-1.8F, 8.5F, -1.8F, 3.6F, 1.5F, 3.6F, CubeDeformation.NONE);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        float age = state.ageInTicks;
        float walkAmount = Math.min(state.walkAnimationSpeed, 1.0F);
        float walkCycle = state.walkAnimationPos * 0.6662F;
        float idleBob = Mth.sin(age * 0.08F) * 0.035F;
        float walkBob = Mth.sin(walkCycle) * 0.025F * walkAmount;
        float tailWag = Mth.sin(age * 0.18F) * 0.16F + Mth.sin(walkCycle * 2.0F) * 0.10F * walkAmount;
        float bodyBob = Math.abs(Mth.sin(walkCycle)) * 0.35F * walkAmount;
        float earFlick = Mth.sin(age * 0.11F) * 0.04F;

        if (Mth.sin(age * 0.37F) > 0.92F) {
            earFlick += 0.16F;
        }

        this.leftAntler.xRot = -walkBob;
        this.leftAntler.zRot = idleBob + walkBob;
        this.rightAntler.xRot = -walkBob;
        this.rightAntler.zRot = -idleBob - walkBob;

        this.leftEar.xRot = LEFT_EAR_X_ROT + earFlick * 0.35F;
        this.leftEar.zRot = LEFT_EAR_Z_ROT + earFlick;
        this.rightEar.xRot = LEFT_EAR_X_ROT + earFlick * 0.25F;
        this.rightEar.zRot = RIGHT_EAR_Z_ROT - earFlick * 0.85F;

        this.tail.xRot = TAIL_X_ROT + 0.08F * walkAmount;
        this.tail.yRot = tailWag;

        this.body.xRot = BODY_X_ROT;
        this.body.y = 14.0F + bodyBob;
        this.head.y = 8.5F + bodyBob * 0.45F + Mth.sin(age * 0.07F) * 0.05F;

        float prance = Mth.sin(walkCycle + 1.5707964F) * 0.18F * walkAmount;
        this.rightFrontLeg.xRot += prance;
        this.leftFrontLeg.xRot -= prance;
        this.rightHindLeg.xRot -= prance * 0.45F;
        this.leftHindLeg.xRot += prance * 0.45F;
    }
}
