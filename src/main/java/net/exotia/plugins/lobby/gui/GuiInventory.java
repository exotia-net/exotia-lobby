package net.exotia.plugins.lobby.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.BaseGui;
import dev.triumphteam.gui.guis.GuiItem;
import net.exotia.plugins.lobby.configuration.ConfigurationGui;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GuiInventory {
    public static void setupGui(String name, BaseGui gui, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage) {
        GuiItem filling = ItemBuilder.from(Material.AIR).asGuiItem();
        gui.setItem(configurationGui.getGuis().get(name).getSlotsEmpty(), filling);
        gui.disableAllInteractions();
        gui.setOpenGuiAction(event -> UtilMessage.playSound((Player) event.getPlayer(), configurationMessage.getSounds().getClick()));
    }
}
