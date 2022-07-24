package com.chup.advancedminingtools.tools.BlastPickaxe.listeners;

import com.chup.advancedminingtools.Extras;
import com.chup.advancedminingtools.Main;
import com.chup.advancedminingtools.extras.NBTEditor;
import com.chup.advancedminingtools.xseries.XMaterial;
import com.chup.advancedminingtools.xseries.XSound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlastMineListener implements Listener {

    private static Main plugin;

    public BlastMineListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBreak(BlockBreakEvent e) {

        if(Main.serverChecks.contains(e)) {return;}

        Player player = e.getPlayer();
        Location loc = e.getBlock().getLocation();
        if (Extras.getItemInHand(player).getType() != XMaterial.AIR.parseMaterial()) {
            ItemStack tool = Extras.getItemInHand(player);
            int radius = NBTEditor.getInt(tool, "Radius");
            String name = plugin.getMessages().getString("blast-tool.name");
            name = name.replace("{radius}", radius + "x" + radius + "x" + radius);
            name = ChatColor.translateAlternateColorCodes('&', name);
            if (tool != null && tool.hasItemMeta() && tool.getItemMeta().getDisplayName() != null && tool.getItemMeta().getDisplayName().equals(name)) {
                e.setCancelled(true);
                Boolean trenchMode = NBTEditor.getBoolean(tool, "TrenchMode");
                List<Location> locations = new ArrayList<>();
                for (int x = (int) -(Math.floor(radius / 2)); x < Math.ceil(radius / 2) + 1; x++) {
                    for (int y = (int) -(Math.floor(radius / 2)); y < Math.ceil(radius / 2) + 1; y++) {
                        for (int z = (int) -(Math.floor(radius / 2)); z < Math.ceil(radius / 2 + 1); z++) {
                            Location nextLoc = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y, loc.getZ() + z);
                            locations.add(nextLoc);
                        }
                    }
                }
                for (Location location : locations) {
                    if (trenchMode) {
                        if (location.getBlock().getType() != XMaterial.BEDROCK.parseMaterial() &&
                                location.getBlock().getType() != XMaterial.END_PORTAL_FRAME.parseMaterial() &&
                                location.getBlock().getType() != XMaterial.END_PORTAL.parseMaterial() &&
                                location.getBlock().getType() != XMaterial.SPAWNER.parseMaterial() &&
                                location.getBlock().getType() != XMaterial.CHEST.parseMaterial() &&
                                location.getBlock().getType() != XMaterial.TRAPPED_CHEST.parseMaterial()) {
                            BlockBreakEvent breakEvent = new BlockBreakEvent(location.getBlock(), player);
                            Main.serverChecks.add(breakEvent);
                            Bukkit.getServer().getPluginManager().callEvent(breakEvent);
                            Main.serverChecks.remove(breakEvent);
                            if(!breakEvent.isCancelled()) {
                                Collection<ItemStack> drops = location.getBlock().getDrops();
                                location.getBlock().setType(XMaterial.AIR.parseMaterial());
                                for (ItemStack item : drops) {
                                    int playerSlots = player.getInventory().firstEmpty();
                                    if (playerSlots == -1) {
                                        location.getWorld().dropItem(location, item);
                                    } else {
                                        player.getInventory().addItem(item);
                                    }
                                }
                            }
                        }
                    } else {
                        if (location.getBlock().getType() == XMaterial.NETHERRACK.parseMaterial()) {
                            Collection<ItemStack> drops = location.getBlock().getDrops();
                            location.getBlock().setType(XMaterial.AIR.parseMaterial());
                            for (ItemStack item : drops) {
                                int playerSlots = player.getInventory().firstEmpty();
                                if (playerSlots == -1) {
                                    location.getWorld().dropItem(location, item);
                                } else {
                                    player.getInventory().addItem(item);
                                }
                            }
                        }
                    }
                }
                boolean unbreakable = NBTEditor.getBoolean(tool, "Break");
                if (unbreakable) {
                    tool.setDurability((short) 0);
                    Extras.setItemInHand(player, tool);
                } else {
                    tool.setDurability((short) (tool.getDurability() + 1));
                    if (tool.getDurability() > tool.getType().getMaxDurability()) {
                        Extras.setItemInHand(player, null);
                        player.playSound(player.getLocation(), XSound.ENTITY_ITEM_BREAK.parseSound(), 1F, 1F);
                    }
                }
            }
        }
    }
}
