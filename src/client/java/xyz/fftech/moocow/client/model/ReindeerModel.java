package xyz.fftech.moocow.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import xyz.fftech.moocow.MooCowMod;
import xyz.fftech.moocow.client.render.ReindeerRenderState;

public class ReindeerModel extends EntityModel<ReindeerRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MooCowMod.id("reindeer"), "main");

    private static final float HEAD_Y = 7.0F;
    private static final float HEAD_Z = -9.0F;
    private static final float BODY_Y = 13.0F;
    private static final float BODY_Z = 1.0F;
    private static final float LEFT_EAR_X_ROT = -0.18F;
    private static final float LEFT_EAR_Z_ROT = 0.42F;
    private static final float RIGHT_EAR_Z_ROT = -0.42F;
    private static final float TAIL_X_ROT = -0.72F;

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart leftAntler;
    private final ModelPart rightAntler;
    private final ModelPart redNose;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart tail;

    public ReindeerModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.rightHindLeg = root.getChild("right_hind_leg");
        this.leftHindLeg = root.getChild("left_hind_leg");
        this.rightFrontLeg = root.getChild("right_front_leg");
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.leftAntler = this.head.getChild("left_antler");
        this.rightAntler = this.head.getChild("right_antler");
        this.redNose = this.head.getChild("red_nose");
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
                .texOffs(0, 0).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 7.0F, 6.0F, CubeDeformation.NONE)
                .texOffs(32, 0).addBox(-2.5F, -1.2F, -8.8F, 5.0F, 3.0F, 4.0F, CubeDeformation.NONE)
                .texOffs(52, 0).addBox(-2.0F, -4.6F, -4.5F, 4.0F, 2.0F, 3.0F, CubeDeformation.NONE),
            PartPose.offset(0.0F, HEAD_Y, HEAD_Z)
        );

        head.addOrReplaceChild(
            "red_nose",
            CubeListBuilder.create()
                .texOffs(104, 24).addBox(-1.5F, -0.9F, -9.5F, 3.0F, 2.0F, 1.0F, CubeDeformation.NONE),
            PartPose.ZERO
        );

        head.addOrReplaceChild(
            "left_ear",
            CubeListBuilder.create()
                .texOffs(56, 18).addBox(0.0F, -2.4F, -0.8F, 2.0F, 5.0F, 1.0F, CubeDeformation.NONE),
            PartPose.offsetAndRotation(3.3F, -3.0F, -2.2F, LEFT_EAR_X_ROT, 0.0F, LEFT_EAR_Z_ROT)
        );
        head.addOrReplaceChild(
            "right_ear",
            CubeListBuilder.create().mirror(true)
                .texOffs(56, 18).addBox(-2.0F, -2.4F, -0.8F, 2.0F, 5.0F, 1.0F, CubeDeformation.NONE),
            PartPose.offsetAndRotation(-3.3F, -3.0F, -2.2F, LEFT_EAR_X_ROT, 0.0F, RIGHT_EAR_Z_ROT)
        );

        head.addOrReplaceChild(
            "left_antler",
            CubeListBuilder.create()
                .texOffs(70, 0).addBox(1.1F, -10.4F, -3.5F, 1.0F, 7.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(76, 0).addBox(1.6F, -10.2F, -3.5F, 5.0F, 1.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(88, 0).addBox(5.6F, -13.0F, -3.5F, 1.0F, 3.8F, 1.0F, CubeDeformation.NONE)
                .texOffs(94, 0).addBox(4.0F, -12.0F, -4.4F, 1.0F, 3.2F, 1.0F, CubeDeformation.NONE)
                .texOffs(100, 0).addBox(2.7F, -11.2F, -4.3F, 1.0F, 2.7F, 1.0F, CubeDeformation.NONE)
                .texOffs(106, 0).addBox(1.1F, -11.0F, -2.2F, 1.0F, 2.7F, 1.0F, CubeDeformation.NONE),
            PartPose.ZERO
        );
        head.addOrReplaceChild(
            "right_antler",
            CubeListBuilder.create().mirror(true)
                .texOffs(70, 10).addBox(-2.1F, -10.4F, -3.5F, 1.0F, 7.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(76, 10).addBox(-6.6F, -10.2F, -3.5F, 5.0F, 1.0F, 1.0F, CubeDeformation.NONE)
                .texOffs(88, 10).addBox(-6.6F, -13.0F, -3.5F, 1.0F, 3.8F, 1.0F, CubeDeformation.NONE)
                .texOffs(94, 10).addBox(-5.0F, -12.0F, -4.4F, 1.0F, 3.2F, 1.0F, CubeDeformation.NONE)
                .texOffs(100, 10).addBox(-3.7F, -11.2F, -4.3F, 1.0F, 2.7F, 1.0F, CubeDeformation.NONE)
                .texOffs(106, 10).addBox(-2.1F, -11.0F, -2.2F, 1.0F, 2.7F, 1.0F, CubeDeformation.NONE),
            PartPose.ZERO
        );

        root.addOrReplaceChild(
            "body",
            CubeListBuilder.create()
                .texOffs(0, 24).addBox(-5.5F, -6.0F, -9.0F, 11.0F, 10.0F, 18.0F, CubeDeformation.NONE)
                .texOffs(42, 28).addBox(-4.0F, -7.2F, -8.4F, 8.0F, 3.0F, 7.0F, CubeDeformation.NONE)
                .texOffs(42, 44).addBox(-3.4F, -7.4F, -4.0F, 6.8F, 2.4F, 4.0F, CubeDeformation.NONE),
            PartPose.offset(0.0F, BODY_Y, BODY_Z)
        );

        root.addOrReplaceChild(
            "right_hind_leg",
            createLegBuilder(false),
            PartPose.offset(-3.2F, 14.0F, 7.0F)
        );
        root.addOrReplaceChild(
            "left_hind_leg",
            createLegBuilder(true),
            PartPose.offset(3.2F, 14.0F, 7.0F)
        );
        root.addOrReplaceChild(
            "right_front_leg",
            createLegBuilder(false),
            PartPose.offset(-3.3F, 14.0F, -5.8F)
        );
        root.addOrReplaceChild(
            "left_front_leg",
            createLegBuilder(true),
            PartPose.offset(3.3F, 14.0F, -5.8F)
        );

        root.addOrReplaceChild(
            "tail",
            CubeListBuilder.create()
                .texOffs(80, 24).addBox(-1.0F, -0.4F, 0.0F, 2.0F, 4.0F, 2.0F, CubeDeformation.NONE)
                .texOffs(90, 24).addBox(-1.8F, 2.8F, -0.3F, 3.6F, 2.6F, 2.6F, CubeDeformation.NONE),
            PartPose.offsetAndRotation(0.0F, 11.0F, 10.2F, TAIL_X_ROT, 0.0F, 0.0F)
        );

        return LayerDefinition.create(mesh, 128, 128);
    }

    private static CubeListBuilder createLegBuilder(boolean mirrored) {
        return CubeListBuilder.create().mirror(mirrored)
            .texOffs(56, 0).addBox(-1.25F, 0.0F, -1.25F, 2.5F, 7.0F, 2.5F, CubeDeformation.NONE)
            .texOffs(56, 12).addBox(-1.5F, 6.5F, -1.5F, 3.0F, 2.2F, 3.0F, CubeDeformation.NONE)
            .texOffs(56, 24).addBox(-1.8F, 8.3F, -1.8F, 3.6F, 1.7F, 3.6F, CubeDeformation.NONE);
    }

    @Override
    public void setupAnim(ReindeerRenderState state) {
        super.setupAnim(state);

        float age = state.ageInTicks;
        float walkAmount = Math.min(state.walkAnimationSpeed, 1.0F);
        float walkCycle = state.walkAnimationPos * 0.6662F;
        float idleBreath = Mth.sin(age * 0.08F) * 0.015F;
        float tailWag = Mth.sin(age * 0.18F) * 0.10F + Mth.sin(walkCycle * 1.8F) * 0.08F * walkAmount;
        float earFlick = Mth.sin(age * 0.11F) * 0.035F;

        if (Mth.sin(age * 0.37F) > 0.92F) {
            earFlick += 0.13F;
        }

        this.head.x = 0.0F;
        this.head.y = HEAD_Y;
        this.head.z = HEAD_Z;
        this.head.xRot = state.xRot * 0.017453292F + idleBreath;
        this.head.yRot = state.yRot * 0.017453292F;
        this.head.zRot = 0.0F;

        this.body.x = 0.0F;
        this.body.y = BODY_Y;
        this.body.z = BODY_Z;
        this.body.xRot = 0.0F;
        this.body.yRot = 0.0F;
        this.body.zRot = 0.0F;

        float legSwing = Mth.cos(walkCycle) * 0.75F * walkAmount;
        float oppositeLegSwing = Mth.cos(walkCycle + Mth.PI) * 0.75F * walkAmount;
        this.rightHindLeg.xRot = legSwing;
        this.leftHindLeg.xRot = oppositeLegSwing;
        this.rightFrontLeg.xRot = oppositeLegSwing;
        this.leftFrontLeg.xRot = legSwing;
        this.rightHindLeg.yRot = 0.0F;
        this.leftHindLeg.yRot = 0.0F;
        this.rightFrontLeg.yRot = 0.0F;
        this.leftFrontLeg.yRot = 0.0F;
        this.rightHindLeg.zRot = 0.0F;
        this.leftHindLeg.zRot = 0.0F;
        this.rightFrontLeg.zRot = 0.0F;
        this.leftFrontLeg.zRot = 0.0F;

        this.leftAntler.xRot = -idleBreath;
        this.leftAntler.yRot = 0.0F;
        this.leftAntler.zRot = idleBreath * 0.7F;
        this.rightAntler.xRot = -idleBreath;
        this.rightAntler.yRot = 0.0F;
        this.rightAntler.zRot = -idleBreath * 0.7F;

        this.redNose.visible = state.hasRedNose;

        this.leftEar.xRot = LEFT_EAR_X_ROT + earFlick * 0.35F;
        this.leftEar.yRot = 0.0F;
        this.leftEar.zRot = LEFT_EAR_Z_ROT + earFlick;
        this.rightEar.xRot = LEFT_EAR_X_ROT + earFlick * 0.25F;
        this.rightEar.yRot = 0.0F;
        this.rightEar.zRot = RIGHT_EAR_Z_ROT - earFlick * 0.85F;

        this.tail.xRot = TAIL_X_ROT + 0.05F * walkAmount;
        this.tail.yRot = tailWag;
        this.tail.zRot = 0.0F;
    }
}
