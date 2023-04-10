package net.exotia.plugins.lobby.gui.inventory;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import net.exotia.plugins.lobby.configuration.ConfigurationGui;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.gui.GuiButton;
import net.exotia.plugins.lobby.gui.GuiInventory;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GuiStatic {
    public static void openStatic(String name, Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage) {
        Gui gui = Gui.gui().title(UtilMessage.getComponent("<white>" + configurationGui.getGuis().get(name).getTitle())).rows(configurationGui.getGuis().get(name).getSize()).create();
        HashMap<Integer, GuiButton> buttons = configurationGui.getGuis().get(name).getButtons();
        GuiInventory.setupGui(name, gui, configurationGui, configurationMessage);
        for (int key : buttons.keySet()) {
            GuiButton button = buttons.get(key);
            for (int slot : button.getSlots())
                gui.setItem(slot, ItemBuilder.from(button.getItem()).asGuiItem(event -> button.getAction().runAction((Player) event.getWhoClicked(), configurationGui, configurationMessage)));
        }
        gui.open(player);
    }
}
