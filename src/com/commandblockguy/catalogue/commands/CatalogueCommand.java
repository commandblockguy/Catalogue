package com.commandblockguy.catalogue.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.commandblockguy.catalogue.gui.Icon;
import com.commandblockguy.catalogue.gui.SortedDisplay;
import com.commandblockguy.catalogue.gui.filters.FilterOperator;
import com.commandblockguy.catalogue.gui.filters.ItemFilter;

public class CatalogueCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String itemName = "Dirt";
            if(args.length != 0)
            	itemName = args[0];
            ItemFilter filter = new ItemFilter(itemName, FilterOperator.EQUALS);
            SortedDisplay display = new SortedDisplay(0);
            ArrayList<Icon> icons = filter.getOutput(player.getWorld());
            display.addIcons(icons);
            display.display(player);
        }

        return true;
    }

}
