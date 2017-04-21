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
import com.commandblockguy.catalogue.gui.Icon;
import com.commandblockguy.catalogue.gui.icons.ShopIcon;

public abstract class Filter {
	protected String filterValue;
	protected String column;
	protected FilterOperator operator;
	protected Connection c = Catalogue.connection;
	
	public Filter(String value, FilterOperator operator) {
		filterValue = value;
		this.operator = operator;
	}
	
	public String getValue() {
		return filterValue;
	}
	public void setValue(String value) {
		filterValue = value;
	}
	public ArrayList<Icon> getOutput(World world) {
		String query = "SELECT * FROM shops WHERE " + column + operatorValue(operator) + "?";
		ArrayList<Icon> output = new ArrayList<Icon>();
	    try {
	        PreparedStatement pstmt = c.prepareStatement(query);
	        pstmt.setString(1, filterValue);
	        ResultSet results = pstmt.executeQuery();
	        while(results.next()) {
	        	String item = results.getString("ItemType");
	        	UUID player = UUID.fromString(results.getString("PlayerName"));
	        	Location pos = new Location(world, results.getInt("PosX"), results.getInt("PosY"), results.getInt("PosZ"));
	        	double buyPrice = results.getDouble("BuyPrice");
	        	double sellPrice = results.getDouble("SellPrice");
	        	Shop shop = new Shop(item, buyPrice, sellPrice, pos, player);
	        	ShopIcon icon = new ShopIcon(shop);
	        	icon.addLore("Player: " + Bukkit.getOfflinePlayer(player).getName());
	        	icon.addLore("Buy Price: " + buyPrice);
	        	icon.addLore("Sell Price: " + sellPrice);
	        	icon.addLore("Pos: " + pos.getBlockX() + ", " + pos.getBlockY() + ", " + pos.getBlockZ());
	        	output.add(icon);
	        }
	        return output;
	    } catch (SQLException e) {
	        	e.printStackTrace();
	    }
		return null;
	}
	public static String operatorValue(FilterOperator operator) {
		String value;
		switch (operator) {
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
		default:
			value = "=";
			break;
		}
		return value;
	}
}
