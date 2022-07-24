package com.chup.advancedminingtools.extras.antirepair;

import com.chup.advancedminingtools.Extras;
import com.chup.advancedminingtools.Main;
import com.chup.advancedminingtools.extras.NBTEditor;
import com.chup.advancedminingtools.xseries.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;

public class RepairListener implements Listener {

    private Main plugin;

    public RepairListener(Main plugin) {
        this.plugin = plugin;
    }

    String prefix = ChatColor.RED.toString() + ChatColor.BOLD + "AdvancedMiningTools " + ChatColor.DARK_RED.toString() + ChatColor.BOLD + ">> ";

    @EventHandler(priority = EventPriority.HIGH)
    public void onRepair(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().toLowerCase().startsWith("/repair")) {
            Player player = e.getPlayer();
            ItemStack tool = Extras.getItemInHand(player);
            if (tool.getType() != XMaterial.AIR.parseMaterial() && NBTEditor.contains(tool, "TrenchMode")) {
                boolean unbreakable = NBTEditor.getBoolean(tool, "Break");
                if (!unbreakable) {
                    String message = plugin.getMessages().getString("invalid-repair");
                    player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onFix(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().toLowerCase().startsWith("/fix")) {
            Player player = e.getPlayer();
            ItemStack tool = Extras.getItemInHand(player);
            if (tool.getType() != XMaterial.AIR.parseMaterial() && NBTEditor.contains(tool, "TrenchMode")) {
                boolean unbreakable = NBTEditor.getBoolean(tool, "Break");
                if (!unbreakable) {
                    String message = plugin.getMessages().getString("invalid-repair");
                    player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onRepairAll(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().toLowerCase().startsWith("/repairall")) {
            Player player = e.getPlayer();
            Boolean exists = false;
            for (int i = 0; i < player.getInventory().getSize(); i++) {
                ItemStack tool = player.getInventory().getItem(i);
                if (tool.getType() != XMaterial.AIR.parseMaterial() && NBTEditor.contains(tool, "TrenchMode")) {
                    boolean unbreakable = NBTEditor.getBoolean(tool, "Break");
                    if (!unbreakable) {
                        exists = true;
                    }
                }
            }
            if (exists) {
                String message = plugin.getMessages().getString("invalid-repair");
                player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                e.setCancelled(true);
            }
        }
    }
}
