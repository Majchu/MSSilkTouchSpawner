# SilkTouchSpawner Plugin

## Overview
SilkTouchSpawner is a lightweight Minecraft plugin designed to enhance gameplay by allowing players to mine and place mob spawners using a tool enchanted with Silk Touch. The plugin modifies the default spawner behavior to provide a more dynamic experience while maintaining balance.

## Features
- **Mine spawners with Silk Touch:** Use a tool enchanted with Silk Touch to drop the spawner as an item, preserving the mob type.
- **Place spawners:** Placed spawners retain their mob type, ensuring the same behavior as the original.
- **Experience handling:** No experience is dropped when mining spawners with Silk Touch, preserving balance.
- **Optimized performance:** The plugin utilizes caching and efficient event handling to minimize server load.

## Installation
1. Download the latest release of the plugin from the [Releases](https://github.com/Majchu/MSSilkTouchSpawner/releases/tag/1.0) section.
2. Place the `.jar` file in the `plugins` folder of your Minecraft server.
3. Restart or reload your server.

## Usage
1. Enchant a tool (e.g., pickaxe) with Silk Touch.
2. Use the enchanted tool to mine mob spawners.
3. The spawner will drop as an item, retaining its mob type.
4. Place the spawner back in the world to continue spawning the same type of mob.

## Compatibility
- Minecraft version: **1.21.3** (tested)
- Dependencies: None

## Permissions
- `silktouchspawner.use`: Allows players to mine and place spawners.

## Support
For issues, suggestions, or questions, please create an issue on the [GitHub repository](https://github.com/Majchu/MSSilkTouchSpawner/issues).

## License
This plugin is distributed under the MIT License. See the [LICENSE](LICENSE) file for details.
