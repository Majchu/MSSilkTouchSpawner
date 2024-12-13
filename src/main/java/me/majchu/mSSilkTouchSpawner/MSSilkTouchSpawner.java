package me.majchu.mSSilkTouchSpawner;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class MSSilkTouchSpawner extends JavaPlugin implements Listener {

    private final Map<Block, String> spawnerCache = new HashMap<>();

    @Override
    public void onEnable() {
        registerListeners(new SpawnerListener());
    }

    @Override
    public void onDisable() {
        spawnerCache.clear();
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }

    private class SpawnerListener implements Listener {

        @EventHandler
        public void onBlockBreak(BlockBreakEvent event) {
            if (event.getBlock().getType() == Material.SPAWNER) {
                event.setDropItems(false);

                Player player = event.getPlayer();
                ItemStack tool = player.getInventory().getItemInMainHand();

                Optional.ofNullable(tool)
                        .filter(t -> t.containsEnchantment(Enchantment.SILK_TOUCH))
                        .ifPresent(t -> {
                            CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getState();
                            String mobType = spawner.getSpawnedType().toString();

                            spawnerCache.put(event.getBlock(), mobType);

                            ItemStack spawnerItem = new ItemStack(Material.SPAWNER);
                            BlockStateMeta meta = (BlockStateMeta) spawnerItem.getItemMeta();
                            CreatureSpawner metaSpawner = (CreatureSpawner) meta.getBlockState();
                            metaSpawner.setSpawnedType(spawner.getSpawnedType());
                            meta.setBlockState(metaSpawner);
                            spawnerItem.setItemMeta(meta);

                            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), spawnerItem);

                            event.setExpToDrop(0);
                        });
            }
        }

        @EventHandler
        public void onBlockPlace(BlockPlaceEvent event) {
            if (event.getBlock().getType() == Material.SPAWNER) {
                ItemStack placedItem = event.getItemInHand();
                if (placedItem.getItemMeta() instanceof BlockStateMeta) {
                    BlockStateMeta meta = (BlockStateMeta) placedItem.getItemMeta();
                    if (meta.getBlockState() instanceof CreatureSpawner) {
                        CreatureSpawner spawner = (CreatureSpawner) meta.getBlockState();
                        Block block = event.getBlock();
                        CreatureSpawner blockSpawner = (CreatureSpawner) block.getState();

                        String mobType = spawnerCache.getOrDefault(block, spawner.getSpawnedType().toString());
                        blockSpawner.setSpawnedType(org.bukkit.entity.EntityType.valueOf(mobType));
                        blockSpawner.update();
                    }
                }
            }
        }
    }
}
