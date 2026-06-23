# Moo-Cow Mod

A standalone Fabric client/server mod for Minecraft `26.2`.

Planned PyreHaven creature set:

- Reindeer
- Reindeer cheese
- Moo-cows
- Zom-bees

## Test release 0.1.0-test.2

This second test build refreshes the summonable reindeer with:

- a Blockbench-authored source model under `blockbench/reindeer_v2.bbmodel`
- refined blocky geometry with fuller body mass, shoulder/chest fluff, a forehead tuft, red nose, expanded antlers, and darker hooves
- a remade 128×128 texture with warmer fur shading, tan muzzle/cheek detail, lighter antlers, and stronger hoof contrast
- improved Java model animation with subtle idle bob, antler sway, ear flicks, tail wag, body bob, and a slightly livelier walking prance
- the existing server-side entity registration, basic animal AI, client renderer, and reindeer spawn egg item

## Previous test release 0.1.0-test.1

The first test build added a summonable reindeer entity with basic AI, renderer, model, generated texture, code-driven animation, and spawn egg.

## Testing

Install Fabric Loader `0.19.3` for Minecraft `26.2`, Fabric API `0.153.0+26.2`, and this mod jar.

Useful commands:

```mcfunction
/summon moo-cow:reindeer ~ ~ ~
/give @p moo-cow:reindeer_spawn_egg
```

## Development notes

This repo uses the same verified Minecraft `26.2` official-name Loom setup as the other PyreHaven standalone Fabric 26.x mods. Entity texture work lives under `src/main/resources/assets/moo-cow/textures/entity/`. Reindeer animation is currently Java model animation in `ReindeerModel#setupAnim`; no external animation runtime is required for this first test build.
