package com.chup.advancedminingtools.extras.antirepair;

import com.chup.advancedminingtools.extras.NBTEditor;
import com.chup.advancedminingtools.extras.Version;
import com.chup.advancedminingtools.xseries.XMaterial;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class AnvilListener implements Listener {

    @EventHandler
    public void onRename(InventoryClickEvent e) {
        if (e.getInventory().getType() == InventoryType.CRAFTING || e.getInventory().getType() == InventoryType.ANVIL) {
            if (e.getRawSlot() == 2) {
                if (e.getInventory().getItem(0) != null) {
                    ItemStack tool = e.getInventory().getItem(0);
                    if (tool.getType() != XMaterial.AIR.parseMaterial() && NBTEditor.contains(tool, "TrenchMode")) {
                        e.setCancelled(true);
                    }
                }
                if (e.getInventory().getItem(1) != null) {
                    ItemStack tool = e.getInventory().getItem(1);
                    if (tool.getType() != XMaterial.AIR.parseMaterial() && NBTEditor.contains(tool, "TrenchMode")) {
                        e.setCancelled(true);
                    }
                }
            }
        } else {
            if (Version.getCurrentVersion().isOlder(Version.v1_13_R2)) { return; }
            if (e.getInventory().getType() == InventoryType.SMITHING) {
                if (e.getRawSlot() == 2) {
                    if (e.getInventory().getItem(0) != null) {
                        ItemStack tool = e.getInventory().getItem(0);
                        if (tool.getType() != XMaterial.AIR.parseMaterial() && NBTEditor.contains(tool, "TrenchMode")) {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}

