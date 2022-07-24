package com.chup.advancedminingtools;

import com.chup.advancedminingtools.extras.SpartanExecutor;
import com.chup.advancedminingtools.extras.antirepair.AnvilListener;
import com.chup.advancedminingtools.extras.antirepair.RepairClickListener;
import com.chup.advancedminingtools.extras.antirepair.RepairListener;
import com.chup.advancedminingtools.tools.BlastPickaxe.BlastConfig;
import com.chup.advancedminingtools.tools.BlastPickaxe.listeners.BlastClickListener;
import com.chup.advancedminingtools.tools.BlastPickaxe.listeners.BlastRightClickListener;
import com.chup.advancedminingtools.configuration.ConfigManager;
import com.chup.advancedminingtools.extras.Metrics;
import com.chup.advancedminingtools.tools.BlastPickaxe.listeners.BlastMineListener;
import com.chup.advancedminingtools.tools.ShockwavePickaxe.ShockwaveConfig;
import com.chup.advancedminingtools.tools.ShockwavePickaxe.listeners.ShockwaveClickListener;
import com.chup.advancedminingtools.tools.ShockwavePickaxe.listeners.ShockwaveMineListener;
import com.chup.advancedminingtools.tools.ShockwavePickaxe.listeners.ShockwaveRightClickListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    public ConfigManager configManager;
    public static List<BlockBreakEvent> serverChecks = new ArrayList<>();

    @Override
    public void onEnable() {

        System.out.println("AdvancedMiningTools >> Plugin successfully enabled.");

        this.configManager = new ConfigManager(this);
        this.configManager.load("messages.yml");
        this.configManager.save("messages.yml");

        new BlastConfig(this);
        new ShockwaveConfig(this);
        new Extras(this);

        Metrics metrics = new Metrics(this, 11068);

        getCommand("advancedminingtools").setExecutor(new TrenchPickaxeExecutor(this));
        Bukkit.getPluginManager().registerEvents(new BlastMineListener(this), this);
        Bukkit.getPluginManager().registerEvents(new BlastRightClickListener(this), this);
        Bukkit.getPluginManager().registerEvents(new BlastClickListener(this), this);

        Bukkit.getPluginManager().registerEvents(new ShockwaveMineListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ShockwaveRightClickListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ShockwaveClickListener(this), this);

        Bukkit.getPluginManager().registerEvents(new RepairListener(this), this);
        Bukkit.getPluginManager().registerEvents(new RepairClickListener(this), this);
        Bukkit.getPluginManager().registerEvents(new AnvilListener(), this);
        Bukkit.getPluginManager().registerEvents(new SpartanExecutor(this), this);

    }

    public FileConfiguration getMessages() {
        return this.configManager.get("messages.yml");
    }
}
