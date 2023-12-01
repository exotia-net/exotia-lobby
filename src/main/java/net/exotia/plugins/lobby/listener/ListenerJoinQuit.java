package net.exotia.plugins.lobby.listener;

import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.lobby.configuration.ConfigurationGui;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.configuration.ConfigurationPlugin;
import net.exotia.plugins.lobby.lobby.gui.GuiButton;
import net.exotia.plugins.lobby.lobby.gui.GuiSelector;
import net.exotia.plugins.lobby.lobby.player.VanishPlayers;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;

public class ListenerJoinQuit implements Listener {
    @Inject
    private VanishPlayers vanishPlayers;
    @Inject
    private ConfigurationPlugin configurationPlugin;
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ConfigurationGui configurationGui;
    @Inject
    private GuiSelector guiSelector;

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(UtilMessage.getMessage(player, configurationMessage.getEventsConnect().getJoin(), player.getDisplayName()));

        player.setBedSpawnLocation(configurationPlugin.getLocation());
        player.teleport(configurationPlugin.getLocation());

        if (!player.hasPermission("exotia.lobby.command.server")) return;

//        setupHotbar(player.getInventory());
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.setQuitMessage(UtilMessage.getMessage(player, configurationMessage.getEventsConnect().getQuit(), player.getDisplayName()));

        vanishPlayers.removePlayer(player);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("exotia.lobby.command.server")) return;
        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
            return;

        HashMap<Integer, GuiButton> buttons = configurationGui.getGuiButtons();

        int slot = player.getInventory().getHeldItemSlot();

        if (buttons.get(slot) == null) return;

        guiSelector.open(player);
    }

    public void setupHotbar(PlayerInventory inventory) {
        HashMap<Integer, GuiButton> buttons = configurationGui.getGuiButtons();

        inventory.clear();

        for (int slot : buttons.keySet()) {
            inventory.setItem(slot, buttons.get(slot).getItem());
        }

        inventory.setHeldItemSlot(4);
    }
}
