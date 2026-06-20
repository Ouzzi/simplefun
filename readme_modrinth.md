# 🧱 Simplefun
**Small Tweaks, Big Fun – And Fully Configurable!**

Simplefun is a small collection of "Vanilla+" fun features that polish your Minecraft experience without changing the core game. Throw bricks, yeet your items, turn into a pig — and toggle every single feature on or off.

![Banner](https://cdn.modrinth.com/data/cached_images/ae23faac2513023ad0907feae66e1826e68f1caa.png)


## ✨ Feature Highlights

### 😂 Fun & Interaction
* **Yeet:** Crouch + Drop = Throw! Yeet items into the world with force (Sneak + Q). Adjustable strength.
* **Throwable Bricks:** Throw Bricks, Nether Bricks and Resin Bricks to smash glass or deal damage.
* **Brick Snowball:** Craftable from a Brick and 4 Snowballs. Flies like a snowball, but hits much harder — with a mix of snow and brick impact particles.
* **Piggy Transformation:** Eat (cooked) porkchop and turn into a pig for 5 minutes — a pig head is rendered on your character. Other players see it too in multiplayer.

### ⚔️ Combat & PvP
* **No-Damage:** Hitting with a **feather** deals 0 damage (knockback still applies) — great for non-lethal knockback fights. Also available as a custom **No-Damage enchantment** sold by librarians.
* **Higher Knockback:** The vanilla **Knockback** enchantment now goes up to **level 5**, and can be applied to feathers and sticks.
* **Player Head Drops:** Defeated players drop their head upon death in PvP (uses their current skin).

## ⚙️ Configuration
Everything is adjustable! Open the in-game config screen (on **Fabric** via **Mod Menu**, on **NeoForge** via the mod list's config button), edit `config/simplefun.json`, or change values at runtime with `/simplefun …` (OP level 4).

## 📦 Loaders & Requirements
Runs on **Fabric** and **NeoForge** (Minecraft 1.21.11).

* **Cloth Config** – required on both loaders.
* **Fabric:** Fabric Loader + Fabric API, and **Mod Menu** (optional) for the in-game config button.
* **NeoForge:** NeoForge 21.11+ (the config button is in NeoForge's built-in mod list).

## 🆕 Changelog

### 1.2.0
* 🧩 **Now runs on NeoForge** as well as Fabric (Minecraft 1.21.11).
* 🐷 Piggy Transformation multiplayer sync works on **both** loaders (synced entity data on Fabric, a synced data attachment on NeoForge).
* 🧱 Brick Snowball moved to the **Ingredients** creative tab.

### 1.1.0
* 🐷 **Piggy Transformation** in multiplayer — the pig head is synced to other players.
* 🪶 **No-Damage is now toggleable** in the config.
* 🥊 **Higher Knockback levels** (up to V).
* 🔧 Fixed a bug where the **Knockback enchantment did nothing** (the data override accidentally removed the vanilla effect).
* 🧰 Cloth Config is now a proper dependency, the admin command correctly requires OP level 4, command messages and config options are fully translated (EN/DE), plus a lot of internal cleanup.
