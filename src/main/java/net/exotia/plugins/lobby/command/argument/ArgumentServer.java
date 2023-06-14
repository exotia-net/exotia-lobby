package net.exotia.plugins.lobby.command.argument;

import dev.rollczi.litecommands.argument.ArgumentName;
import dev.rollczi.litecommands.argument.simple.OneArgument;
import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.suggestion.Suggestion;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.configuration.ConfigurationPlugin;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.entity.Player;
import panda.std.Option;
import panda.std.Result;

import java.util.List;
import java.util.stream.Collectors;

@ArgumentName("server")
public class ArgumentServer implements OneArgument<String> {
    @Inject
    private ConfigurationPlugin configurationPlugin;
    @Inject
    private ConfigurationMessage configurationMessage;
    public static final String KEY = "server_name";

    @Override
    public Result<String, ?> parse(LiteInvocation liteInvocation, String s) {
        String argument = configurationPlugin.getServerList().stream().filter(string -> string.equals(s)).findFirst().orElse(null);
        if (argument == null) UtilMessage.playSound((Player) liteInvocation.sender().getHandle(), configurationMessage.getSounds().getError());
        return Option.of(argument).toResult(UtilMessage.getMessage(configurationMessage.getCommandsServer().getNotFound()));
    }

    @Override
    public List<Suggestion> suggest(LiteInvocation invocation) {
        List<String> serverList = configurationPlugin.getServerList();
        return serverList.stream()
            .filter(string -> invocation.sender().hasPermission("exotia.lobby.server." + string))
            .map(Suggestion::of)
            .collect(Collectors.toList());
    }
}
