package com.commandblockguy.catalogue.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.commandblockguy.catalogue.Catalogue;
import com.commandblockguy.catalogue.gui.SortedDisplay;
import com.commandblockguy.catalogue.gui.comparators.AmountComparator;
import com.commandblockguy.catalogue.gui.comparators.BuyPriceComparator;
import com.commandblockguy.catalogue.gui.comparators.ItemNameComparator;
import com.commandblockguy.catalogue.gui.comparators.PerItemBuyPriceComparator;
import com.commandblockguy.catalogue.gui.comparators.PerItemSellPriceComparator;
import com.commandblockguy.catalogue.gui.comparators.PlayerNameComparator;
import com.commandblockguy.catalogue.gui.comparators.SellPriceComparator;
import com.commandblockguy.catalogue.gui.comparators.TownNameComparator;
import com.commandblockguy.catalogue.gui.filters.AmountFilter;
import com.commandblockguy.catalogue.gui.filters.BuyPriceFilter;
import com.commandblockguy.catalogue.gui.filters.Filter;
import com.commandblockguy.catalogue.gui.filters.FilterOperator;
import com.commandblockguy.catalogue.gui.filters.ItemFilter;
import com.commandblockguy.catalogue.gui.filters.NoFilter;
import com.commandblockguy.catalogue.gui.filters.PlayerFilter;
import com.commandblockguy.catalogue.gui.filters.SellPriceFilter;
import com.commandblockguy.catalogue.gui.filters.TownFilter;
import com.commandblockguy.catalogue.gui.icons.ShopIcon;

public class CatalogueCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String title = Catalogue.getPlugin().localization.getString("commands.catalogue.windowtitle");
            //check if the player has the permissions to run the command
            if (player.hasPermission("catalogue.gui")) {
            	//in case multiple filters are applied, a arraylist is used
        		ArrayList<Filter> filters = new ArrayList<Filter>();
        		ArrayList<Filter> additiveFilters = new ArrayList<Filter>();
        		Comparator<ShopIcon> sorter = null;
        		//sort order will be inverted if des or descending is added
        		boolean descending = false;
        		if(args.length == 0) {
        			//run with no args, showing all shops
        			additiveFilters.add(new NoFilter());
        		} else if(args[0].equals("browse") && args.length == 1) {
        			//run wit browse arg, show all shops
        			filters.add(new NoFilter());
        		} else if(args.length == 1) {
        			//run with one arg, the name of an item
        			String itemName = args[0].replace("_", " ");
        			title += ": " + itemName; //set the window title
        			String[] ignore = {"browse", "search", "filter", "afilter", "sort"};
        			if(!(Arrays.asList(ignore).contains(args[0])))
        				additiveFilters.add(new ItemFilter(itemName, FilterOperator.EQUALS));
        		} else {
        			//run with an item name plus filters and sorters
        			String itemName = args[0].replace("_", " ");
        			title += ": " + itemName; //set the window title
        			String[] ignore = {"browse", "search", "filter", "afilter", "sort"};
        			if(!(Arrays.asList(ignore).contains(args[0])))
        				filters.add(new ItemFilter(itemName, FilterOperator.EQUALS));
        			//look at each argument after the item name
        			for(int i = 0; i < args.length; i++) {
        				
        				if(args[i].equals("sort") && sorter == null) {
        					if(args.length < i + 2)
        						return false;
        					switch(args[i + 1].toLowerCase()) {
        					case "amount":
        						sorter = new AmountComparator();
        						break;
        					case "buyprice":
        						sorter = new BuyPriceComparator();
        						break;
        					case "item":
        						sorter = new ItemNameComparator();
        						break;
        					case "peritembuy":
        						sorter = new PerItemBuyPriceComparator();
        						break;
        					case "peritemsell":
        						sorter = new PerItemSellPriceComparator();
        						break;
        					case "player":
        						sorter = new PlayerNameComparator();
        						break;
        					case "sellprice":
        						sorter = new SellPriceComparator();
        						break;
        					case "town":
        						sorter = new TownNameComparator();
        					default:
        						break;
        					}
        					
        					//sort order will be inverted if des or descending is added
        					try {
        						switch(args[i + 2].toLowerCase()) {
        						default:
        							descending = false;
        							break;
        						case "des":
        							descending = true;
        							break;
        						case "descending":
        							descending = true;
        							break;
        						}
        					} catch(ArrayIndexOutOfBoundsException exception) {
        						//des is not specified
        						descending = false;
        					}
        				}
        				
        				else if(args[i].equals("filter") || args[i].equals("afilter")) {
        					//if operator is null, a default is assigned
        					FilterOperator operator = null;
        					boolean additive = false;
        					if(args[i].equals("afilter")) {
        						additive = true;
        					}
        					if(args.length < i + 2) {
        						//filters need at least a type and a value
        						return false;
        					}
        					try {
        						//we try to find a third argument to the filter as an operator
        						switch (args[i + 3].toUpperCase()) {
        						default:
        						case "EQUALS":
        						case "E":
        						case "EXACTLY":
        						case "=":
        							operator = FilterOperator.EQUALS;
        							break;
        						case "NOT_EQUAL":
        						case "NE":
        						case "NOT":
        						case "!=":
        						case "<>":
        							operator = FilterOperator.NOT_EQUAL;
        							break;
        						case "LESS_THAN":
        						case "LT":
        						case "UNDER":
        						case "<":
        							operator = FilterOperator.LESS_THAN;
        							break;
        						case "GREATER_THAN":
        						case "GT":
        						case "OVER":
        						case ">":
        							operator = FilterOperator.GREATER_THAN;
        							break;
        						case "LESS_THAN_EQUAL_TO":
        						case "LTE":
        						case "<=":
        							operator = FilterOperator.LESS_THAN_EQUAL_TO;
        							break;
        						case "GREATER_THAN_EQUAL_TO":
        						case "GTE":
        						case ">=":
        							operator = FilterOperator.GREATER_THAN_EQUAL_TO;
        							break;
        						}
        					} catch(ArrayIndexOutOfBoundsException exception) {
        						//we can ignore this, it means that the operator was omitted
        					}
        					Filter toAdd = new NoFilter();
        					switch(args[i + 1].toLowerCase()) {
        					//cases for each type of filter
        					case "amount":
        						//default operator
        						if(operator == null) operator = FilterOperator.GREATER_THAN_EQUAL_TO;
        						toAdd = new AmountFilter(Double.parseDouble(args[i + 2]), operator);
        						break;
        					case "buyprice":
        					case "buy":
        					case "price":
        						if(operator == null) operator = FilterOperator.LESS_THAN_EQUAL_TO;
        						toAdd = new BuyPriceFilter(Double.parseDouble(args[i + 2]), operator);
        						break;
        					case "sellprice":
        					case "sell":
        					case "value":
        						if(operator == null) operator = FilterOperator.LESS_THAN_EQUAL_TO;
        						toAdd = new SellPriceFilter(Double.parseDouble(args[i + 2]), operator);
        						break;
        					case "player":
        						if(operator == null) operator = FilterOperator.EQUALS;
        						toAdd = new PlayerFilter(args[i + 2], operator);
        						break;
        					case "town":
        						if(operator == null) operator = FilterOperator.EQUALS;
        						toAdd = new TownFilter(args[i + 2], operator);
        					default:
        						break;
        					}
        					if(additive) {
        						additiveFilters.add(toAdd);
        					} else {
        						filters.add(toAdd);
        					}
        				}
        			}
        		}
        		
            	SortedDisplay display = new SortedDisplay(0, title); //create the display with no scroll
            	ArrayList<ShopIcon> icons = new ArrayList<ShopIcon>(); //arraylist of icons to be filtered
            	if(additiveFilters.size() == 0 && filters.size() > 0) {
            		sender.sendMessage("Condition Met!");
            		additiveFilters.add(filters.get(0));
            		filters.remove(0);
            	}
            	for(Filter filter : additiveFilters) {
            		sender.sendMessage("Additive Filter " + filter.getValue());
            		icons.addAll(filter.getOutput(player.getWorld()));
            	}
            	for(Filter filter : filters) {
            		sender.sendMessage("Subractive Filter " + filter.getValue());
            		filter.filter(icons, player.getWorld());
            	}
            	
            	if(sorter == null)
            		//make sure we sort by something
            		sorter = new ItemNameComparator();
            	icons.sort(sorter); //sort the list using a comparator
            	
            	if(descending)
            		Collections.reverse(icons); //sort order will be inverted if des is added after a sort
            	
            	display.addIcons(icons); //populate the display with icons
            	display.display(player);
            }
        }

        return true;
    }

}
