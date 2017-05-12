package com.commandblockguy.catalogue.gui.filters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import com.commandblockguy.catalogue.Catalogue;
import com.commandblockguy.catalogue.Shop;
import com.commandblockguy.catalogue.gui.icons.ShopIcon;

public abstract class Filter {
	protected String filterValue;
	protected String column;
	protected FilterOperator operator;
	protected String operatorString;
	protected Connection c = Catalogue.connection;
	
	public Filter(String value, FilterOperator operator) {
		filterValue = value;
		this.operator = operator;
		operatorString = operatorValue(operator);
	}
	
	public String getValue() {
		return filterValue;
	}
	public void setValue(String value) {
		filterValue = value;
	}
	public ArrayList<ShopIcon> getOutput(World world) {
		//Filters from ALL shops
		String query = "SELECT * FROM shops WHERE " + column + operatorValue(operator) + "?";
		ArrayList<ShopIcon> output = new ArrayList<ShopIcon>();
	    try {
	        PreparedStatement pstmt = c.prepareStatement(query);
	        pstmt.setString(1, filterValue);
	        ResultSet results = pstmt.executeQuery();
	        while(results.next()) {
	        	String item = results.getString("ItemType");
	        	int amount = results.getInt("Amount");
	        	UUID player = UUID.fromString(results.getString("PlayerName"));
	        	Location pos = new Location(world, results.getInt("PosX"), results.getInt("PosY"), results.getInt("PosZ"));
	        	double buyPrice = results.getDouble("BuyPrice");
	        	double buyUnit = buyPrice / amount;
	        	double sellPrice = results.getDouble("SellPrice");
	        	double sellUnit = sellPrice / amount;
	        	String townName = results.getString("TownName");
	        	Shop shop = new Shop(item, amount, buyPrice, sellPrice, pos, player);
	        	ShopIcon icon = new ShopIcon(shop);
	        	icon.setAmount(amount);
	        	String playerName = Bukkit.getOfflinePlayer(player).getName();
	        	if(playerName == null)
	        		playerName = "Server";
	        	icon.addLore("Player: " + playerName);
	        	if(buyPrice > 0) {
	        		icon.addLore("Buy Price: " + buyPrice);
	        		if(amount != 1)
	        			icon.addLore("(" + buyUnit + " per item)");
	        	}
	        	if(sellPrice > 0) {
	        		icon.addLore("Sell Price: " + sellPrice);
	        		if(amount != 1)
	        			icon.addLore("(" + sellUnit + " per item)");
	        	}
	        	icon.addLore("Pos: " + pos.getBlockX() + ", " + pos.getBlockY() + ", " + pos.getBlockZ());
	        	if(townName == null)
	        		townName = "Wilderness";
	        	icon.addLore("Town: " + townName);
	        	output.add(icon);
	        }
	        return output;
	    } catch (SQLException e) {
	        	e.printStackTrace();
	    }
		return null;
	}
	
	public abstract String value(ShopIcon icon);
	
	public ArrayList<ShopIcon> filter(ArrayList<ShopIcon> icons, World world) {
		ArrayList<ShopIcon> output = icons;
		ArrayList<ShopIcon> toRemove = new ArrayList<ShopIcon>();
		for(ShopIcon icon : output) {
			String shopValue = this.value(icon);
			boolean isInt = true;
			try {
				Integer.parseInt(shopValue);
			} catch(NumberFormatException exception) {
				isInt = false;
			}
			if(isInt)
				shopValue += ".0";
			switch (operatorString) {
			default:
			case "=":
				if (!shopValue.equalsIgnoreCase(this.filterValue)) {
					toRemove.add(icon);
				}
				break;
			case "<>":
				if (shopValue.equalsIgnoreCase(this.filterValue))
					toRemove.add(icon);
				break;
			case "<":
				if (!(Double.parseDouble(shopValue) < Double.parseDouble(this.filterValue)))
					toRemove.add(icon);
				break;
			case ">":
				if (!(Double.parseDouble(shopValue) > Double.parseDouble(this.filterValue)))
					toRemove.add(icon);
				break;
			case "<=":
				if (!(Double.parseDouble(shopValue) <= Double.parseDouble(this.filterValue)))
					toRemove.add(icon);
				break;
			case ">=":
				if (!(Double.parseDouble(shopValue) >= Double.parseDouble(this.filterValue)))
					toRemove.add(icon);
				break;
			case "none":
				break;
			}
		}
		output.removeAll(toRemove);
		return output;
	}
	public static String operatorValue(FilterOperator operator) {
		String value;
		switch (operator) {
		default:
		case EQUALS:
			value = "=";
			break;
		case NOT_EQUAL:
			value = "<>";
			break;
		case LESS_THAN:
			value = "<";
			break;
		case GREATER_THAN:
			value = ">";
			break;
		case LESS_THAN_EQUAL_TO:
			value = "<=";
			break;
		case GREATER_THAN_EQUAL_TO:
			value = ">=";
			break;
		}
		return value;
	}
}
