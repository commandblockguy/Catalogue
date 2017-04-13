package com.commandblockguy.catalogue.commands;

import org.bukkit.Bukkit;
//import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
//import org.bukkit.inventory.ItemStack;

//import com.commandblockguy.catalogue.gui.Icon;
//import com.commandblockguy.catalogue.gui.ShopIcon;
import com.commandblockguy.catalogue.gui.SortedDisplay;

public class CatalogueCommand implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Bukkit.broadcastMessage("Command Run by: " + player.getDisplayName() + "!");
            //Icon[] icons = {new ShopIcon(new ItemStack(Material.DIRT), 0)};
            SortedDisplay display = new SortedDisplay();
            display.display((Player) sender);
        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }

}
