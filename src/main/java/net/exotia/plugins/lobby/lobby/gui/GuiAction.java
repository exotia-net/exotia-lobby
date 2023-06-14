package net.exotia.plugins.lobby.lobby.gui;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.injector.annotation.Inject;
import lombok.Builder;
import lombok.Getter;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.lobby.server.BungeeChannel;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.entity.Player;

@Builder
@Getter
public class GuiAction extends OkaeriConfig {
    private String name;
    private String value;

    public void runAction(Player player, ConfigurationMessage configurationMessage, BungeeChannel bungeeChannel) {
        ActionType.valueOf(name.toUpperCase()).runAction(player, value, configurationMessage, bungeeChannel);
    }

    public enum ActionType {
        SERVER {
            public void runAction(Player player, String value, ConfigurationMessage configurationMessage, BungeeChannel bungeeChannel) {
                bungeeChannel.sendPlayer(player, value);
            }
        },
        TEXT {
            public void runAction(Player player, String value, ConfigurationMessage configurationMessage, BungeeChannel bungeeChannel) {
                UtilMessage.sendMessage(player, configurationMessage.getEventsLink(), value);
                UtilMessage.playSound(player, configurationMessage.getSounds().getClick());
                player.closeInventory();
            }
        };

        public abstract void runAction(Player player, String value, ConfigurationMessage configurationMessage, BungeeChannel bungeeChannel);
    }
}