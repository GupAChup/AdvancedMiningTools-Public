package com.chup.advancedminingtools.tools.ShockwavePickaxe;

import com.chup.advancedminingtools.Extras;
import com.chup.advancedminingtools.Main;
import com.chup.advancedminingtools.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShockwaveSelectionGUI {

    private Main plugin;

    public ShockwaveSelectionGUI(Main plugin) {
        this.plugin = plugin;
    }

    public Inventory openInventory() {

        String guiName = plugin.getMessages().getString("shockwave-tool.selectiongui.gui_name");

        Inventory gui = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', guiName));

        ItemStack trench = new ItemStack(XMaterial.BRICK.parseMaterial());
        ItemMeta trenchMeta = trench.getItemMeta();
        String trenchName = plugin.getMessages().getString("shockwave-tool.selectiongui.trench.name");
        trenchMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', trenchName));
        List<String> trenchLore = new ArrayList<>();
        Iterator trenchLoreIterator = plugin.getMessages().getStringList("shockwave-tool.selectiongui.trench.lore").iterator();
        while (trenchLoreIterator.hasNext()) {
            String trenchLores = (String)trenchLoreIterator.next();
            trenchLore.add(ChatColor.translateAlternateColorCodes('&', trenchLores));
        }
        trenchMeta.setLore(trenchLore);
        trench.setItemMeta(trenchMeta);

        ItemStack tray = new ItemStack(XMaterial.NETHER_BRICK.parseMaterial());
        ItemMeta trayMeta = tray.getItemMeta();
        String trayName = plugin.getMessages().getString("shockwave-tool.selectiongui.tray.name");
        trayMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', trayName));
        List<String> trayLore = new ArrayList<>();
        Iterator trayLoreIterator = plugin.getMessages().getStringList("shockwave-tool.selectiongui.tray.lore").iterator();
        while (trayLoreIterator.hasNext()) {
            String trayLores = (String)trayLoreIterator.next();
            trayLore.add(ChatColor.translateAlternateColorCodes('&', trayLores));
        }
        trayMeta.setLore(trayLore);
        tray.setItemMeta(trayMeta);

        ItemStack mainBackground = Extras.getColor(ShockwaveConfig.getMainSelectionColor());
        ItemStack secondaryBackground = Extras.getColor(ShockwaveConfig.getSecondarySelectionColor());
        ItemStack complimentBackground = Extras.getColor(ShockwaveConfig.getComplimentSelectionColor());

        gui.setItem(0, mainBackground);
        gui.setItem(1, secondaryBackground);
        gui.setItem(2, complimentBackground);
        gui.setItem(3, mainBackground);
        gui.setItem(4, mainBackground);
        gui.setItem(5, mainBackground);
        gui.setItem(6, complimentBackground);
        gui.setItem(7, secondaryBackground);
        gui.setItem(8, mainBackground);
        gui.setItem(9, mainBackground);
        gui.setItem(10, complimentBackground);
        gui.setItem(11, trench);
        gui.setItem(12, complimentBackground);
        gui.setItem(13, secondaryBackground);
        gui.setItem(14, complimentBackground);
        gui.setItem(15, tray);
        gui.setItem(16, complimentBackground);
        gui.setItem(17, mainBackground);
        gui.setItem(18, mainBackground);
        gui.setItem(19, secondaryBackground);
        gui.setItem(20, complimentBackground);
        gui.setItem(21, mainBackground);
        gui.setItem(22, mainBackground);
        gui.setItem(23, mainBackground);
        gui.setItem(24, complimentBackground);
        gui.setItem(25, secondaryBackground);
        gui.setItem(26, mainBackground);

        return gui;
    }

}
