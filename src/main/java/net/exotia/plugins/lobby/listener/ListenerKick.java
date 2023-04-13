package net.exotia.plugins.lobby.listener;

import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class ListenerKick implements Listener {
    @Inject
    private ConfigurationMessage configurationMessage;

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        if (event.getPlayer().hasPermission("exotia.lobby.bypass")) event.setCancelled(true);
        if (!event.getReason().equals("You have been idle for too long!")) return;
        event.setReason(UtilMessage.getMessage(event.getPlayer(), configurationMessage.getEventsDisonnect().getTimeout()));
    }
}
