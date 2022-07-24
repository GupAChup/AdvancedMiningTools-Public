package com.chup.advancedminingtools.tools.ShockwavePickaxe;

import com.chup.advancedminingtools.Main;

public class ShockwaveConfig {

    private static Main main;

    public ShockwaveConfig(Main main) {

        ShockwaveConfig.main = main;
        main.getConfig().options().copyDefaults();
        main.saveDefaultConfig();

    }

    public static String getMainSelectionColor() {return main.getConfig().getString("tools.shockwave-pickaxe.gui_main_color");}
    public static String getSecondarySelectionColor() {return main.getConfig().getString("tools.shockwave-pickaxe.gui_secondary_color");}
    public static String getComplimentSelectionColor() {return main.getConfig().getString("tools.shockwave-pickaxe.gui_compliment_color");}

}
