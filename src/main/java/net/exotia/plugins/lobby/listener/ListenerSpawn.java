package net.exotia.plugins.lobby.listener;

import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.lobby.configuration.ConfigurationPlugin;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.world.WorldInitEvent;

public class ListenerSpawn implements Listener {
    @Inject
    private ConfigurationPlugin configurationPlugin;

    public void setupGameRules(World world) {
        if (world == null) return;
        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        world.setGameRule(GameRule.BLOCK_EXPLOSION_DROP_DECAY, true);
        world.setGameRule(GameRule.COMMAND_BLOCK_OUTPUT, false);
        world.setGameRule(GameRule.DISABLE_RAIDS, true);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_FIRE_TICK, false);
        world.setGameRule(GameRule.DO_INSOMNIA, false);
        world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world.setGameRule(GameRule.DO_MOB_LOOT, false);
        world.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
        world.setGameRule(GameRule.DO_TILE_DROPS, false);
        world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
        world.setGameRule(GameRule.DO_WARDEN_SPAWNING, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setGameRule(GameRule.DO_VINES_SPREAD, false);
        world.setGameRule(GameRule.DROWNING_DAMAGE, false);
        world.setGameRule(GameRule.FALL_DAMAGE, false);
        world.setGameRule(GameRule.FIRE_DAMAGE, false);
        world.setGameRule(GameRule.MOB_EXPLOSION_DROP_DECAY, true);
        world.setGameRule(GameRule.MOB_GRIEFING, false);
        world.setGameRule(GameRule.NATURAL_REGENERATION, false);
        world.setGameRule(GameRule.PLAYERS_SLEEPING_PERCENTAGE, 100);
        world.setGameRule(GameRule.REDUCED_DEBUG_INFO, true);
        world.setGameRule(GameRule.RANDOM_TICK_SPEED, 0);
        world.setGameRule(GameRule.SEND_COMMAND_FEEDBACK, false);
        world.setGameRule(GameRule.SPAWN_RADIUS, 0);
        world.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false);
        world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
        world.setGameRule(GameRule.UNIVERSAL_ANGER, false);
        world.setGameRule(GameRule.LOG_ADMIN_COMMANDS, false);
        world.setGameRule(GameRule.KEEP_INVENTORY, true);
        world.setGameRule(GameRule.MAX_ENTITY_CRAMMING, 99999);
    }

    @EventHandler
    public void onWorldLoad(WorldInitEvent event) {
        setupGameRules(event.getWorld());
    }

    @EventHandler
    public void onPlayerBlockDestroyEvent(HangingBreakByEntityEvent event) {
        if (!(event.getRemover() instanceof Player player)) {
            event.setCancelled(true);
            return;
        }
        if (!player.hasPermission("exotia.lobby.bypass")) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerBlockDestroyEvent(HangingPlaceEvent event) {
        Player player = event.getPlayer();
        if (player == null) return;
        if (player.hasPermission("exotia.lobby.bypass")) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamageOthersEvent(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamagedEvent(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDrainHunger(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamagedByBlockEvent(EntityDamageByBlockEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().hasMetadata("NPC")) return;
        if (!event.getPlayer().hasPermission("exotia.lobby.bypass")) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerBlockPlace(BlockPlaceEvent event) {
        if (!event.getPlayer().hasPermission("exotia.lobby.bypass")) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerBlockBreak(BlockBreakEvent event) {
        if (!event.getPlayer().hasPermission("exotia.lobby.bypass")) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerWaterFill(PlayerBucketFillEvent event) {
        if (!event.getPlayer().hasPermission("exotia.lobby.bypass")) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerWaterEmpty(PlayerBucketEmptyEvent event) {
        if (!event.getPlayer().hasPermission("exotia.lobby.bypass")) event.setCancelled(true);
    }

//    @EventHandler
//    public void onOraxenBlocksBreak(OraxenFurnitureBreakEvent event) {
//        if (!event.getPlayer().hasPermission("exotia.lobby.bypass")) event.setCancelled(true);
//    }
//
//    @EventHandler
//    public void onOraxenBlocksPlace(OraxenFurniturePlaceEvent event) {
//        if (!event.getPlayer().hasPermission("exotia.lobby.bypass")) event.setCancelled(true);
//    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (!event.getPlayer().hasPermission("exotia.lobby.bypass")) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItem(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!player.hasPermission("exotia.lobby.bypass")) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerSwapItems(PlayerSwapHandItemsEvent event) {
        if (!event.getPlayer().hasPermission("exotia.lobby.bypass")) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerHotbarSwap(InventoryClickEvent event) {
        if (!event.getWhoClicked().hasPermission("exotia.lobby.bypass")) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("exotia.lobby.bypass")) return;
        if (player.getLocation().getY() > configurationPlugin.getLimit()) return;
        player.teleport(configurationPlugin.getLocation());
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer().getBedSpawnLocation() == null)
            event.setRespawnLocation(configurationPlugin.getLocation());
    }

    @EventHandler
    public void onPlayerDeath(PlayerRespawnEvent event) {
        event.setRespawnLocation(configurationPlugin.getLocation());
    }
}