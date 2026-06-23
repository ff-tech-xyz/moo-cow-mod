# Moo-Cow Mod

A standalone Fabric client/server mod for Minecraft `26.2`.

Planned PyreHaven creature set:

- Reindeer
- Reindeer cheese
- Moo-cows
- Zom-bees

## Test release 0.1.0-test.1

This first test build adds a summonable reindeer entity with:

- server-side entity registration and basic animal AI
- a client-side renderer
- a custom blocky model with antlers
- a generated vanilla-style texture
- code-driven walking/head animation
- a reindeer spawn egg item

## Testing

Install Fabric Loader `0.19.3` for Minecraft `26.2`, Fabric API `0.153.0+26.2`, and this mod jar.

Useful commands:

```mcfunction
/summon moo-cow:reindeer ~ ~ ~
/give @p moo-cow:reindeer_spawn_egg
```

## Development notes

This repo uses the same verified Minecraft `26.2` official-name Loom setup as the other PyreHaven standalone Fabric 26.x mods. Entity texture work lives under `src/main/resources/assets/moo-cow/textures/entity/`. Reindeer animation is currently Java model animation in `ReindeerModel#setupAnim`; no external animation runtime is required for this first test build.
