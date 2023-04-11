package net.exotia.plugins.lobby.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.exotia.plugins.lobby.LobbyPlugin;
import org.bukkit.entity.Player;

public class UtilChannel {

    public static boolean sendPlayer(Player player, String server) {
        try {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(server.toLowerCase());
            player.sendPluginMessage(LobbyPlugin.getPlugin(), "BungeeCord", out.toByteArray());
            return true;
        } catch (Exception error) {
            return false;
        }
    }
}
