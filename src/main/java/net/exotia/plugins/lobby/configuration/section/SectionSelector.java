package net.exotia.plugins.lobby.configuration.section;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Getter;
import net.exotia.plugins.lobby.lobby.gui.GuiAction;
import net.exotia.plugins.lobby.lobby.gui.GuiButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class SectionSelector extends OkaeriConfig {
        private String title = "✟ꟹ";
        private int size = 4;
        private List<Integer> slotsEmpty = new ArrayList<>();
        private HashMap<Integer, GuiButton> buttons = setupServerButtons();

        private HashMap<Integer, GuiButton> setupServerButtons() {
        HashMap<Integer, GuiButton> buttons = new HashMap<>();
        buttons.put(0, GuiButton.builder()
                .slots(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17))
                .id("required_blank")
                .type("oraxen")
                .displayName("<gradient:#4fa943:#9ec52f><bold>Tryb Survival 1.20+</bold></gradient>")
                .lore(List.of("<gray>Odkryj nasz serwer", "<gray>i jego możliwości."))
                .action(GuiAction.builder().name("command").value("queue survival").build())
                .build()
        );
        buttons.put(2, GuiButton.builder()
                .slots(List.of(27, 28, 29, 30, 31, 32, 33, 34, 35))
                .id("required_blank")
                .type("oraxen")
                .displayName("<gradient:#4fa943:#9ec52f><bold>Dołącz do nas!</bold></gradient>")
                .lore(List.of("<gray>Przejdź do linku", "<gray>na czacie!"))
                .action(GuiAction.builder().name("text").value("黾 <hover:show_text:'Kliknij, aby <gradient:#5964f0:#404ff3><bold>dołączyć!</bold></gradient>'><click:OPEN_URL:'https://dc.exotia.net/'>Dołącz na naszego <gradient:#5964f0:#404ff3><bold><u>discorda!</u><bold></gradient></click></hover>").build())
                .build()
        );
        return buttons;
    }
}
