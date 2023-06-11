package net.exotia.plugins.lobby.gui;

import eu.okaeri.configs.OkaeriConfig;
import io.th0rgal.oraxen.api.OraxenItems;
import lombok.Builder;
import lombok.Getter;
import net.exotia.plugins.lobby.configuration.ConfigurationGui;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.utils.UtilChannel;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

@Builder
@Getter
public class GuiAction extends OkaeriConfig {
    private String name;
    private String value;

    public void runAction(Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage) {
        ActionType.valueOf(name.toUpperCase()).runAction(player, configurationGui, configurationMessage, value);
    }

    public enum ActionType {
        SERVER {
            public void runAction(Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage, String value) {
                if (UtilChannel.sendPlayer(player, value)) {
                    UtilMessage.sendMessage(player, configurationMessage.getCommandsServer().getSuccess(), value);
                    UtilMessage.playSound(player, configurationMessage.getSounds().getClick());
                } else {
                    UtilMessage.sendMessage(player, configurationMessage.getCommandsServer().getError(), value);
                    UtilMessage.playSound(player, configurationMessage.getSounds().getError());
                }
                player.closeInventory();
            }
        },
        TEXT {
            public void runAction(Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage, String value) {
                UtilMessage.sendMessage(player, configurationMessage.getEventsLink(), value);
                UtilMessage.playSound(player, configurationMessage.getSounds().getClick());
                player.closeInventory();
            }
        },
        OPEN {
            public void runAction(Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage, String value) {
                GuiType.valueOf(value.toUpperCase()).open(player, configurationGui, configurationMessage);
            }
        };
//        SET_ITEM {
//            public void runAction(Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage, String value) {
//                ItemStack item = OraxenItems.getItemById(value).build();
//                if (item != null) player.getInventory().setItem(EquipmentSlot.HEAD, item);
//                UtilMessage.playSound(player, configurationMessage.getSounds().getClick());
//            }
//        };

        public abstract void runAction(Player player, ConfigurationGui configurationGui, ConfigurationMessage configurationMessage, String value);
    }
}