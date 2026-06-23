package xyz.fftech.moocow.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.fish.Cod;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MooCodEntity extends Cod {
    private static final int BASE_SNEEZE_COOLDOWN_TICKS = 180;
    private int sneezeCooldownTicks = BASE_SNEEZE_COOLDOWN_TICKS;
    private boolean breachSneezeArmed;

    public MooCodEntity(EntityType<? extends Cod> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH, 8.0D)
            .add(Attributes.MOVEMENT_SPEED, 1.0D)
            .add(Attributes.ATTACK_DAMAGE, 2.0D)
            .add(Attributes.FOLLOW_RANGE, 20.0D)
            .add(Attributes.WATER_MOVEMENT_EFFICIENCY, 1.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1.0D, 40));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Villager.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            return;
        }

        ServerLevel level = (ServerLevel) this.level();
        if (--this.sneezeCooldownTicks <= 0 && this.isInWater()) {
            Vec3 movement = this.getDeltaMovement();
            this.setDeltaMovement(movement.x, 0.72D, movement.z);
            this.breachSneezeArmed = true;
            this.sneezeCooldownTicks = BASE_SNEEZE_COOLDOWN_TICKS + this.random.nextInt(50);
            level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.DOLPHIN_SPLASH, SoundSource.HOSTILE, 1.0F, 0.9F);
        }

        if (this.breachSneezeArmed && !this.isInWater() && this.getDeltaMovement().y <= 0.02D) {
            SneezeHelper.trySneeze(level, this, 1, 3);
            this.breachSneezeArmed = false;
        }

        if (this.breachSneezeArmed && this.isInWater() && this.getDeltaMovement().y < 0.0D) {
            this.breachSneezeArmed = false;
        }
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(Items.COD_BUCKET);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return InteractionResult.PASS;
    }
}
