package net.exotia.plugins.lobby.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.List;

@Getter
public class ConfigurationPlugin extends OkaeriConfig {
    @Comment("Spawn")
    private Location location = new Location(Bukkit.getWorld("world"), -4.5, 63.0, -1.5, 180, -5);
    @Comment("Limit")
    private int limit = 45;
    @Comment("Server list")
    private List<String> serverList = List.of("survival");
    @Comment("Chat formats")
    private HashMap<String, String> formats = setupFormats();

    private HashMap<String, String> setupFormats() {
        HashMap<String, String> formats = new HashMap<>();
        formats.put("kreator", "<color:#96d68d>");
        formats.put("admin", "<color:#d6918d>");
        formats.put("mod", "<color:#8d9fd6>");
        formats.put("artysta", "<color:#d6d58d>");
        formats.put("media", "<color:#bf8dd6>");
        formats.put("patron", "<color:#d68da8>");
        formats.put("legenda", "<color:#d6b38d>");
        formats.put("mistrz", "<color:#aad68d>");
        formats.put("bohater", "<color:#8dbed6>");
        formats.put("gracz", "<color:#9ea19d>");
        formats.put("default", "<color:#9ea19d>");
        return formats;
    }
}
