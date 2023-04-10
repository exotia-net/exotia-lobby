package net.exotia.plugins.lobby.gui.inventory;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.PaginatedGui;
import net.exotia.plugins.lobby.configuration.ConfigurationGui;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.gui.GuiButton;
import net.exotia.plugins.lobby.gui.GuiInventory;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GuiPaginated {
    public static void openPaginated(String name, Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage) {
        PaginatedGui gui = Gui.paginated().title(UtilMessage.getComponent("<white>" + configurationGui.getGuis().get(name).getTitle())).rows(configurationGui.getGuis().get(name).getSize()).create();
        HashMap<Integer, GuiButton> buttons = configurationGui.getGuis().get(name).getButtons();
        GuiButton next = configurationGui.getGuis().get(name).getButtonNext();
        GuiButton previous = configurationGui.getGuis().get(name).getButtonPrevious();
        GuiInventory.setupGui(name, gui, configurationGui, configurationMessage);
        gui.setPageSize(configurationGui.getGuis().get(name).getItemsPerPage());
        gui.setItem(next.getSlots().get(0), ItemBuilder.from(next.getItem()).asGuiItem(event -> {
            gui.next();
            UtilMessage.playSound(player, configurationMessage.getSounds().getClick());
        }));
        gui.setItem(previous.getSlots().get(0), ItemBuilder.from(previous.getItem()).asGuiItem(event -> {
            gui.previous();
            UtilMessage.playSound(player, configurationMessage.getSounds().getClick());
        }));
        for (int key : buttons.keySet()) {
            GuiButton button = buttons.get(key);
            gui.addItem(ItemBuilder.from(button.getItem()).asGuiItem(event -> button.getAction().runAction((Player) event.getWhoClicked(), configurationGui, configurationMessage)));
        }
        gui.open(player);
    }
}
