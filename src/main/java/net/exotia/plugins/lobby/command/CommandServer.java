package net.exotia.plugins.lobby.command;

import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.By;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.lobby.command.argument.ArgumentServer;
import net.exotia.plugins.lobby.configuration.ConfigurationGui;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.configuration.ConfigurationPlugin;
import net.exotia.plugins.lobby.lobby.gui.GuiSelector;
import net.exotia.plugins.lobby.lobby.server.BungeeChannel;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.entity.Player;

@Route(name = "servers", aliases = "serwer")
@Permission("exotia.lobby.command.server")
public class CommandServer {
    @Inject
    private ConfigurationPlugin configurationPlugin;
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ConfigurationGui configurationGui;
    @Inject
    private BungeeChannel bungeeChannel;
    @Inject
    private GuiSelector guiSelector;

    @Execute(required = 0)
    public void openGUI(Player player) {
        guiSelector.open(player);
    }

    @Execute(required = 1)
    public void sendPlayer(Player player, @Arg @By(ArgumentServer.KEY) String serverName) {
        bungeeChannel.sendPlayer(player, serverName);
    }
}
