package xyz.fftech.moocow.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import xyz.fftech.moocow.MooCowMod;

public class ReindeerEntity extends Animal {
    private static final Ingredient FOOD = Ingredient.of(Items.WHEAT, Items.SWEET_BERRIES, Items.APPLE);
    private static final EntityDataAccessor<Boolean> HAS_RED_NOSE = SynchedEntityData.defineId(ReindeerEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDimensions BABY_DIMENSIONS = EntityDimensions.scalable(0.55F, 0.875F).withEyeHeight(0.675F);
    private static final int RED_NOSE_CHANCE = 20;
    private static final int MILKING_COOLDOWN_TICKS = 20 * 60 * 3;

    private int milkingCooldownTicks;

    public ReindeerEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(HAS_RED_NOSE, false);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes()
            .add(Attributes.MAX_HEALTH, 18.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.24D)
            .add(Attributes.FOLLOW_RANGE, 24.0D)
            .add(Attributes.STEP_HEIGHT, 1.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, FOOD, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return FOOD.test(stack);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide() && this.milkingCooldownTicks > 0) {
            --this.milkingCooldownTicks;
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.is(Items.BUCKET) && !this.isBaby()) {
            if (this.level().isClientSide()) {
                return InteractionResult.SUCCESS;
            }

            if (this.milkingCooldownTicks > 0) {
                return InteractionResult.FAIL;
            }

            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack filledBucket = ItemUtils.createFilledResult(stack, player, MooCowMod.RANDEER_MILK_BUCKET.getDefaultInstance());
            player.setItemInHand(hand, filledBucket);
            this.milkingCooldownTicks = MILKING_COOLDOWN_TICKS;

            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("HasRedNose", this.hasRedNose());
        output.putInt("MilkingCooldown", this.milkingCooldownTicks);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setRedNose(input.getBooleanOr("HasRedNose", false));
        this.milkingCooldownTicks = input.getIntOr("MilkingCooldown", 0);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData spawnData) {
        SpawnGroupData finalizedData = super.finalizeSpawn(level, difficulty, spawnReason, spawnData);

        if (spawnReason != EntitySpawnReason.BREEDING && spawnReason != EntitySpawnReason.LOAD) {
            this.setRedNose(level.getRandom().nextInt(RED_NOSE_CHANCE) == 0);
        }

        return finalizedData;
    }

    @Override
    public EntityDimensions getDefaultDimensions(Pose pose) {
        return this.isBaby() ? BABY_DIMENSIONS : super.getDefaultDimensions(pose);
    }

    public boolean hasRedNose() {
        return this.entityData.get(HAS_RED_NOSE);
    }

    public void setRedNose(boolean hasRedNose) {
        this.entityData.set(HAS_RED_NOSE, hasRedNose);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        ReindeerEntity baby = MooCowMod.REINDEER.create(level, EntitySpawnReason.BREEDING);

        if (baby != null) {
            boolean inheritsRedNose = false;

            if (otherParent instanceof ReindeerEntity otherReindeer) {
                inheritsRedNose = this.random.nextBoolean() ? this.hasRedNose() : otherReindeer.hasRedNose();
            }

            baby.setRedNose(inheritsRedNose || this.random.nextInt(RED_NOSE_CHANCE) == 0);
        }

        return baby;
    }
}
