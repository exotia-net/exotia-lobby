package net.exotia.plugins.lobby.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.utils.UtilMessage;
import net.exotia.plugins.lobby.utils.UtilVanish;
import org.bukkit.entity.Player;

@Route(name = "vanish", aliases = "gracze")
@Permission("exotia.lobby.command.vanish")
public class CommandVanish {
    @Inject
    private ConfigurationMessage configurationMessage;

    @Execute
    void switchPlayers(Player player) {
        if (UtilVanish.getPlayer(player) == null) UtilVanish.hidePlayers(player);
        UtilVanish.showPlayers(player);
    }

    @Route(name = "off", aliases = {"ukryj"})
    void hidePlayers(Player player) {
        UtilVanish.hidePlayers(player);
        UtilMessage.sendMessage(player, configurationMessage.getCommandsVanish().getHide());
        UtilMessage.playSound(player, configurationMessage.getSounds().getStep());
    }

    @Route(name = "on", aliases = {"pokaz"})
    void showPlayers(Player player) {
        UtilVanish.showPlayers(player);
        UtilMessage.sendMessage(player, configurationMessage.getCommandsVanish().getHide());
        UtilMessage.playSound(player, configurationMessage.getSounds().getStep());
    }
}
