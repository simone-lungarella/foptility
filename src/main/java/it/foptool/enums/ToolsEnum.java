package it.foptool.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ToolsEnum {
    
    TRASH_CAN("Contenitori rifiuti"),
    CAN("Contenitori"),
    GENERIC_TOOL("Utensili vari"),
    SHELV("Scaffalatura"),
    SANITATION_SERVICE("Servizi igienici"),
    FAUCETS("Rubinetteria sanitaria"),
    TOMATO_PRESS("Passa pomodori"),
    DRYING_OVEN("Forno essiccazione"),
    WIGHT_SCALE("Bilancia"),
    STEEL_BENCH("Banchi d'acciaio"),
    CHOPPING_BOARD("Taglieri"),
    LEVEL("Lavelli"),
    WINDOWS("Finestre-vetri"),
    NON_WASHABLE_WALLS("Soffitti e pareti non lavabili");

    @Getter
    private String description;

    public static ToolsEnum getByDescription(String description) {
        for (ToolsEnum tool : ToolsEnum.values()) {
            if (tool.getDescription().equals(description)) {
                return tool;
            }
        }
        return null;
    }
}
