package net.exotia.plugins.lobby.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.lobby.player.VanishPlayers;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.entity.Player;

@Route(name = "gracze", aliases = "vanish")
@Permission("exotia.lobby.command.vanish")
public class CommandVanish {
    @Inject
    private VanishPlayers vanishPlayers;
    @Inject
    private ConfigurationMessage configurationMessage;

    @Execute
    void switchPlayers(Player player) {
        if (vanishPlayers.getPlayer(player) == null) {
            vanishPlayers.hidePlayers(player);
            UtilMessage.sendMessage(player, configurationMessage.getCommandsVanish().getHide());
            UtilMessage.playSound(player, configurationMessage.getSounds().getStep());
            return;
        }
        vanishPlayers.showPlayers(player);
        UtilMessage.sendMessage(player, configurationMessage.getCommandsVanish().getShow());
        UtilMessage.playSound(player, configurationMessage.getSounds().getStep());
    }

    @Route(name = "off", aliases = {"ukryj"})
    void hidePlayers(Player player) {
        vanishPlayers.hidePlayers(player);
        UtilMessage.sendMessage(player, configurationMessage.getCommandsVanish().getHide());
        UtilMessage.playSound(player, configurationMessage.getSounds().getStep());
    }

    @Route(name = "on", aliases = {"pokaz"})
    void showPlayers(Player player) {
        vanishPlayers.showPlayers(player);
        UtilMessage.sendMessage(player, configurationMessage.getCommandsVanish().getShow());
        UtilMessage.playSound(player, configurationMessage.getSounds().getStep());
    }
}
