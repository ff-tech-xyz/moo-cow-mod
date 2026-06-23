package xyz.fftech.moocow.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class MoopoisonEffect extends MobEffect {
    private static final int TICK_INTERVAL = 20;

    public MoopoisonEffect() {
        super(MobEffectCategory.HARMFUL, 0x6B7D68);
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        entity.hurtServer(level, entity.damageSources().magic(), 1.0F + amplifier);
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int interval = Math.max(5, TICK_INTERVAL >> amplifier);
        return duration % interval == 0;
    }
}
