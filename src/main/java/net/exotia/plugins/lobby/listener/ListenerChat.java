package net.exotia.plugins.lobby.listener;

import eu.okaeri.injector.annotation.Inject;
import me.clip.placeholderapi.PlaceholderAPI;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.configuration.ConfigurationPlugin;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ListenerChat implements Listener {
    @Inject
    private ConfigurationPlugin configurationPlugin;
    @Inject
    private ConfigurationMessage configurationMessage;

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("exotia.lobby.chat")) {
            String color = configurationPlugin.getFormats().get(PlaceholderAPI.setPlaceholders(player, "%vault_group%")) != null ? configurationPlugin.getFormats().get(PlaceholderAPI.setPlaceholders(player, "%vault_group%")) : "";
            String formattedMessage = UtilMessage.getMessage(player, configurationMessage.getEventsChat().getSuccess(), player.getDisplayName(), color, event.getMessage());
            event.setFormat(formattedMessage);
            return;
        }
        event.setCancelled(true);
        UtilMessage.sendMessage(player, configurationMessage.getEventsChat().getFailed());
        UtilMessage.playSound(player, configurationMessage.getSounds().getError());
    }
}
