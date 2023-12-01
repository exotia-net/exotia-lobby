package net.exotia.plugins.lobby.handler;

import dev.rollczi.litecommands.handler.result.ResultHandlerChain;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.permission.MissingPermissions;
import dev.rollczi.litecommands.permission.MissingPermissionsHandler;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import org.bukkit.command.CommandSender;

public class HandlerUnauthorized implements MissingPermissionsHandler<CommandSender> {
    @Inject
    private ConfigurationMessage configurationMessage;

    @Override
    public void handle(Invocation<CommandSender> invocation, MissingPermissions missingPermissions, ResultHandlerChain<CommandSender> chain) {
//        UtilMessage.sendMessage(sender, configurationMessage.getCommandsNoPermission().getFailed());
//        UtilMessage.playSound((Player) sender, configurationMessage.getSounds().getError());
    }
}
