package com.chup.advancedminingtools;

import com.chup.advancedminingtools.extras.NBTEditor;
import com.chup.advancedminingtools.extras.Version;
import com.chup.advancedminingtools.tools.ShockwavePickaxe.listeners.ShockwaveMineListener;
import com.chup.advancedminingtools.xseries.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Extras {

    private static Main plugin;

    public Extras(Main plugin) {
        this.plugin = plugin;
    }

    public static ItemStack getItemInHand(Player p) {
        if (Version.getCurrentVersion().isNewer(Version.v1_8_R3)) {
            return p.getInventory().getItemInMainHand();
        } else {
            return p.getItemInHand();
        }
    }

    public static void setItemInHand(Player p, ItemStack item) {
        if (Version.getCurrentVersion().isNewer(Version.v1_8_R3)) {
            p.getInventory().setItemInMainHand(item);
        } else {
            p.setItemInHand(item);
        }
    }

    public static ItemStack blastPickaxe(int radius, boolean mode, Material mat, Boolean unbreakable, Short durability, Boolean switchable) {
        ItemStack tool = new ItemStack(mat);
        ItemMeta toolMeta = tool.getItemMeta();
        String name = plugin.getMessages().getString("blast-tool.name");
        name = name.replace("{radius}", radius + "x" + radius + "x" + radius);
        toolMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> toolLore = new ArrayList<>();
        if (mode) {
            Iterator loreIterator = plugin.getMessages().getStringList("blast-tool.trench_lore").iterator();
            while (loreIterator.hasNext()) {
                String lore = (String) loreIterator.next();
                toolLore.add(ChatColor.translateAlternateColorCodes('&', lore));
            }
        } else {
            Iterator loreIterator = plugin.getMessages().getStringList("blast-tool.tray_lore").iterator();
            while (loreIterator.hasNext()) {
                String lore = (String) loreIterator.next();
                toolLore.add(ChatColor.translateAlternateColorCodes('&', lore));
            }
        }
        toolMeta.setLore(toolLore);
        toolMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        toolMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        toolMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        tool.setItemMeta(toolMeta);
        tool = NBTEditor.set(tool, mode, "TrenchMode");
        tool = NBTEditor.set(tool, radius, "Radius");
        tool = NBTEditor.set(tool, unbreakable, "Break");
        tool = NBTEditor.set(tool, switchable, "Switchable");

        Random toolRandom = new Random();
        int toolChoice = toolRandom.nextInt(1000000000);
        tool = NBTEditor.set(tool, toolChoice, "RandomToPreventSimilarity");

        tool.setDurability(durability);

        return tool;
    }

    public static ItemStack shockwavePickaxe(int radius, boolean mode, Material mat, Boolean unbreakable, Short durability, Boolean switchable) {
        ItemStack tool = new ItemStack(mat);
        ItemMeta toolMeta = tool.getItemMeta();
        String name = plugin.getMessages().getString("shockwave-tool.name");
        name = name.replace("{radius}", radius + "x" + radius + "x1");
        toolMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> toolLore = new ArrayList<>();
        if (mode) {
            Iterator loreIterator = plugin.getMessages().getStringList("shockwave-tool.trench_lore").iterator();
            while (loreIterator.hasNext()) {
                String lore = (String) loreIterator.next();
                toolLore.add(ChatColor.translateAlternateColorCodes('&', lore));
            }
        } else {
            Iterator loreIterator = plugin.getMessages().getStringList("shockwave-tool.tray_lore").iterator();
            while (loreIterator.hasNext()) {
                String lore = (String) loreIterator.next();
                toolLore.add(ChatColor.translateAlternateColorCodes('&', lore));
            }
        }
        toolMeta.setLore(toolLore);
        toolMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        toolMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        toolMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        tool.setItemMeta(toolMeta);
        tool = NBTEditor.set(tool, mode, "TrenchMode");
        tool = NBTEditor.set(tool, radius, "Radius");
        tool = NBTEditor.set(tool, unbreakable, "Break");
        tool = NBTEditor.set(tool, switchable, "Switchable");

        Random toolRandom = new Random();
        int toolChoice = toolRandom.nextInt(1000000000);
        tool = NBTEditor.set(tool, toolChoice, "RandomToPreventSimilarity");

        tool.setDurability(durability);

        return tool;
    }

    public static ItemStack getColor(String color) {
        if (color.equalsIgnoreCase("black")) {
            return XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
        } else if (color.equalsIgnoreCase("red")) {
            return XMaterial.RED_STAINED_GLASS_PANE.parseItem();
        } else if (color.equalsIgnoreCase("green")) {
            return XMaterial.GREEN_STAINED_GLASS_PANE.parseItem();
        } else if (color.equalsIgnoreCase("brown")) {
            return XMaterial.BROWN_STAINED_GLASS_PANE.parseItem();
        } else if (color.equalsIgnoreCase("blue")) {
            return XMaterial.BLUE_STAINED_GLASS_PANE.parseItem();
        } else if (color.equalsIgnoreCase("purple")) {
            return XMaterial.PURPLE_STAINED_GLASS_PANE.parseItem();
        } else if (color.equalsIgnoreCase("cyan")) {
            return XMaterial.CYAN_STAINED_GLASS_PANE.parseItem();
        } else if (color.equalsIgnoreCase("light gray") || color.equalsIgnoreCase("light_gray") || color.equalsIgnoreCase("lightgray")) {
            return XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE.parseItem();
        } else if (color.equalsIgnoreCase("gray")) {
            return XMaterial.GRAY_STAINED_GLASS_PANE.parseItem();
        } else if (color.equalsIgnoreCase("pink")) {
            return XMaterial.PINK_STAINED_GLASS_PANE.parseItem();
        } else if (color.equalsIgnoreCase("lime")) {
            return XMaterial.LIME_STAINED_GLASS_PANE.parseItem();
        } else if (color.equalsIgnoreCase("yellow")) {
            return XMaterial.YELLOW_STAINED_GLASS_PANE.parseItem();
        } else if (color.equalsIgnoreCase("light blue") || color.equalsIgnoreCase("light_blue") || color.equalsIgnoreCase("lightblue")) {
            return XMaterial.LIGHT_BLUE_STAINED_GLASS_PANE.parseItem();
        } else if (color.equalsIgnoreCase("magenta")) {
            return XMaterial.MAGENTA_STAINED_GLASS_PANE.parseItem();
        } else if (color.equalsIgnoreCase("orange")) {
            return XMaterial.ORANGE_STAINED_GLASS_PANE.parseItem();
        } else if (color.equalsIgnoreCase("white")) {
            return XMaterial.WHITE_STAINED_GLASS_PANE.parseItem();
        }
        return XMaterial.GLASS_PANE.parseItem();
    }

    public static void help(Player player) {
        Iterator helpIterator = plugin.getMessages().getStringList("help").iterator();
        while (helpIterator.hasNext()) {
            String helpMessage = (String) helpIterator.next();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', helpMessage));
        }
    }

    public static Boolean isNumberOdd(int num) {
        Boolean odd = true;
        if (num % 2 == 0) {
            odd = false;
        }
        return odd;
    }

    public static Material getPickaxeMaterial(String material) {
        Material mat = null;
        if(material.equalsIgnoreCase("WOOD")) {
            mat = XMaterial.WOODEN_PICKAXE.parseMaterial();
        } else if(material.equalsIgnoreCase("GOLD")) {
            mat = XMaterial.GOLDEN_PICKAXE.parseMaterial();
        } else if(material.equalsIgnoreCase("IRON")) {
            mat = XMaterial.IRON_PICKAXE.parseMaterial();
        } else if(material.equalsIgnoreCase("DIAMOND")) {
            mat = XMaterial.DIAMOND_PICKAXE.parseMaterial();
        } else if(material.equalsIgnoreCase("NETHERITE")) {
            mat = XMaterial.NETHERITE_PICKAXE.parseMaterial();
        }
        return  mat;
    }
}
