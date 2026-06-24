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

    private static final float DEG_TO_RAD = 0.017453292F;
    private static final float BODY_Y = 13.0F;
    private static final float BODY_Z = 1.5F;
    private static final float NECK_Y = 10.0F;
    private static final float NECK_Z = -7.5F;
    private static final float HEAD_Y = 6.5F;
    private static final float HEAD_Z = -10.5F;
    private static final float TAIL_X_ROT = -38.0F * DEG_TO_RAD;
    private static final float NECK_X_ROT = -16.0F * DEG_TO_RAD;
    private static final float LEFT_EAR_X_ROT = -11.0F * DEG_TO_RAD;
    private static final float LEFT_EAR_Z_ROT = 28.0F * DEG_TO_RAD;
    private static final float RIGHT_EAR_Z_ROT = -28.0F * DEG_TO_RAD;

    private final ModelPart body;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightHindLower;
    private final ModelPart leftHindLower;
    private final ModelPart rightFrontLower;
    private final ModelPart leftFrontLower;
    private final ModelPart leftAntler;
    private final ModelPart rightAntler;
    private final ModelPart redNose;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart tail;

    public ReindeerModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.neck = root.getChild("neck");
        this.head = root.getChild("head");
        this.rightHindLeg = root.getChild("right_hind_leg");
        this.leftHindLeg = root.getChild("left_hind_leg");
        this.rightFrontLeg = root.getChild("right_front_leg");
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.rightHindLower = this.rightHindLeg.getChild("lower");
        this.leftHindLower = this.leftHindLeg.getChild("lower");
        this.rightFrontLower = this.rightFrontLeg.getChild("lower");
        this.leftFrontLower = this.leftFrontLeg.getChild("lower");
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

        root.addOrReplaceChild(
            "body",
            CubeListBuilder.create()
                .texOffs(0, 24).addBox(-5.8F, -6.0F, -10.0F, 11.6F, 10.0F, 18.5F, CubeDeformation.NONE)
                .texOffs(0, 24).addBox(-5.4F, -4.7F, 4.7F, 10.8F, 8.3F, 5.0F, CubeDeformation.NONE)
                .texOffs(48, 58).addBox(-5.7F, -7.2F, -11.1F, 11.4F, 5.2F, 7.4F, CubeDeformation.NONE)
                .texOffs(0, 24).addBox(-4.8F, 2.3F, -7.9F, 9.6F, 2.8F, 14.0F, CubeDeformation.NONE)
                .texOffs(88, 104).addBox(-4.5F, -7.8F, -7.0F, 9.0F, 1.4F, 11.5F, CubeDeformation.NONE)
                .texOffs(48, 98).addBox(-4.6F, -5.7F, -11.8F, 9.2F, 6.8F, 2.1F, CubeDeformation.NONE)
                .texOffs(48, 98).addBox(4.8F, -3.0F, -7.7F, 1.5F, 5.0F, 11.0F, CubeDeformation.NONE)
                .texOffs(48, 98).addBox(-6.3F, -3.0F, -7.7F, 1.5F, 5.0F, 11.0F, CubeDeformation.NONE),
            PartPose.offset(0.0F, BODY_Y, BODY_Z)
        );

        root.addOrReplaceChild(
            "neck",
            CubeListBuilder.create()
                .texOffs(48, 98).addBox(-3.0F, -5.0F, -1.4F, 6.0F, 7.3F, 3.7F, CubeDeformation.NONE)
                .texOffs(48, 98).addBox(-2.5F, -5.8F, 1.5F, 5.0F, 4.6F, 1.5F, CubeDeformation.NONE)
                .texOffs(48, 98).addBox(-4.0F, -0.4F, -1.3F, 8.0F, 5.1F, 2.2F, CubeDeformation.NONE),
            PartPose.offsetAndRotation(0.0F, NECK_Y, NECK_Z, NECK_X_ROT, 0.0F, 0.0F)
        );

        PartDefinition head = root.addOrReplaceChild(
            "head",
            CubeListBuilder.create()
                .texOffs(0, 0).addBox(-4.2F, -4.2F, -3.1F, 8.4F, 7.1F, 6.4F, CubeDeformation.NONE)
                .texOffs(32, 0).addBox(-3.2F, -1.5F, -6.9F, 6.4F, 3.7F, 4.8F, CubeDeformation.NONE)
                .texOffs(32, 0).addBox(-2.1F, -3.1F, -5.5F, 4.2F, 2.3F, 3.2F, CubeDeformation.NONE)
                .texOffs(48, 98).addBox(-3.5F, -0.3F, -2.8F, 1.8F, 3.0F, 2.3F, CubeDeformation.NONE)
                .texOffs(48, 98).addBox(1.7F, -0.3F, -2.8F, 1.8F, 3.0F, 2.3F, CubeDeformation.NONE)
                .texOffs(48, 98).addBox(-2.0F, -5.0F, -2.2F, 4.0F, 2.1F, 2.9F, CubeDeformation.NONE)
                .texOffs(116, 104).addBox(-3.7F, -1.8F, -3.6F, 1.0F, 1.1F, 0.35F, CubeDeformation.NONE)
                .texOffs(116, 104).addBox(2.7F, -1.8F, -3.6F, 1.0F, 1.1F, 0.35F, CubeDeformation.NONE),
            PartPose.offset(0.0F, HEAD_Y, HEAD_Z)
        );

        head.addOrReplaceChild(
            "red_nose",
            CubeListBuilder.create()
                .texOffs(100, 24).addBox(-1.5F, -0.8F, -7.8F, 3.0F, 2.2F, 1.1F, CubeDeformation.NONE),
            PartPose.ZERO
        );

        head.addOrReplaceChild(
            "left_ear",
            CubeListBuilder.create()
                .texOffs(60, 24).addBox(-0.3F, -2.6F, -0.4F, 1.8F, 5.2F, 1.2F, CubeDeformation.NONE)
                .texOffs(60, 24).addBox(0.1F, -1.6F, -0.5F, 1.3F, 3.6F, 1.4F, CubeDeformation.NONE),
            PartPose.offsetAndRotation(3.6F, -2.7F, 0.0F, LEFT_EAR_X_ROT, 0.0F, LEFT_EAR_Z_ROT)
        );
        head.addOrReplaceChild(
            "right_ear",
            CubeListBuilder.create().mirror(true)
                .texOffs(60, 24).addBox(-1.5F, -2.6F, -0.4F, 1.8F, 5.2F, 1.2F, CubeDeformation.NONE)
                .texOffs(60, 24).addBox(-1.4F, -1.6F, -0.5F, 1.3F, 3.6F, 1.4F, CubeDeformation.NONE),
            PartPose.offsetAndRotation(-3.6F, -2.7F, 0.0F, LEFT_EAR_X_ROT, 0.0F, RIGHT_EAR_Z_ROT)
        );

        addAntler(head, "left_antler", false, 2.2F);
        addAntler(head, "right_antler", true, -2.2F);

        addLeg(root, "right_hind_leg", -3.3F, 7.2F, false, true);
        addLeg(root, "left_hind_leg", 3.3F, 7.2F, true, true);
        addLeg(root, "right_front_leg", -3.4F, -5.6F, false, false);
        addLeg(root, "left_front_leg", 3.4F, -5.6F, true, false);

        root.addOrReplaceChild(
            "tail",
            CubeListBuilder.create()
                .texOffs(48, 98).addBox(-1.1F, -0.6F, -0.6F, 2.2F, 3.5F, 1.9F, CubeDeformation.NONE)
                .texOffs(48, 98).addBox(-2.2F, 2.2F, -0.8F, 4.4F, 3.1F, 2.5F, CubeDeformation.NONE),
            PartPose.offsetAndRotation(0.0F, 11.0F, 10.8F, TAIL_X_ROT, 0.0F, 0.0F)
        );

        return LayerDefinition.create(mesh, 128, 128);
    }

    private static void addAntler(PartDefinition head, String name, boolean mirrored, float xOffset) {
        CubeListBuilder cubes = CubeListBuilder.create().mirror(mirrored)
            .texOffs(60, 0).addBox(mirrored ? -0.2F : -0.8F, -6.8F, -0.8F, 1.0F, 7.0F, 1.0F, CubeDeformation.NONE)
            .texOffs(60, 0).addBox(mirrored ? -5.0F : -0.2F, -6.8F, -0.8F, 5.2F, 1.1F, 1.0F, CubeDeformation.NONE)
            .texOffs(60, 0).addBox(mirrored ? -5.2F : 4.1F, -10.0F, -0.8F, 1.1F, 4.2F, 1.0F, CubeDeformation.NONE)
            .texOffs(60, 0).addBox(mirrored ? -3.5F : 2.4F, -9.1F, -1.8F, 1.1F, 3.7F, 1.1F, CubeDeformation.NONE)
            .texOffs(60, 0).addBox(mirrored ? -1.8F : 0.8F, -8.2F, -1.9F, 1.0F, 3.1F, 1.1F, CubeDeformation.NONE)
            .texOffs(60, 0).addBox(mirrored ? -0.2F : -0.8F, -8.5F, 0.6F, 1.0F, 3.3F, 1.0F, CubeDeformation.NONE)
            .texOffs(60, 0).addBox(mirrored ? -4.0F : 3.0F, -6.8F, 0.8F, 1.0F, 2.9F, 1.0F, CubeDeformation.NONE)
            .texOffs(60, 0).addBox(mirrored ? -5.9F : 4.8F, -8.4F, -0.9F, 1.1F, 2.8F, 1.1F, CubeDeformation.NONE);

        head.addOrReplaceChild(
            name,
            cubes,
            PartPose.offsetAndRotation(xOffset, -3.9F, 0.3F, 0.0F, 0.0F, (mirrored ? -5.0F : 5.0F) * DEG_TO_RAD)
        );
    }

    private static void addLeg(PartDefinition root, String name, float x, float z, boolean mirrored, boolean hind) {
        PartDefinition leg = root.addOrReplaceChild(
            name,
            CubeListBuilder.create().mirror(mirrored)
                .texOffs(48, 58).addBox(-1.45F, -0.4F, -1.4F, 2.9F, 5.7F, 2.9F, CubeDeformation.NONE)
                .texOffs(48, 98).addBox(-1.25F, -0.9F, hind ? -1.9F : -2.0F, 2.5F, 3.1F, 0.9F, CubeDeformation.NONE),
            PartPose.offset(x, 14.0F, z)
        );

        PartDefinition lower = leg.addOrReplaceChild(
            "lower",
            CubeListBuilder.create().mirror(mirrored)
                .texOffs(86, 58).addBox(-1.05F, -0.2F, -1.05F, 2.1F, 4.1F, 2.1F, CubeDeformation.NONE),
            PartPose.offset(0.0F, 5.0F, 0.0F)
        );

        lower.addOrReplaceChild(
            "hoof",
            CubeListBuilder.create().mirror(mirrored)
                .texOffs(0, 88).addBox(-1.5F, 3.2F, -1.55F, 3.0F, 1.8F, 3.1F, CubeDeformation.NONE),
            PartPose.ZERO
        );
    }

    @Override
    public void setupAnim(ReindeerRenderState state) {
        super.setupAnim(state);

        float age = state.ageInTicks;
        float walkAmount = Math.min(state.walkAnimationSpeed, 1.0F);
        float walkCycle = state.walkAnimationPos * 0.72F;
        float idleBreath = Mth.sin(age * 0.08F) * 0.018F;
        float bodyBob = Mth.sin(walkCycle * 2.0F) * 0.22F * walkAmount;
        float tailWag = Mth.sin(age * 0.18F) * 0.10F + Mth.sin(walkCycle * 1.8F) * 0.11F * walkAmount;
        float earFlick = Mth.sin(age * 0.11F) * 0.035F;

        if (Mth.sin(age * 0.37F) > 0.92F) {
            earFlick += 0.13F;
        }

        this.body.x = 0.0F;
        this.body.y = BODY_Y + bodyBob;
        this.body.z = BODY_Z;
        this.body.xRot = Mth.sin(walkCycle * 2.0F) * 0.018F * walkAmount;
        this.body.yRot = 0.0F;
        this.body.zRot = Mth.sin(walkCycle) * 0.012F * walkAmount;

        this.neck.x = 0.0F;
        this.neck.y = NECK_Y + bodyBob * 0.55F;
        this.neck.z = NECK_Z;
        this.neck.xRot = NECK_X_ROT + idleBreath - Mth.sin(walkCycle * 2.0F) * 0.035F * walkAmount;
        this.neck.yRot = state.yRot * 0.004F;
        this.neck.zRot = 0.0F;

        this.head.x = 0.0F;
        this.head.y = HEAD_Y + bodyBob * 0.35F;
        this.head.z = HEAD_Z;
        this.head.xRot = state.xRot * DEG_TO_RAD + idleBreath - Mth.sin(walkCycle * 2.0F) * 0.025F * walkAmount;
        this.head.yRot = state.yRot * DEG_TO_RAD;
        this.head.zRot = 0.0F;

        float rightHindSwing = Mth.cos(walkCycle) * 0.58F * walkAmount;
        float leftHindSwing = Mth.cos(walkCycle + Mth.PI) * 0.58F * walkAmount;
        float rightFrontSwing = leftHindSwing;
        float leftFrontSwing = rightHindSwing;
        animateLeg(this.rightHindLeg, this.rightHindLower, rightHindSwing, walkAmount);
        animateLeg(this.leftHindLeg, this.leftHindLower, leftHindSwing, walkAmount);
        animateLeg(this.rightFrontLeg, this.rightFrontLower, rightFrontSwing, walkAmount);
        animateLeg(this.leftFrontLeg, this.leftFrontLower, leftFrontSwing, walkAmount);

        this.leftAntler.xRot = -idleBreath;
        this.leftAntler.yRot = Mth.sin(age * 0.05F) * 0.01F;
        this.leftAntler.zRot = 5.0F * DEG_TO_RAD + idleBreath * 0.7F;
        this.rightAntler.xRot = -idleBreath;
        this.rightAntler.yRot = -Mth.sin(age * 0.05F) * 0.01F;
        this.rightAntler.zRot = -5.0F * DEG_TO_RAD - idleBreath * 0.7F;

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

    private static void animateLeg(ModelPart upper, ModelPart lower, float swing, float walkAmount) {
        upper.xRot = swing;
        upper.yRot = 0.0F;
        upper.zRot = 0.0F;
        lower.xRot = Math.max(0.0F, -swing * 0.55F) + Mth.sin(Math.abs(swing)) * 0.08F * walkAmount;
        lower.yRot = 0.0F;
        lower.zRot = 0.0F;
    }
}
