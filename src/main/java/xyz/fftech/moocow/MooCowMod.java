package xyz.fftech.moocow;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;
import xyz.fftech.moocow.entity.ReindeerEntity;

public class MooCowMod implements ModInitializer {
    public static final String MOD_ID = "moo-cow";

    public static final EntityType<ReindeerEntity> REINDEER = registerReindeer();
    public static final Item REINDEER_SPAWN_EGG = registerItem(
        "reindeer_spawn_egg",
        key -> new SpawnEggItem(new Item.Properties().setId(key).spawnEgg(REINDEER))
    );
    public static final Item RANDEER_MILK_BUCKET = registerItem(
        "randeer_milk_bucket",
        key -> new Item(
            new Item.Properties()
                .setId(key)
                .craftRemainder(Items.BUCKET)
                .component(DataComponents.CONSUMABLE, Consumables.MILK_BUCKET)
                .usingConvertsTo(Items.BUCKET)
                .stacksTo(1)
        )
    );

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(REINDEER, ReindeerEntity.createAttributes());
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

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(output -> output.accept(RANDEER_MILK_BUCKET));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register(output -> output.accept(REINDEER_SPAWN_EGG));
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    private static EntityType<ReindeerEntity> registerReindeer() {
        Identifier id = id("reindeer");
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, id);
        EntityType<ReindeerEntity> type = EntityType.Builder
            .of(ReindeerEntity::new, MobCategory.CREATURE)
            .sized(1.1F, 1.75F)
            .eyeHeight(1.35F)
            .clientTrackingRange(10)
            .build(key);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type);
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
