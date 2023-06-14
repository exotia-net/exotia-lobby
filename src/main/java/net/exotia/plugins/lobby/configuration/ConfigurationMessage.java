package net.exotia.plugins.lobby.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;

@Getter
public class ConfigurationMessage extends OkaeriConfig {
    @Comment("No permission message")
    private CommandsNoPermission commandsNoPermission = new CommandsNoPermission();
    @Comment("Invalid usage message")
    private CommandsInvalid commandsInvalid = new CommandsInvalid();
    @Comment("Player Command")
    private CommandsPlayer commandsPlayer = new CommandsPlayer();
    @Comment("Reload Command")
    private CommandsReload commandsReload = new CommandsReload();
    @Comment("Server Command")
    private CommandsServer commandsServer = new CommandsServer();
    @Comment("Vanish Command")
    private CommandsVanish commandsVanish = new CommandsVanish();
    @Comment("Connect Events")
    private EventsConnect eventsConnect = new EventsConnect();
    @Comment("Disconnect Events")
    private EventsDisconnect eventsDisonnect = new EventsDisconnect();
    @Comment("Disconnect Events")
    private EventsChat eventsChat = new EventsChat();
    @Comment("Link Events")
    private String eventsLink = "鱂 %value_1%";
    @Comment("Sounds")
    private Sounds sounds = new Sounds();

    @Getter
    public class CommandsNoPermission extends OkaeriConfig {
        private String failed = "鱂 Nie masz <gradient:#4fa943:#9ec52f><bold>dostępu</bold></gradient> do tej <gradient:#4fa943:#9ec52f><bold>komendy!</bold></gradient>";
    }

    @Getter
    public class CommandsInvalid extends OkaeriConfig {
        private String invalid = "鱂 Niepoprawne użycie <gradient:#4fa943:#9ec52f><bold>komendy!</bold></gradient>";
        private String invalidUsage = "鱂 Niepoprawne użycie <gradient:#4fa943:#9ec52f><bold>komendy!</bold></gradient> Spróbuj użyć <gradient:#4fa943:#9ec52f><bold>%value_1%!</bold></gradient>";
    }

    @Getter
    public class CommandsPlayer extends OkaeriConfig {
        private String offline = "鱂 Wybrany gracz jest <gradient:#4fa943:#9ec52f><bold>offline!</bold></gradient>";
        private String only = "鱂 Użycie dostępne tylko dla <gradient:#4fa943:#9ec52f><bold>graczy!</bold></gradient>";
    }

    @Getter
    public class CommandsReload extends OkaeriConfig {
        private String success = "鱂 Konfiguracja <gradient:#4fa943:#9ec52f><bold>przeładowana!</bold></gradient>";
        private String failed = "鱂 Wystąpił <gradient:#4fa943:#9ec52f><bold>błąd</bold></gradient> przy przeładowywaniu <gradient:#4fa943:#9ec52f><bold>konfiguracji!</bold></gradient>";
    }

    @Getter
    public class CommandsServer extends OkaeriConfig {
        private String success = "鱂 Zostałeś/aś przeniesiony/a na <gradient:#4fa943:#9ec52f><bold><bold>%value_1%!</bold></gradient>";
        private String error = "鱂 Wystąpił <gradient:#4fa943:#9ec52f><bold>błąd</bold></gradient> przy przenoszeniu na <gradient:#4fa943:#9ec52f><bold>%value_1%!</bold></gradient>";
        private String notFound = "鱂 Serwer, na który próbujesz się przenieść <gradient:#4fa943:#9ec52f><bold>nie istnieje!</bold></gradient>";
        private String invalid = "鱂 Serwer, na który próbujesz się przenieść jest <gradient:#4fa943:#9ec52f><bold>niedostępny!</bold></gradient>";
    }

    @Getter
    public class CommandsVanish extends OkaeriConfig {
        private String show = "鱂 Teraz widzisz innych <gradient:#4fa943:#9ec52f><bold>graczy!</bold></gradient>";
        private String hide = "鱂 Ukryłeś/aś innych <gradient:#4fa943:#9ec52f><bold>graczy!</bold></gradient>";
    }

    @Getter
    public class EventsConnect extends OkaeriConfig {
        private String join = "鱂 %value_1% dołączył/a do <gradient:#4fa943:#9ec52f><bold>Lobby!</bold></gradient>";
        private String quit = "鱂 %value_1% opuścił/a <gradient:#4fa943:#9ec52f><bold>Lobby!</bold></gradient>";
    }

    @Getter
    public class EventsDisconnect extends OkaeriConfig {
        private String timeout = "<gradient:#4fa943:#9ec52f><bold>ᴇхᴏᴛɪᴀ.ɴᴇᴛ</bold></gradient>\n\nᴍɪɴᴀʟ ᴄᴢᴀѕ ɴᴀ <gradient:#4fa943:#9ec52f><bold>ᴡʏʙᴏʀ ᴛʀʏʙᴜ!</bold></gradient>\nᴘᴏʟᴀᴄᴢ ѕɪᴇ <gradient:#4fa943:#9ec52f><bold>ᴘᴏɴᴏᴡɴɪᴇ!</bold></gradient>\n\n<gradient:#4fa943:#9ec52f><bold>ᴅᴄ.ᴇхᴏᴛɪᴀ.ɴᴇᴛ</bold></gradient>";
    }

    @Getter
    public class EventsChat extends OkaeriConfig {
        private String success = "%vault_prefix% %value_1% %value_2%%value_3%";
        private String failed = "鱂 Nie masz <gradient:#4fa943:#9ec52f><bold>dostępu</bold></gradient> do wysyłania <gradient:#4fa943:#9ec52f><bold>wiadomości!</bold></gradient>";
    }

    @Getter
    public class Sounds extends OkaeriConfig {
        private String success = "ui_toast_challenge_complete";
        private String activate = "block_note_block_pling";
        private String step = "block_note_block_xylophone";
        private String error = "block_note_block_bit";
        private String click = "ui_button_click";
    }
}
