# Moo-Cow Mod

A standalone Fabric client/server mod for Minecraft `26.2`.

Planned PyreHaven creature set:

- Reindeer
- Reindeer cheese
- Moo-cows
- Zom-bees

## Test release 0.1.0-test.10

This tenth test build gives the Reindeer a full Blockbench/API art pass after the prior model, texture, and movement read too rough in-game:

- adds a new editable Blockbench source model at `blockbench/reindeer_v3.bbmodel`
- rebuilds the runtime Java model with a fuller body, separate neck, clearer head/muzzle, visible eyes, stronger red nose, larger branching antlers, chest/side fluff, jointed legs, darker hooves, and a readable tail
- replaces the 128×128 runtime texture with cleaner fur dithering, cream muzzle/chest markings, brighter antlers, black eye detail, darker hoof islands, and stronger red-nose highlight
- updates the code-driven animation so the walk cycle uses smoother four-leg motion with knee follow-through, subtle body/neck/head bob, ear flicks, antler sway, and tail wag without pulling pieces apart

## Test release 0.1.0-test.7

This seventh test build adds the outbreak loop:

- Bucket of Randeer Cheese converts vanilla bees into hostile Zom-bees and returns an empty bucket
- Zom-bees fly, attack players and villagers, and apply Moopoison without dying after stinging
- Moopoison is a harmful damage-over-time effect that can kill
- Moopoisoned player/villager deaths spawn Moo-cows on land or Moo-cods in water
- Moo-cows and breaching Moo-cods sneeze out capped 1-3 Zom-bee swarms

Useful commands:

```mcfunction
/give @p moo-cow:randeer_cheese_bucket
/give @p moo-cow:zom_bee_spawn_egg
/give @p moo-cow:moo_cow_spawn_egg
/give @p moo-cow:moo_cod_spawn_egg
/effect give @p moo-cow:moopoison 7 0
```

## Test release 0.1.0-test.3

This third test build stabilizes the reindeer model and animation after the second build's in-game model pieces appeared separated and the walk cycle looked twitchy:

- switches the renderer model from vanilla `QuadrupedModel` to a custom `EntityModel` so only the reindeer's own parts and pivots drive animation
- removes the exaggerated body/head bob that could make the body, head, and legs look disconnected
- replaces the additive prance with a calmer four-leg walk cycle
- keeps subtle idle head breathing, ear flicks, antler sway, and tail wag without moving the main body pieces apart

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
