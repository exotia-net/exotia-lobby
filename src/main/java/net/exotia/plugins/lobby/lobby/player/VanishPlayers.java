package net.exotia.plugins.lobby.lobby.player;

import net.exotia.plugins.lobby.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class VanishPlayers {
    private final HashMap<UUID, Boolean> hiddenPlayers = new HashMap<>();

    public Boolean getPlayer(Player player) {
        return hiddenPlayers.get(player.getUniqueId());
    }

    public void hidePlayers(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) player.hidePlayer(LobbyPlugin.getPlugin(), target);
        hiddenPlayers.put(player.getUniqueId(), true);
    }

    public void showPlayers(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) player.showPlayer(LobbyPlugin.getPlugin(), target);
        hiddenPlayers.remove(player.getUniqueId());
    }

    public void removePlayer(Player player) {
        if (hiddenPlayers.get(player.getUniqueId()) != null) hiddenPlayers.remove(player.getUniqueId());
    }

    public void clear() {
        hiddenPlayers.clear();
    }
}
