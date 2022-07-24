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
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class RepairClickListener implements Listener {

    private Main plugin;

    public RepairClickListener(Main plugin) {
        this.plugin = plugin;
    }

    String prefix = ChatColor.RED.toString() + ChatColor.BOLD + "AdvancedMiningTools " + ChatColor.DARK_RED.toString() + ChatColor.BOLD + ">> ";
    @EventHandler(priority = EventPriority.HIGH)
    public void onRepairClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock().getType() == XMaterial.IRON_BLOCK.parseMaterial() || e.getClickedBlock().getType() == XMaterial.GOLD_BLOCK.parseMaterial()) {
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
    }
}
