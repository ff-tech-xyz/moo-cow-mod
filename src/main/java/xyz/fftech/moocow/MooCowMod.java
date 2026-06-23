package xyz.fftech.moocow;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;
import xyz.fftech.moocow.effect.MoopoisonEffect;
import xyz.fftech.moocow.entity.MooCodEntity;
import xyz.fftech.moocow.entity.MooCowEntity;
import xyz.fftech.moocow.entity.ReindeerEntity;
import xyz.fftech.moocow.entity.ZomBeeEntity;
import xyz.fftech.moocow.item.RandeerCheeseBucketItem;
import xyz.fftech.moocow.item.RandeerMilkBucketItem;

public class MooCowMod implements ModInitializer {
    public static final String MOD_ID = "moo-cow";
    private static final FoodProperties RANDEER_CHEESE_FOOD = new FoodProperties.Builder()
        .nutrition(4)
        .saturationModifier(0.75F)
        .build();

    public static final Holder.Reference<MobEffect> MOOPOISON = registerEffect("moopoison", new MoopoisonEffect());

    public static final EntityType<ReindeerEntity> REINDEER = registerEntity(
        "reindeer",
        EntityType.Builder.of(ReindeerEntity::new, MobCategory.CREATURE)
            .sized(1.1F, 1.75F)
            .eyeHeight(1.35F)
            .clientTrackingRange(10)
    );
    public static final EntityType<ZomBeeEntity> ZOM_BEE = registerEntity(
        "zom_bee",
        EntityType.Builder.of(ZomBeeEntity::new, MobCategory.MONSTER)
            .sized(0.7F, 0.6F)
            .eyeHeight(0.3F)
            .clientTrackingRange(8)
    );
    public static final EntityType<MooCowEntity> MOO_COW = registerEntity(
        "moo_cow",
        EntityType.Builder.of(MooCowEntity::new, MobCategory.MONSTER)
            .sized(0.9F, 1.4F)
            .eyeHeight(1.3F)
            .clientTrackingRange(8)
    );
    public static final EntityType<MooCodEntity> MOO_COD = registerEntity(
        "moo_cod",
        EntityType.Builder.of(MooCodEntity::new, MobCategory.MONSTER)
            .sized(0.5F, 0.3F)
            .eyeHeight(0.2F)
            .clientTrackingRange(8)
    );

    public static final Item REINDEER_SPAWN_EGG = registerItem(
        "reindeer_spawn_egg",
        key -> new SpawnEggItem(new Item.Properties().setId(key).spawnEgg(REINDEER))
    );
    public static final Item ZOM_BEE_SPAWN_EGG = registerItem(
        "zom_bee_spawn_egg",
        key -> new SpawnEggItem(new Item.Properties().setId(key).spawnEgg(ZOM_BEE))
    );
    public static final Item MOO_COW_SPAWN_EGG = registerItem(
        "moo_cow_spawn_egg",
        key -> new SpawnEggItem(new Item.Properties().setId(key).spawnEgg(MOO_COW))
    );
    public static final Item MOO_COD_SPAWN_EGG = registerItem(
        "moo_cod_spawn_egg",
        key -> new SpawnEggItem(new Item.Properties().setId(key).spawnEgg(MOO_COD))
    );
    public static final Item RANDEER_MILK_BUCKET = registerItem(
        "randeer_milk_bucket",
        key -> new RandeerMilkBucketItem(
            new Item.Properties()
                .setId(key)
                .craftRemainder(Items.BUCKET)
                .component(DataComponents.CONSUMABLE, Consumables.MILK_BUCKET)
                .usingConvertsTo(Items.BUCKET)
                .stacksTo(1)
        )
    );
    public static final Item RANDEER_CHEESE_BUCKET = registerItem(
        "randeer_cheese_bucket",
        key -> new RandeerCheeseBucketItem(
            new Item.Properties()
                .setId(key)
                .food(RANDEER_CHEESE_FOOD)
                .craftRemainder(Items.BUCKET)
                .usingConvertsTo(Items.BUCKET)
                .stacksTo(1)
        )
    );

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(REINDEER, ReindeerEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ZOM_BEE, ZomBeeEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(MOO_COW, MooCowEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(MOO_COD, MooCodEntity.createAttributes());
        SpawnPlacements.register(REINDEER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Animal::checkAnimalSpawnRules);

        BiomeModifications.addSpawn(
            BiomeSelectors.includeByKey(
                Biomes.TAIGA,
                Biomes.SNOWY_TAIGA,
                Biomes.OLD_GROWTH_PINE_TAIGA,
                Biomes.OLD_GROWTH_SPRUCE_TAIGA,
                Biomes.SNOWY_PLAINS,
                Biomes.ICE_SPIKES,
                Biomes.GROVE,
                Biomes.SNOWY_SLOPES
            ),
            MobCategory.CREATURE,
            REINDEER,
            8,
            2,
            4
        );

        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            if (!entity.hasEffect(MOOPOISON) || !(entity instanceof Player || entity instanceof Villager) || !(entity.level() instanceof ServerLevel level)) {
                return;
            }

            if (entity.isInWater()) {
                MooCodEntity mooCod = MOO_COD.create(level, EntitySpawnReason.MOB_SUMMONED);
                if (mooCod != null) {
                    mooCod.setPos(entity.getX(), entity.getY(), entity.getZ());
                    mooCod.setYRot(entity.getYRot());
                    mooCod.setXRot(entity.getXRot());
                    level.addFreshEntity(mooCod);
                }
            } else {
                MooCowEntity mooCow = MOO_COW.create(level, EntitySpawnReason.MOB_SUMMONED);
                if (mooCow != null) {
                    mooCow.setPos(entity.getX(), entity.getY(), entity.getZ());
                    mooCow.setYRot(entity.getYRot());
                    mooCow.setXRot(entity.getXRot());
                    level.addFreshEntity(mooCow);
                }
            }
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(output -> {
            output.accept(RANDEER_MILK_BUCKET);
            output.accept(RANDEER_CHEESE_BUCKET);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register(output -> {
            output.accept(REINDEER_SPAWN_EGG);
            output.accept(ZOM_BEE_SPAWN_EGG);
            output.accept(MOO_COW_SPAWN_EGG);
            output.accept(MOO_COD_SPAWN_EGG);
        });
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    private static <T extends net.minecraft.world.entity.Entity> EntityType<T> registerEntity(String path, EntityType.Builder<T> builder) {
        Identifier id = id(path);
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, id);
        EntityType<T> type = builder.build(key);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type);
    }

    private static Holder.Reference<MobEffect> registerEffect(String path, MobEffect effect) {
        Identifier id = id(path);
        ResourceKey<MobEffect> key = ResourceKey.create(Registries.MOB_EFFECT, id);
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, key, effect);
    }

    private static Item registerItem(String path, ItemFactory factory) {
        Identifier id = id(path);
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);
        return Registry.register(BuiltInRegistries.ITEM, key, factory.create(key));
    }

    @FunctionalInterface
    private interface ItemFactory {
        Item create(ResourceKey<Item> key);
    }
}
