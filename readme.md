# Simplefun - Documentation & Wiki

**Simplefun** is a fun, fully configurable Fabric mod for Minecraft **1.21.11** that adds a handful of "Vanilla+" gameplay tweaks — throwing, transformations, and combat tweaks — all toggleable.

## 📥 Installation & Dependencies

To use Simplefun, the following mods must be installed:
1.  **Fabric Loader**
2.  **Fabric API**
3.  **Cloth Config API** (for the configuration menu)
4.  **Mod Menu** (optional, but recommended for in-game configuration)

## 📖 Features in Detail

All features can be configured via the `cloth-config` GUI or the file `config/simplefun.json`.

### 🎮 Fun & Gameplay
| Feature | Description | Config Key |
| :--- | :--- | :--- |
| **Yeet (Throw)** | Allows throwing items far by sneaking (`Shift` + `Q`). Strength is adjustable. | `fun.enableYeet`, `fun.yeetStrength` |
| **Throwable Bricks** | Bricks, Nether Bricks, and Resin Bricks can be thrown. Can optionally break glass. | `fun.enableThrowableBricks`, `fun.throwableBricksBreakBlocks` |
| **Brick Snowball** | A new item that flies like a snowball but deals damage. Particles are a mix of snow and brick fragments. | `fun.brickSnowballDamage` |
| **Piggy Transformation** | Eating (cooked) porkchop turns the player into a pig for 5 minutes — a pig head is rendered on top. Visible to all players in multiplayer. | `fun.enablePiggyEffect` |

**Recipe: Brick Snowball**
```
  S
S B S
  S
```
*(S = Snowball, B = Brick)*

### ⚔️ PvP 
* **Player Head Drops:** Players drop their head when killed in PvP (uses current skin).

### 🪄 Enchantments & Combat
| Feature | Description | Config Key |
| :--- | :--- | :--- |
| **No-Damage** | Hitting with a **feather** deals 0 damage (knockback still applies). Also available as a custom **No-Damage enchantment** (sold by librarians) that can be applied to feathers, sticks and weapons. | `fun.enableNoDamage` |
| **Higher Knockback** | The vanilla **Knockback** enchantment now goes up to **level 5** (instead of 2) and can be applied to feathers and sticks too. | *(data-driven)* |

### 💻 Commands
The following commands change config values at runtime (Level 4 OP required):

* `/simplefun pvp headDrops <true|false>` – toggle player head drops.
* `/simplefun tweaks yeet toggle <true|false>` – enable/disable yeet.
* `/simplefun tweaks yeet strength <value>` – set the yeet velocity multiplier.
* `/simplefun tweaks bricks enable <true|false>` – enable/disable throwable bricks.
* `/simplefun tweaks bricks breakGlass <true|false>` – allow thrown bricks to shatter glass.
* `/simplefun tweaks bricks damage <value>` – set thrown-brick damage.
* `/simplefun tweaks bricks snowballDamage <value>` – set Brick Snowball damage.

## 🏗️ Building from Source

To compile the mod yourself:

1.  Clone the repository.
2.  Open a terminal in the folder.
3.  Run:
    * Windows: `gradlew build`
    * Linux/Mac: `./gradlew build`
4.  The file will be located in `build/libs/`.

## ⚖️ License
This project is released under the **CC0 1.0 Universal** license. You can copy, modify, and use the code however you like.