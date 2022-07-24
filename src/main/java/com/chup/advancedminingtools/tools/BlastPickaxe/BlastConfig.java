package com.chup.advancedminingtools.tools.BlastPickaxe;

import com.chup.advancedminingtools.Main;

public class BlastConfig {

    private static Main main;

    public BlastConfig(Main main) {

        BlastConfig.main = main;
        main.getConfig().options().copyDefaults();
        main.saveDefaultConfig();

    }

    public static String getMainSelectionColor() {return main.getConfig().getString("tools.blast-pickaxe.gui_main_color");}
    public static String getSecondarySelectionColor() {return main.getConfig().getString("tools.blast-pickaxe.gui_secondary_color");}
    public static String getComplimentSelectionColor() {return main.getConfig().getString("tools.blast-pickaxe.gui_compliment_color");}

}
