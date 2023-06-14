package net.exotia.plugins.lobby.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.configs.exception.OkaeriException;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.lobby.configuration.ConfigurationFactory;
import net.exotia.plugins.lobby.configuration.ConfigurationGui;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.configuration.ConfigurationPlugin;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Route(name = "exotialobby", aliases = "el")
@Permission("exotia.lobby.command.reload")
public class CommandReload {
    @Inject
    private ConfigurationFactory configurationFactory;
    @Inject
    private ConfigurationPlugin configurationPlugin;
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ConfigurationGui configurationGui;

    @Execute(route = "admin reload")
    public void reload(CommandSender sender) {
        try {
            configurationPlugin.load(true);
            configurationMessage.load(true);
            configurationGui.load(true);
            UtilMessage.sendMessage(sender, configurationMessage.getCommandsReload().getSuccess());
            if (sender instanceof Player player)
                UtilMessage.playSound(player, configurationMessage.getSounds().getActivate());
        } catch (OkaeriException error) {
            UtilMessage.sendMessage(sender, configurationMessage.getCommandsReload().getFailed());
            if (sender instanceof Player player)
                UtilMessage.playSound(player, configurationMessage.getSounds().getError());
            configurationPlugin = configurationFactory.produce(ConfigurationPlugin.class, "config.yml");
            configurationMessage = configurationFactory.produce(ConfigurationMessage.class, "messages.yml");
            configurationGui = configurationFactory.produce(ConfigurationGui.class, "guis.yml");
        }
    }
}