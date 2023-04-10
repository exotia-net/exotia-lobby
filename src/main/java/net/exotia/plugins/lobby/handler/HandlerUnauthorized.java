package net.exotia.plugins.lobby.handler;

import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.command.permission.RequiredPermissions;
import dev.rollczi.litecommands.handle.PermissionHandler;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.command.CommandSender;

public class HandlerUnauthorized implements PermissionHandler<CommandSender> {
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private UtilMessage utilMessage;

    @Override
    public void handle(CommandSender sender, LiteInvocation invocation, RequiredPermissions requiredPermissions) {
        utilMessage.sendMessage(sender, configurationMessage.getCommandsNoPermission().getFailed());
    }
}
