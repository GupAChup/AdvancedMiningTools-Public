package com.chup.advancedminingtools.extras;

import com.chup.advancedminingtools.Extras;
import com.chup.advancedminingtools.Main;
import com.chup.advancedminingtools.xseries.XMaterial;
import me.vagdedes.spartan.api.PlayerViolationEvent;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class SpartanExecutor implements Listener {

    private Main plugin;

    public SpartanExecutor(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void violationEvent(PlayerViolationEvent e) {
        Boolean check = plugin.getConfig().getBoolean("general-settings.spartan_override");
        if (check) {
            Enums.HackType type = e.getHackType();
            if (type.equals(Enums.HackType.BlockReach) ||
                    type.equals(Enums.HackType.FastBreak) ||
                    type.equals(Enums.HackType.GhostHand) ||
                    type.equals(Enums.HackType.NoSwing)) {
                Player player = e.getPlayer();
                ItemStack tool = Extras.getItemInHand(player);
                if (tool.getType() != XMaterial.AIR.parseMaterial() && NBTEditor.contains(tool, "TrenchMode")) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
