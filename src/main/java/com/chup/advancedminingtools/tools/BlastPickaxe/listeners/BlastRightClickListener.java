package com.chup.advancedminingtools.tools.BlastPickaxe.listeners;

import com.chup.advancedminingtools.tools.BlastPickaxe.BlastSelectionGUI;
import com.chup.advancedminingtools.Extras;
import com.chup.advancedminingtools.Main;
import com.chup.advancedminingtools.extras.NBTEditor;
import com.chup.advancedminingtools.xseries.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BlastRightClickListener implements Listener {

    private Main plugin;

    public BlastRightClickListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Action action = e.getAction();
        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();
            ItemStack tool = Extras.getItemInHand(player);
            if (Extras.getItemInHand(player).getType() != XMaterial.AIR.parseMaterial()) {
                int radius = NBTEditor.getInt(tool, "Radius");
                String name = plugin.getMessages().getString("blast-tool.name");
                name = name.replace("{radius}", radius + "x" + radius + "x" + radius);
                name = ChatColor.translateAlternateColorCodes('&', name);
                if (tool != null && tool.hasItemMeta() && tool.getItemMeta().getDisplayName() != null && tool.getItemMeta().getDisplayName().equals(name)) {
                    if (!NBTEditor.contains(tool, "Switchable") || NBTEditor.getBoolean(tool, "Switchable")) {
                        Inventory inv = new BlastSelectionGUI(this.plugin).openInventory();
                        player.openInventory(inv);
                    }
                }
            }
        }
    }
}
