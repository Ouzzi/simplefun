# Simplefun - Documentation & Wiki

**Simplefun** is a modular Fabric mod for Minecraft 1.21+ that adds various gameplay tweaks, visual improvements, and administration tools.

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

**Recipe: Brick Snowball**
´´´
  S
S B S
  S
*(S = Snowball, B = Brick)*
´´´

### ⚔️ PvP 
* **Player Head Drops:** Players drop their head when killed in PvP (uses current skin).

### 💻 Commands
The following commands are available for Admins:

* `/simplefun config ...`: Allows changing config values at runtime (Level 4 OP required).

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