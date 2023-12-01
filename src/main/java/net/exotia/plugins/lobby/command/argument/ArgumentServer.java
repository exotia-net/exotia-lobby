package net.exotia.plugins.lobby.command.argument;

import dev.rollczi.litecommands.argument.Argument;
import dev.rollczi.litecommands.argument.parser.ParseResult;
import dev.rollczi.litecommands.argument.resolver.ArgumentResolver;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.suggestion.SuggestionContext;
import dev.rollczi.litecommands.suggestion.SuggestionResult;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.configuration.ConfigurationPlugin;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.stream.Collectors;

public class ArgumentServer extends ArgumentResolver<CommandSender, String> {
    @Inject
    private ConfigurationPlugin configurationPlugin;
    @Inject
    private ConfigurationMessage configurationMessage;

    @Override
    protected ParseResult<String> parse(Invocation invocation, Argument context, String argument) {
        String serverName = configurationPlugin.getServerList()
                .stream().filter(string -> string.equals(argument))
                .findFirst().orElse(null);

        if (serverName == null && invocation.sender() instanceof Player player) {
            UtilMessage.playSound(player, configurationMessage.getSounds().getError());
            UtilMessage.sendMessage(player, configurationMessage.getCommandsServer().getNotFound());
        }

        return ParseResult.success(serverName);
    }

    @Override
    public SuggestionResult suggest(Invocation invocation, Argument argument, SuggestionContext context) {
        return SuggestionResult.of(configurationPlugin.getServerList()
                .stream().filter(string -> invocation.platformSender().hasPermission("exotia.lobby.server." + string))
                .collect(Collectors.toList()));
    }
}
