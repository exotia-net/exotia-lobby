package net.exotia.plugins.lobby.utils;

import net.exotia.plugins.lobby.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class UtilVanish {
    private static final HashMap<UUID, Boolean> hiddenPlayers = new HashMap<>();

    public static Boolean getPlayer(Player player) {
        return hiddenPlayers.get(player.getUniqueId());
    }

    public static void hidePlayers(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) player.hidePlayer(LobbyPlugin.getPlugin(), target);
        hiddenPlayers.put(player.getUniqueId(), true);
    }

    public static void showPlayers(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) player.showPlayer(LobbyPlugin.getPlugin(), target);
        hiddenPlayers.remove(player.getUniqueId());
    }

    public static void removePlayer(Player player) {
        if (hiddenPlayers.get(player.getUniqueId()) != null) hiddenPlayers.remove(player.getUniqueId());
    }
}
