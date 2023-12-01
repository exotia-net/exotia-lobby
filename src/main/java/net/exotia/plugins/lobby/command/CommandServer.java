package net.exotia.plugins.lobby.command;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.lobby.configuration.ConfigurationGui;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.configuration.ConfigurationPlugin;
import net.exotia.plugins.lobby.lobby.gui.GuiSelector;
import net.exotia.plugins.lobby.lobby.server.BungeeChannel;
import org.bukkit.entity.Player;

@Command(name = "servers", aliases = "serwer")
@Permission("exotia.lobby.command.server")
public class CommandServer {
    @Inject
    private BungeeChannel bungeeChannel;
    @Inject
    private ConfigurationPlugin configurationPlugin;
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ConfigurationGui configurationGui;
    @Inject
    private GuiSelector guiSelector;

    @Execute
    public void openGUI(@Context Player player) {
        guiSelector.open(player);
    }

    @Execute
    public void sendPlayer(@Context Player player, @Arg String serverName) {
        bungeeChannel.sendPlayer(player, serverName);
    }
}
