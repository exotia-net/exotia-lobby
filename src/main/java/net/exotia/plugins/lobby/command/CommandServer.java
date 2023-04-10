package net.exotia.plugins.lobby.command;

import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.lobby.configuration.ConfigurationGui;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.gui.GuiAction;
import net.exotia.plugins.lobby.gui.GuiType;
import org.bukkit.entity.Player;

@Route(name = "server", aliases = "serwer")
@Permission("exotia.lobby.command.server")
public class CommandServer {
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ConfigurationGui configurationGui;

    @Execute
    public void openGUI(Player player) {
        GuiType.SERVER.open(player, configurationGui, configurationMessage);
    }

    @Execute(min = 1)
    public void sendPlayer(Player player, @Arg String serverName) {
        GuiAction.ActionType.SERVER.runAction(player, configurationGui, configurationMessage, serverName);
    }
}
