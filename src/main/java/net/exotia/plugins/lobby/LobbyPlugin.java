package net.exotia.plugins.lobby;

import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.bukkit.tools.BukkitOnlyPlayerContextual;
import dev.rollczi.litecommands.bukkit.tools.BukkitPlayerArgument;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.OkaeriInjector;
import lombok.Getter;
import net.exotia.plugins.lobby.command.CommandReload;
import net.exotia.plugins.lobby.command.CommandServer;
import net.exotia.plugins.lobby.command.CommandVanish;
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
import net.exotia.plugins.lobby.utils.UtilMessage;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

public final class LobbyPlugin extends JavaPlugin {
    private final Injector injector = OkaeriInjector.create();
    @Getter
    private static Plugin plugin;
    @Getter
    private static BukkitAudiences audiences;
    private final ConfigurationFactory configurationFactory = new ConfigurationFactory(this.getDataFolder());
    private ConfigurationPlugin configurationPlugin;
    private ConfigurationMessage configurationMessage;
    private ConfigurationGui configurationGui;

    @Override
    public void onEnable() {
        plugin = this;
        audiences = BukkitAudiences.create(this);

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
    }

    private void setupCommands() {
        LiteBukkitFactory.builder(this.getServer(), "exotia.net")
                .argument(Player.class, new BukkitPlayerArgument<>(this.getServer(), UtilMessage.getMessage(configurationMessage.getCommandsPlayer().getOffline())))
                .contextualBind(Player.class, new BukkitOnlyPlayerContextual<>(UtilMessage.getMessage(configurationMessage.getCommandsPlayer().getOnly())))
                .commandInstance(
                        injector.createInstance(CommandReload.class),
                        injector.createInstance(CommandServer.class),
                        injector.createInstance(CommandVanish.class)
                )
                .invalidUsageHandler(injector.createInstance(HandlerInvalid.class))
                .permissionHandler(injector.createInstance(HandlerUnauthorized.class))
                .register();
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

        configurationPlugin.save();
        configurationMessage.save();
        configurationGui.save();
    }
}