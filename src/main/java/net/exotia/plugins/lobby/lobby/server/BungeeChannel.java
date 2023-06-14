package net.exotia.plugins.lobby.lobby.server;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.lobby.LobbyPlugin;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.configuration.ConfigurationPlugin;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.entity.Player;

public class BungeeChannel {
    @Inject
    private ConfigurationPlugin configurationPlugin;
    @Inject ConfigurationMessage configurationMessage;

    public void sendPlayer(Player player, String server) {
        if (!player.hasPermission("exotia.lobby.server." + server)) {
            UtilMessage.sendMessage(player, configurationMessage.getCommandsServer().getInvalid());
            UtilMessage.playSound(player, configurationMessage.getSounds().getError());
            return;
        }
        try {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(server.toLowerCase());
            player.sendPluginMessage(LobbyPlugin.getPlugin(), "BungeeCord", out.toByteArray());
            UtilMessage.sendMessage(player, configurationMessage.getCommandsServer().getSuccess(), server);
            UtilMessage.playSound(player, configurationMessage.getSounds().getClick());
            player.closeInventory();
        } catch (Exception error) {
            UtilMessage.sendMessage(player, configurationMessage.getCommandsServer().getError(), server);
            UtilMessage.playSound(player, configurationMessage.getSounds().getError());
        }
    }
}