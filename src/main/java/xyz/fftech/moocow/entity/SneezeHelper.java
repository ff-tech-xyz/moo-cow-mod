package xyz.fftech.moocow.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.phys.Vec3;
import xyz.fftech.moocow.MooCowMod;

public final class SneezeHelper {
    public static final int ZOM_BEE_CAP = 8;
    public static final double ZOM_BEE_CAP_RADIUS = 16.0D;

    private SneezeHelper() {
    }

    public static boolean trySneeze(ServerLevel level, Entity source, int minCount, int maxCount) {
        int nearbyZomBees = level.getEntities(
            source,
            source.getBoundingBox().inflate(ZOM_BEE_CAP_RADIUS),
            entity -> entity.getType() == MooCowMod.ZOM_BEE
        ).size();

        if (nearbyZomBees >= ZOM_BEE_CAP) {
            return false;
        }

        int count = minCount + level.getRandom().nextInt(maxCount - minCount + 1);
        count = Math.min(count, ZOM_BEE_CAP - nearbyZomBees);
        Vec3 look = source.getLookAngle();
        if (look.lengthSqr() < 0.0001D) {
            look = new Vec3(0.0D, 0.0D, 1.0D);
        }

        for (int i = 0; i < count; i++) {
            ZomBeeEntity zomBee = MooCowMod.ZOM_BEE.create(level, EntitySpawnReason.MOB_SUMMONED);
            if (zomBee == null) {
                continue;
            }

            double side = (i - (count - 1) * 0.5D) * 0.35D;
            double spawnX = source.getX() + look.x * 1.25D + side;
            double spawnY = source.getY() + source.getBbHeight() * 0.65D;
            double spawnZ = source.getZ() + look.z * 1.25D - side;
            zomBee.setPos(spawnX, spawnY, spawnZ);
            zomBee.setYRot(source.getYRot());
            zomBee.setXRot(source.getXRot());
            zomBee.setDeltaMovement(look.x * 0.16D, 0.18D + level.getRandom().nextDouble() * 0.08D, look.z * 0.16D);
            level.addFreshEntity(zomBee);
        }

        level.playSound(null, source.getX(), source.getY(), source.getZ(), SoundEvents.PANDA_SNEEZE, SoundSource.HOSTILE, 1.0F, 0.75F + level.getRandom().nextFloat() * 0.2F);
        level.sendParticles(ParticleTypes.ITEM_SLIME, source.getX(), source.getY() + source.getBbHeight() * 0.7D, source.getZ(), 18, 0.45D, 0.25D, 0.45D, 0.08D);
        level.sendParticles(ParticleTypes.ASH, source.getX(), source.getY() + source.getBbHeight() * 0.7D, source.getZ(), 10, 0.5D, 0.25D, 0.5D, 0.02D);
        return count > 0;
    }
}
