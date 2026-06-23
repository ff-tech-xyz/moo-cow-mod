package xyz.fftech.moocow.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.bee.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import xyz.fftech.moocow.MooCowMod;
import xyz.fftech.moocow.entity.ZomBeeEntity;

public class RandeerCheeseBucketItem extends Item {
    public RandeerCheeseBucketItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (!(target instanceof Bee bee) || target instanceof ZomBeeEntity) {
            return super.interactLivingEntity(stack, player, target, hand);
        }

        if (player.level().isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        ServerLevel level = (ServerLevel) player.level();
        ZomBeeEntity zomBee = MooCowMod.ZOM_BEE.create(level, EntitySpawnReason.MOB_SUMMONED);
        if (zomBee == null) {
            return InteractionResult.FAIL;
        }

        zomBee.setPos(bee.getX(), bee.getY(), bee.getZ());
        zomBee.setYRot(bee.getYRot());
        zomBee.setXRot(bee.getXRot());
        zomBee.setDeltaMovement(bee.getDeltaMovement());
        if (bee.hasCustomName()) {
            zomBee.setCustomName(bee.getCustomName());
            zomBee.setCustomNameVisible(bee.isCustomNameVisible());
        }

        level.addFreshEntity(zomBee);
        bee.discard();

        if (!player.getAbilities().instabuild) {
            player.setItemInHand(hand, new ItemStack(Items.BUCKET));
        }

        level.playSound(null, zomBee.getX(), zomBee.getY(), zomBee.getZ(), SoundEvents.SLIME_BLOCK_BREAK, SoundSource.HOSTILE, 1.0F, 0.65F);
        level.sendParticles(ParticleTypes.ITEM_SLIME, zomBee.getX(), zomBee.getY() + 0.4D, zomBee.getZ(), 18, 0.35D, 0.25D, 0.35D, 0.08D);
        level.sendParticles(ParticleTypes.ASH, zomBee.getX(), zomBee.getY() + 0.4D, zomBee.getZ(), 10, 0.35D, 0.25D, 0.35D, 0.02D);
        return InteractionResult.SUCCESS_SERVER;
    }
}
