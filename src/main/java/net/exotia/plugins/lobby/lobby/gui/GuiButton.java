package net.exotia.plugins.lobby.lobby.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.GuiItem;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;
import net.exotia.plugins.lobby.utils.UtilItem;
import net.exotia.plugins.lobby.utils.UtilMessage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Stream;

@Builder
@Getter
public class GuiButton extends OkaeriConfig {
    private String id;
    private String type;
    private String displayName;
    private List<String> lore;
    private List<Integer> slots;
    private GuiAction action;

    public ItemStack getItem(String... placeholders) {
        return UtilItem.getItem(id, type, UtilMessage.convertComponent(displayName, placeholders), UtilMessage.convertComponent(getLore(), placeholders), 1);
    }

    public ItemStack getItem(List<String> items, String... placeholders) {
        return UtilItem.getItem(id, type, UtilMessage.convertComponent(displayName, placeholders), UtilMessage.convertComponent(Stream.concat(getLore().stream(), items.stream()).toList(), placeholders), 1);
    }

    public GuiItem getGuiItem(Player player, String sound, List<String> items, String... placeholders) {
        return ItemBuilder.from(UtilItem.getItem(id, type, UtilMessage.convertComponent(displayName, placeholders), UtilMessage.convertComponent(Stream.concat(getLore().stream(), items.stream()).toList(), placeholders), 1)).asGuiItem(event -> UtilMessage.playSound(player, sound));
    }
}
