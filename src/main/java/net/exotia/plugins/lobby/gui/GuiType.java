package net.exotia.plugins.lobby.gui;

import net.exotia.plugins.lobby.configuration.ConfigurationGui;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.gui.inventory.GuiPaginated;
import net.exotia.plugins.lobby.gui.inventory.GuiStatic;
import org.bukkit.entity.Player;

public enum GuiType {
    SERVER {
        public void open(Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage) {
            GuiStatic.openStatic(this.name().toLowerCase(), player, configurationGui, configurationMessage);
        }
    },
    COSMETICS {
        public void open(Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage) {
            GuiPaginated.openPaginated(this.name().toLowerCase(), player, configurationGui, configurationMessage);
        }
    };

    public abstract void open(Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage);
}