package net.exotia.plugins.lobby.listener;

import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.lobby.LobbyPlugin;
import net.exotia.plugins.lobby.configuration.ConfigurationGui;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.configuration.ConfigurationPlugin;
import net.exotia.plugins.lobby.gui.GuiButton;
import net.exotia.plugins.lobby.utils.UtilMessage;
import net.exotia.plugins.lobby.utils.UtilVanish;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ListenerJoinQuit implements Listener {
    @Inject
    private LobbyPlugin lobbyPlugin;
    @Inject
    private ConfigurationPlugin configurationPlugin;
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ConfigurationGui configurationGui;

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(UtilMessage.getMessage(player, configurationMessage.getEventsConnect().getJoin(), player.getDisplayName()));
        player.teleport(configurationPlugin.getLocation());
        if (!player.hasPermission("exotia.lobby.command.server")) return;
        setupHotbar(player.getInventory(), configurationGui);
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(UtilMessage.getMessage(player, configurationMessage.getEventsConnect().getQuit(), player.getDisplayName()));
        UtilVanish.removePlayer(player);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("exotia.lobby.command.server")) return;
        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
            return;
        int slot = player.getInventory().getHeldItemSlot();
        HashMap<Integer, GuiButton> buttons = configurationGui.getHotbarButtons();
        GuiButton button = buttons.get(slot);
        if (button == null) return;
        button.getAction().runAction(player, configurationGui, configurationMessage);
    }

    public static void setupHotbar(PlayerInventory inventory, ConfigurationGui configurationGui) {
        HashMap<Integer, GuiButton> buttons = configurationGui.getHotbarButtons();
        inventory.clear();
        for (int slot : buttons.keySet())
            inventory.setItem(slot, buttons.get(slot).getItem());
        inventory.setHeldItemSlot(4);
    }
}
