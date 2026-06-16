# 🧱 Simplefun
**Small Tweaks, Big Fun – And Fully Configurable!**

Simplefun is a small collection of "Vanilla+" fun features that polish your Minecraft experience without changing the core game. Throw bricks, yeet your items, turn into a pig — and toggle every single feature on or off.

![Banner](https://cdn.modrinth.com/data/cached_images/ae23faac2513023ad0907feae66e1826e68f1caa.png)


## ✨ Feature Highlights

### 😂 Fun & Interaction
* **Yeet:** Crouch + Drop = Throw! Yeet items into the world with force (Sneak + Q). Adjustable strength.
* **Throwable Bricks:** Throw Bricks, Nether Bricks and Resin Bricks to smash glass or deal damage.
* **Brick Snowball:** Craftable from a Brick and 4 Snowballs. Flies like a snowball, but hits much harder — with a mix of snow and brick impact particles.
* **Piggy Transformation:** Eat (cooked) porkchop and turn into a pig for 5 minutes — a pig head is rendered on your character. **Visible to everyone in multiplayer.**

### ⚔️ Combat & PvP
* **No-Damage:** Hitting with a **feather** deals 0 damage (knockback still applies) — great for non-lethal knockback fights. Also available as a custom **No-Damage enchantment** sold by librarians.
* **Higher Knockback:** The vanilla **Knockback** enchantment now goes up to **level 5**, and can be applied to feathers and sticks.
* **Player Head Drops:** Defeated players drop their head upon death in PvP (uses their current skin).

## ⚙️ Configuration
Everything is adjustable! Open the in-game config (via **Mod Menu**) to toggle every single feature, or edit `config/simplefun.json`. Admins can also change values at runtime with `/simplefun …` (OP level 4).

## 📦 Requirements
* **Fabric Loader** & **Fabric API**
* **Cloth Config** (required)
* **Mod Menu** (optional, recommended — adds the in-game config button)

## 🆕 What's new in 1.1.0
* 🐷 **Piggy Transformation now works in multiplayer** — the pig head is synced to all players (previously only visible on your own client).
* 🪶 **No-Damage is now toggleable** in the config.
* 🥊 **Higher Knockback levels** (up to V).
* 🔧 Fixed a bug where the **Knockback enchantment did nothing** (the data override accidentally removed the vanilla effect).
* 🧰 Cloth Config is now declared as a proper dependency, the admin command now correctly requires OP level 4, command messages and config options are fully translated (EN/DE), and a lot of internal cleanup.
