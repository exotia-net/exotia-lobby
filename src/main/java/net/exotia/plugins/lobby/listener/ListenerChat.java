package net.exotia.plugins.lobby.listener;

import eu.okaeri.injector.annotation.Inject;
import io.papermc.paper.event.player.AsyncChatEvent;
import me.clip.placeholderapi.PlaceholderAPI;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.configuration.ConfigurationPlugin;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ListenerChat implements Listener {
    @Inject
    private ConfigurationPlugin configurationPlugin;
    @Inject
    private ConfigurationMessage configurationMessage;

    @EventHandler
    public void onChatEvent(AsyncChatEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("exotia.lobby.chat")) {
            event.setCancelled(true);
            UtilMessage.sendMessage(player, configurationMessage.getEventsChat().getFailed());
            UtilMessage.playSound(player, configurationMessage.getSounds().getError());
            return;
        }

        String color = configurationPlugin.getFormats().get(PlaceholderAPI.setPlaceholders(player, "%vault_group%")) != null ? configurationPlugin.getFormats().get(PlaceholderAPI.setPlaceholders(player, "%vault_group%")) : "";
        event.message(UtilMessage.getComponent(player, configurationMessage.getEventsChat().getSuccess(), player.getDisplayName(), color, event.message().toString()));
    }
}
