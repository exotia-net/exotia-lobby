package net.exotia.plugins.lobby;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.adventure.LiteAdventureExtension;
import dev.rollczi.litecommands.annotations.LiteCommandsAnnotations;
import dev.rollczi.litecommands.bukkit.LiteCommandsBukkit;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.OkaeriInjector;
import lombok.Getter;
import net.exotia.plugins.lobby.command.CommandReload;
import net.exotia.plugins.lobby.command.CommandServer;
import net.exotia.plugins.lobby.command.CommandVanish;
import net.exotia.plugins.lobby.command.argument.ArgumentServer;
import net.exotia.plugins.lobby.configuration.ConfigurationFactory;
import net.exotia.plugins.lobby.configuration.ConfigurationGui;
import net.exotia.plugins.lobby.configuration.ConfigurationMessage;
import net.exotia.plugins.lobby.configuration.ConfigurationPlugin;
import net.exotia.plugins.lobby.handler.HandlerInvalid;
import net.exotia.plugins.lobby.handler.HandlerUnauthorized;
import net.exotia.plugins.lobby.listener.ListenerChat;
import net.exotia.plugins.lobby.listener.ListenerJoinQuit;
import net.exotia.plugins.lobby.listener.ListenerKick;
import net.exotia.plugins.lobby.listener.ListenerSpawn;
import net.exotia.plugins.lobby.lobby.gui.GuiSelector;
import net.exotia.plugins.lobby.lobby.player.VanishPlayers;
import net.exotia.plugins.lobby.lobby.server.BungeeChannel;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

public final class LobbyPlugin extends JavaPlugin {
    @Getter
    private static Plugin plugin;
    private final Injector injector = OkaeriInjector.create();
    private final ConfigurationFactory configurationFactory = new ConfigurationFactory(this.getDataFolder());
    private LiteCommands<CommandSender> liteCommands;
    private ConfigurationPlugin configurationPlugin;
    private ConfigurationMessage configurationMessage;
    private ConfigurationGui configurationGui;
    private VanishPlayers vanishPlayers;

    @Override
    public void onEnable() {
        plugin = this;

        injector.registerInjectable(plugin);
        injector.registerInjectable(injector);
        injector.registerInjectable(configurationFactory);

        setupConfiguration();
        setupUtils();
        setupCommands();
        setupEvents();
    }

    @Override
    public void onDisable() {
        cleanUp();
    }

    private void setupConfiguration() {
        configurationPlugin = configurationFactory.produce(ConfigurationPlugin.class, "config.yml");
        configurationMessage = configurationFactory.produce(ConfigurationMessage.class, "messages.yml");
        configurationGui = configurationFactory.produce(ConfigurationGui.class, "guis.yml");

        injector.registerInjectable(configurationPlugin);
        injector.registerInjectable(configurationMessage);
        injector.registerInjectable(configurationGui);
    }

    private void setupUtils() {
        getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");

        vanishPlayers = new VanishPlayers();
        injector.registerInjectable(vanishPlayers);
        injector.registerInjectable(injector.createInstance(BungeeChannel.class));
        injector.registerInjectable(injector.createInstance(GuiSelector.class));
    }

    private void setupCommands() {
        liteCommands = LiteCommandsBukkit.builder("exotia.net", plugin)
                .extension(new LiteAdventureExtension<CommandSender>()
                        .miniMessage(true)
                        .legacyColor(true)
                        .colorizeArgument(true)
                        .serializer(MiniMessage.miniMessage())
                )
                .commands(LiteCommandsAnnotations.of(
                                injector.createInstance(CommandReload.class),
                                injector.createInstance(CommandServer.class),
                                injector.createInstance(CommandVanish.class)
                        )
                )
                .argument(String.class, injector.createInstance(ArgumentServer.class))
                .invalidUsage(injector.createInstance(HandlerInvalid.class))
                .missingPermission(injector.createInstance(HandlerUnauthorized.class))
                .build();
    }

    private void setupEvents() {
        Stream.of(
                injector.createInstance(ListenerChat.class),
                injector.createInstance(ListenerJoinQuit.class),
                injector.createInstance(ListenerKick.class),
                injector.createInstance(ListenerSpawn.class)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void cleanUp() {
        getServer().getMessenger().unregisterOutgoingPluginChannel(this);

        if (configurationPlugin != null) configurationPlugin.save();
        if (configurationMessage != null) configurationMessage.save();
        if (configurationGui != null) configurationGui.save();

        if (liteCommands != null) liteCommands.unregister();

        vanishPlayers.clear();
    }
}