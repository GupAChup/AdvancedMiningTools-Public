package com.chup.advancedminingtools.tools.BlastPickaxe.listeners;

import com.chup.advancedminingtools.Extras;
import com.chup.advancedminingtools.Main;
import com.chup.advancedminingtools.extras.NBTEditor;
import com.chup.advancedminingtools.xseries.XMaterial;
import com.chup.advancedminingtools.xseries.XSound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class BlastClickListener implements Listener {

    private final Main plugin;

    public BlastClickListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        String selectionGuiName = plugin.getMessages().getString("blast-tool.selectiongui.gui_name");

        Player player = (Player) e.getWhoClicked();
        String prefix = ChatColor.RED.toString() + ChatColor.BOLD + "AdvancedMiningTools " + ChatColor.DARK_RED.toString() + ChatColor.BOLD + ">> ";
        if (Extras.getItemInHand(player).getType() != XMaterial.AIR.parseMaterial()) {
            ItemStack tool = Extras.getItemInHand(player);
            if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', selectionGuiName))) {
                int radius = NBTEditor.getInt(tool, "Radius");
                e.setCancelled(true);
                String name = plugin.getMessages().getString("blast-tool.name");
                name = name.replace("{radius}", radius + "x" + radius + "x" + radius);
                name = ChatColor.translateAlternateColorCodes('&', name);
                if (tool != null && tool.hasItemMeta() && tool.getItemMeta().getDisplayName() != null && tool.getItemMeta().getDisplayName().equals(name)) {
                    Boolean trenchMode = NBTEditor.getBoolean(tool, "TrenchMode");
                    if (e.getRawSlot() == 11) {
                        if (trenchMode) {
                            String message = plugin.getMessages().getString("blast-tool.mode_already_selected");
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
                            player.playSound(player.getLocation(), XSound.ENTITY_VILLAGER_NO.parseSound(), 1.0F, 1.0F);
                        } else {
                            boolean unbreakable = NBTEditor.getBoolean(tool, "Break");
                            Material mat = tool.getType();
                            ItemStack newTool = Extras.blastPickaxe(radius, true, mat, unbreakable, tool.getDurability(), true);
                            int index = 0;
                            for (ItemStack items : player.getInventory().getContents()) {
                                if (tool.isSimilar(items)) {
                                    player.getInventory().setItem(index, newTool);
                                    player.updateInventory();
                                }
                                index++;
                            }
                            String message = plugin.getMessages().getString("blast-tool.mode_switched");
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
                            player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1.0F, 1.0F);
                        }
                    } else if (e.getRawSlot() == 15) {
                        if (!trenchMode) {
                            String message = plugin.getMessages().getString("blast-tool.mode_already_selected");
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
                            player.playSound(player.getLocation(), XSound.ENTITY_VILLAGER_NO.parseSound(), 1.0F, 1.0F);
                        } else {
                            boolean unbreakable = NBTEditor.getBoolean(tool, "Break");
                            Material mat = tool.getType();
                            ItemStack newTool = Extras.blastPickaxe(radius, false, mat, unbreakable, tool.getDurability(), true);
                            int index = 0;
                            for (ItemStack items : player.getInventory().getContents()) {
                                if (tool.isSimilar(items)) {
                                    player.getInventory().setItem(index, newTool);
                                    player.updateInventory();
                                }
                                index++;
                            }
                            String message = plugin.getMessages().getString("blast-tool.mode_switched");
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
                            player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1.0F, 1.0F);
                        }
                    }
                }
            }
        }
    }
}
