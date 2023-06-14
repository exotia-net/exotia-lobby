package net.exotia.plugins.lobby.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import net.exotia.plugins.lobby.configuration.section.SectionSelector;
import net.exotia.plugins.lobby.lobby.gui.GuiAction;
import net.exotia.plugins.lobby.lobby.gui.GuiButton;

import java.util.HashMap;
import java.util.List;

@Getter
public class ConfigurationGui extends OkaeriConfig {
    @Comment("Hotbar buttons")
    private HashMap<Integer, GuiButton> guiButtons = setupGuiButtons();
    @Comment("Selector GUI")
    private SectionSelector guiSelector = new SectionSelector();

    private HashMap<Integer, GuiButton> setupGuiButtons() {
        HashMap<Integer, GuiButton> buttons = new HashMap<>();
        buttons.put(4, GuiButton.builder()
                .id("book_selector")
                .type("oraxen")
                .displayName("<gradient:#4fa943:#9ec52f><bold>Wybierz Serwer</bold></gradient>")
                .lore(List.of("<gray>Kliknij, aby otworzyć", "<gray>menu wyboru serwerów!"))
                .slots(List.of(4))
                .action(GuiAction.builder().name("open").value("server").build())
                .build()
        );
        return buttons;
    }
}
