package net.exotia.plugins.lobby.command;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.lobby.player.VanishPlayers;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.entity.Player;

@Command(name = "vanish", aliases = "gracze")
@Permission("exotia.lobby.command.vanish")
public class CommandVanish {
    @Inject
    private VanishPlayers vanishPlayers;
    @Inject
    private ConfigurationMessage configurationMessage;

    @Execute
    void switchPlayers(@Context Player player) {
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

    @Execute(name = "off", aliases = {"ukryj"})
    void hidePlayers(@Context Player player) {
        vanishPlayers.hidePlayers(player);
        UtilMessage.sendMessage(player, configurationMessage.getCommandsVanish().getHide());
        UtilMessage.playSound(player, configurationMessage.getSounds().getStep());
    }

    @Execute(name = "on", aliases = {"pokaz"})
    void showPlayers(@Context Player player) {
        vanishPlayers.showPlayers(player);
        UtilMessage.sendMessage(player, configurationMessage.getCommandsVanish().getShow());
        UtilMessage.playSound(player, configurationMessage.getSounds().getStep());
    }
}
