package com.commandblockguy.catalogue.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.commandblockguy.catalogue.gui.Icon;
import com.commandblockguy.catalogue.gui.SortedDisplay;
import com.commandblockguy.catalogue.gui.icons.ShopIcon;

public class CatalogueCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Bukkit.broadcastMessage("Command Run by: " + player.getDisplayName() + "!");
            ArrayList<Icon> icons = new ArrayList<Icon>();
            icons.add(new ShopIcon(new ItemStack(Material.DIRT), 0));
            for(int i = 1; i <= 12; i++) {
            	icons.add(new ShopIcon(new ItemStack(Material.STONE), 7));
            }
            SortedDisplay display = new SortedDisplay(0);
            display.addIcons(icons);
            display.display((Player) sender);
        }

        return true;
    }

}
