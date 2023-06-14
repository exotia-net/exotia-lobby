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
        private int size = 3;
        private List<Integer> slotsEmpty = new ArrayList<>();
        private HashMap<Integer, GuiButton> buttons = setupServerButtons();

        private HashMap<Integer, GuiButton> setupServerButtons() {
        HashMap<Integer, GuiButton> buttons = new HashMap<>();
        buttons.put(0, GuiButton.builder()
                .slots(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17))
                .id("required_blank")
                .type("oraxen")
                .displayName("Kliknij, aby <gradient:#4fa943:#9ec52f><bold>dołączyć!</bold></gradient>")
                .lore(List.of("<gray>Odkryj nasz serwer", "<gray>i jego możliwości."))
                .action(GuiAction.builder().name("server").value("survival").build())
                .build()
        );
        buttons.put(2, GuiButton.builder()
                .slots(List.of(18, 19, 20, 21, 22, 23, 24, 25, 26))
                .id("required_blank")
                .type("oraxen")
                .displayName("Kliknij, aby <gradient:#4fa943:#9ec52f><bold>dołączyć!</bold></gradient>")
                .lore(List.of("<gray>Przejdź do linku", "<gray>na czacie!"))
                .action(GuiAction.builder().name("text").value("<hover:show_text:'Kliknij, aby <gradient:#4fa943:#9ec52f><bold>dołączyć!</bold></gradient>'><click:OPEN_URL:'https://dc.exotia.net/'>Link do naszego <gradient:#4fa943:#9ec52f><bold>discorda!</bold></gradient></click></hover>").build())
                .build()
        );
        return buttons;
    }
}
