package com.chup.advancedminingtools;

import com.chup.advancedminingtools.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;

public class TrenchPickaxeExecutor implements CommandExecutor {

    private final Main plugin;

    public TrenchPickaxeExecutor(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = ChatColor.RED.toString() + ChatColor.BOLD + "AdvancedMiningTools " + ChatColor.DARK_RED.toString() + ChatColor.BOLD + ">> ";
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp() || player.hasPermission("advancedminingtools.admin")) {
                if (args.length == 1 && (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl"))) {
                    plugin.configManager.reload("messages.yml");
                    plugin.configManager.save("messages.yml");
                    plugin.reloadConfig();
                    String message = ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("reload"));
                    player.sendMessage(prefix + message);
                } else if (args.length == 5 && args[0].equalsIgnoreCase("give")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target != null) {
                        try {
                            if (args[2].equalsIgnoreCase("blast")) {
                                int radius = Integer.parseInt(args[3]);
                                int amount = Integer.parseInt(args[4]);
                                if (Extras.isNumberOdd(radius)) {
                                    for (int i = 0; i < amount; i++) {
                                        ItemStack tool = Extras.blastPickaxe(radius, true, XMaterial.DIAMOND_PICKAXE.parseMaterial(), true, (short) 0, true);
                                        int invSlots = target.getInventory().firstEmpty();
                                        if (invSlots == -1) {
                                            target.getWorld().dropItem(target.getLocation(), tool);
                                        } else {
                                            target.getInventory().addItem(tool);
                                        }
                                    }
                                    String message = plugin.getMessages().getString("blast-tool.given_blast_pickaxe");
                                    message = message.replace("{player}", target.getName());
                                    message = message.replace("{amount}", Integer.toString(amount));
                                    player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                                } else {
                                    String message = plugin.getMessages().getString("invalid-radius");
                                    player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                                }
                            } else if (args[2].equalsIgnoreCase("shockwave")) {
                                int radius = Integer.parseInt(args[3]);
                                int amount = Integer.parseInt(args[4]);
                                if (Extras.isNumberOdd(radius)) {
                                    for (int i = 0; i < amount; i++) {
                                        ItemStack tool = Extras.shockwavePickaxe(radius, true, XMaterial.DIAMOND_PICKAXE.parseMaterial(), true, (short) 0, true);
                                        int invSlots = target.getInventory().firstEmpty();
                                        if (invSlots == -1) {
                                            target.getWorld().dropItem(target.getLocation(), tool);
                                        } else {
                                            target.getInventory().addItem(tool);
                                        }
                                    }
                                    String message = plugin.getMessages().getString("shockwave-tool.given_shockwave_pickaxe");
                                    message = message.replace("{player}", target.getName());
                                    message = message.replace("{amount}", Integer.toString(amount));
                                    player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                                } else {
                                    String message = plugin.getMessages().getString("invalid-radius");
                                    player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                                }
                            } else if (args[2].equalsIgnoreCase("custom")) {
                                int id = Integer.parseInt(args[3]);
                                int amount = Integer.parseInt(args[4]);
                                if (!plugin.getConfig().contains("tools.custom-tools." + id)) {
                                    String message = plugin.getMessages().getString("custom-tools.invalid_id");
                                    player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                                } else {
                                    int radius = plugin.getConfig().getInt("tools.custom-tools." + id + ".radius");
                                    String type = plugin.getConfig().getString("tools.custom-tools." + id + ".type");
                                    if (type.equalsIgnoreCase("blast") || type.equalsIgnoreCase("shockwave")) {
                                        if (Extras.isNumberOdd(radius)) {
                                            Material mat = Extras.getPickaxeMaterial(plugin.getConfig().getString("tools.custom-tools." + id + ".material"));
                                            if (mat != null) {
                                                Boolean unbreakable = plugin.getConfig().getBoolean("tools.custom-tools." + id + ".unbreakable");
                                                for (int i = 0; i < amount; i++) {
                                                    ItemStack tool = null;
                                                    Boolean switchable = plugin.getConfig().getBoolean("tools.custom-tools." + id + ".switchable");
                                                    if (type.equalsIgnoreCase("blast")) {
                                                        tool = Extras.blastPickaxe(radius, true, mat, unbreakable, (short) 0, switchable);
                                                    } else if (type.equalsIgnoreCase("shockwave")) {
                                                        tool = Extras.shockwavePickaxe(radius, true, mat, unbreakable, (short) 0, switchable);
                                                    }
                                                    int invSlots = target.getInventory().firstEmpty();
                                                    if (invSlots == -1) {
                                                        target.getWorld().dropItem(target.getLocation(), tool);
                                                    } else {
                                                        target.getInventory().addItem(tool);
                                                    }
                                                }
                                                String message = plugin.getMessages().getString("custom-tools.given_custom_pickaxe");
                                                message = message.replace("{player}", target.getName());
                                                message = message.replace("{amount}", Integer.toString(amount));
                                                player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                                            } else {
                                                String message = plugin.getMessages().getString("custom-tools.invalid_material");
                                                player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                                            }
                                        } else {
                                            String message = plugin.getMessages().getString("custom-tools.invalid_radius");
                                            player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                                        }
                                    } else {
                                        String message = plugin.getMessages().getString("custom-tools.invalid_type");
                                        player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                                    }
                                }
                            } else {
                                Extras.help(player);
                            }
                        } catch (NumberFormatException e) {
                            String message = plugin.getMessages().getString("invalid-number");
                            player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                        }
                    } else {
                        String message = plugin.getMessages().getString("invalid-user");
                        player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                    }
                } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
                    Extras.help(player);
                } else {
                    Extras.help(player);
                }
            } else {
                String message = plugin.getMessages().getString("no-permission");
                player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
            }
        } else {
            if (args.length == 1 && (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl"))) {
                plugin.configManager.reload("messages.yml");
                plugin.configManager.save("messages.yml");
                plugin.reloadConfig();
                String message = ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("reload"));
                Bukkit.getConsoleSender().sendMessage(prefix + message);
            } else if (args.length == 5 && args[0].equalsIgnoreCase("give")) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    try {
                        if (args[2].equalsIgnoreCase("blast")) {
                            int radius = Integer.parseInt(args[3]);
                            int amount = Integer.parseInt(args[4]);
                            if (Extras.isNumberOdd(radius)) {
                                for (int i = 0; i < amount; i++) {
                                    ItemStack tool = Extras.blastPickaxe(radius, true, XMaterial.DIAMOND_PICKAXE.parseMaterial(), true, (short) 0, true);
                                    int invSlots = target.getInventory().firstEmpty();
                                    if (invSlots == -1) {
                                        target.getWorld().dropItem(target.getLocation(), tool);
                                    } else {
                                        target.getInventory().addItem(tool);
                                    }
                                }
                                String message = plugin.getMessages().getString("blast-tool.given_blast_pickaxe");
                                message = message.replace("{player}", target.getName());
                                message = message.replace("{amount}", Integer.toString(amount));
                                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                            } else {
                                String message = plugin.getMessages().getString("invalid-radius");
                                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                            }
                        } else if (args[2].equalsIgnoreCase("shockwave")) {
                            int radius = Integer.parseInt(args[3]);
                            int amount = Integer.parseInt(args[4]);
                            if (Extras.isNumberOdd(radius)) {
                                for (int i = 0; i < amount; i++) {
                                    ItemStack tool = Extras.shockwavePickaxe(radius, true, XMaterial.DIAMOND_PICKAXE.parseMaterial(), true, (short) 0, true);
                                    int invSlots = target.getInventory().firstEmpty();
                                    if (invSlots == -1) {
                                        target.getWorld().dropItem(target.getLocation(), tool);
                                    } else {
                                        target.getInventory().addItem(tool);
                                    }
                                }
                                String message = plugin.getMessages().getString("shockwave-tool.given_shockwave_pickaxe");
                                message = message.replace("{player}", target.getName());
                                message = message.replace("{amount}", Integer.toString(amount));
                                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                            } else {
                                String message = plugin.getMessages().getString("invalid-radius");
                                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                            }
                        } else if (args[2].equalsIgnoreCase("custom")) {
                            int id = Integer.parseInt(args[3]);
                            int amount = Integer.parseInt(args[4]);
                            if (!plugin.getConfig().contains("tools.custom-tools." + id)) {
                                String message = plugin.getMessages().getString("custom-tools.invalid_id");
                                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                            } else {
                                int radius = plugin.getConfig().getInt("tools.custom-tools." + id + ".radius");
                                String type = plugin.getConfig().getString("tools.custom-tools." + id + ".type");
                                if (type.equalsIgnoreCase("blast") || type.equalsIgnoreCase("shockwave")) {
                                    if (Extras.isNumberOdd(radius)) {
                                        Material mat = Extras.getPickaxeMaterial(plugin.getConfig().getString("tools.custom-tools." + id + ".material"));
                                        if (mat != null) {
                                            Boolean unbreakable = plugin.getConfig().getBoolean("tools.custom-tools." + id + ".unbreakable");
                                            for (int i = 0; i < amount; i++) {
                                                ItemStack tool = null;
                                                Boolean switchable = plugin.getConfig().getBoolean("tools.custom-tools." + id + ".switchable");
                                                if (type.equalsIgnoreCase("blast")) {
                                                    tool = Extras.blastPickaxe(radius, true, mat, unbreakable, (short) 0, switchable);
                                                } else if (type.equalsIgnoreCase("shockwave")) {
                                                    tool = Extras.shockwavePickaxe(radius, true, mat, unbreakable, (short) 0, switchable);
                                                }
                                                int invSlots = target.getInventory().firstEmpty();
                                                if (invSlots == -1) {
                                                    target.getWorld().dropItem(target.getLocation(), tool);
                                                } else {
                                                    target.getInventory().addItem(tool);
                                                }
                                            }
                                            String message = plugin.getMessages().getString("custom-tools.given_custom_pickaxe");
                                            message = message.replace("{player}", target.getName());
                                            message = message.replace("{amount}", Integer.toString(amount));
                                            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                                        } else {
                                            String message = plugin.getMessages().getString("custom-tools.invalid_material");
                                            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                                        }
                                    } else {
                                        String message = plugin.getMessages().getString("custom-tools.invalid_radius");
                                        Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                                    }
                                } else {
                                    String message = plugin.getMessages().getString("custom-tools.invalid_type");
                                    Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                                }
                            }
                        } else {
                            Iterator helpIterator = plugin.getMessages().getStringList("help").iterator();
                            while (helpIterator.hasNext()) {
                                String helpMessage = (String) helpIterator.next();
                                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', helpMessage));
                            }
                        }
                    } catch (NumberFormatException e) {
                        String message = plugin.getMessages().getString("invalid-number");
                        Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                    }

                } else {
                    String message = plugin.getMessages().getString("invalid-user");
                    Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', message));
                }

            } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
                Iterator helpIterator = plugin.getMessages().getStringList("help").iterator();
                while (helpIterator.hasNext()) {
                    String helpMessage = (String) helpIterator.next();
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', helpMessage));
                }
            } else {
                Iterator helpIterator = plugin.getMessages().getStringList("help").iterator();
                while (helpIterator.hasNext()) {
                    String helpMessage = (String) helpIterator.next();
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', helpMessage));
                }
            }
        }
        return false;
    }
}
