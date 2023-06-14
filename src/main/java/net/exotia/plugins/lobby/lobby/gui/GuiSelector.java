package net.exotia.plugins.lobby.lobby.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.BaseGui;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.lobby.configuration.ConfigurationGui;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.configuration.section.SectionSelector;
import net.exotia.plugins.lobby.lobby.server.BungeeChannel;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class GuiSelector {
    @Inject
    private ConfigurationGui configurationGui;
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private BungeeChannel bungeeChannel;

    public void setupGui(BaseGui gui, List<Integer> emptySlots) {
        GuiItem filling = ItemBuilder.from(Material.AIR).asGuiItem();
        gui.setItem(emptySlots, filling);
        gui.disableAllInteractions();
        gui.setOpenGuiAction(event -> UtilMessage.playSound((Player) event.getPlayer(), configurationMessage.getSounds().getClick()));
    }

    public void open(Player player) {
        SectionSelector guiSelector = configurationGui.getGuiSelector();
        Gui gui = Gui.gui().rows(guiSelector.getSize()).title(UtilMessage.getComponent(player, guiSelector.getTitle())).create();
        setupGui(gui, guiSelector.getSlotsEmpty());
        guiSelector.getButtons().forEach((integer, guiButton) -> gui.setItem(guiButton.getSlots(), ItemBuilder.from(guiButton.getItem()).asGuiItem(event -> guiButton.getAction().runAction(player, configurationMessage, bungeeChannel))));
        gui.open(player);
    }
}
