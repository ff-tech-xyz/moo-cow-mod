package xyz.fftech.moocow;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import xyz.fftech.moocow.entity.ReindeerEntity;

public class MooCowMod implements ModInitializer {
    public static final String MOD_ID = "moo-cow";

    public static final EntityType<ReindeerEntity> REINDEER = registerReindeer();
    public static final Item REINDEER_SPAWN_EGG = registerItem(
        "reindeer_spawn_egg",
        key -> new SpawnEggItem(new Item.Properties().setId(key).spawnEgg(REINDEER))
    );

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(REINDEER, ReindeerEntity.createAttributes());
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    private static EntityType<ReindeerEntity> registerReindeer() {
        Identifier id = id("reindeer");
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, id);
        EntityType<ReindeerEntity> type = EntityType.Builder
            .of(ReindeerEntity::new, MobCategory.CREATURE)
            .sized(1.1F, 1.45F)
            .eyeHeight(1.25F)
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
