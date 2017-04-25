package com.commandblockguy.catalogue.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.commandblockguy.catalogue.gui.SortedDisplay;
import com.commandblockguy.catalogue.gui.comparators.PerItemBuyPriceComparator;
import com.commandblockguy.catalogue.gui.filters.Filter;
import com.commandblockguy.catalogue.gui.filters.FilterOperator;
import com.commandblockguy.catalogue.gui.filters.ItemFilter;
import com.commandblockguy.catalogue.gui.icons.ShopIcon;

public class CatalogueCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String value = args[0];
        	Filter filter = new ItemFilter(value, FilterOperator.EQUALS);
            //if(args.length <= 1)
            	//return false;
            //if(args[0] == "item")
            	//filter = new ItemFilter(value, FilterOperator.EQUALS);
            //if(args[0] == "player")
            	//filter = new PlayerFilter(value, FilterOperator.EQUALS);
            //if(args[0] == "town")
            	//filter = new TownFilter(value, FilterOperator.EQUALS);
            SortedDisplay display = new SortedDisplay(0);
            ArrayList<ShopIcon> icons = filter.getOutput(player.getWorld());
            icons.sort(new PerItemBuyPriceComparator());
            display.addIcons(icons);
            display.display(player);
        }

        return true;
    }

}
