package com.commandblockguy.catalogue;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import com.Acrobot.Breeze.Utils.PriceUtil;

public class Shop {
	public Shop(Sign sign) {
		this(sign, sign.getLine(0));
	}
	public Shop(Sign sign, Player player) {
		this(sign, player.getName());
	}
	public Shop(Sign sign, String player) {
		this(sign.getLines()[3], new double[] {0.0, 0.0}, sign.getLocation(), sign.getLines()[0]);
		this.prices = checkPrices(sign.getLines());
	}
	public Shop(String item, double price[], Location pos, String playerName) {
		this.item = item;
		this.prices = price;
		this.pos = pos;
		this.playerName = playerName;
	}
	
	private String item;
	private double prices[];
	private Location pos;
	private String playerName;
	
	private double[] checkPrices(String lines[]) {
		double price[] = {0.0, 0.0};
		if(PriceUtil.hasBuyPrice(lines[2])) {
			price[0] = PriceUtil.getBuyPrice(lines[2]);
		}
		if(PriceUtil.hasSellPrice(lines[2])) {
			price[1] = PriceUtil.getSellPrice(lines[2]);
		}
		return price;
	}
	
	public String getItemName() {
		return item;
	}
	public double[] getPrices() {
		return prices;
	}
	public double getBuyPrice() {
		return prices[0];
	}
	public double getSellPrice() {
		return prices[1];
	}
	public Location getLocation() {
		return pos;
	}
	public Block getBlock() {
		return pos.getBlock();
	}
	public String getPlayerName() {
		return playerName;
	}
	
	public void register() {
		String[] args = {
					String.valueOf(pos.getBlockX()),
					String.valueOf(pos.getBlockY()),
					String.valueOf(pos.getBlockZ()),
					String.valueOf(prices[0]),
					String.valueOf(prices[1]),
					item,
					playerName,
					"None"
				};
		String query = "INSERT INTO shops (PosX, PosY, PosZ, BuyPrice, SellPrice, Itemtype, PlayerName, TownName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = Catalogue.connection.prepareStatement(query);
            for(int argument = 1; argument <= args.length; argument++) {
            	pstmt.setString(argument, args[argument - 1]);
            }
            pstmt.executeUpdate();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
	}
	public void unregister() {
		String[] args = {
					String.valueOf(pos.getBlockX()),
					String.valueOf(pos.getBlockY()),
					String.valueOf(pos.getBlockZ())
				};
		String query = "DELETE FROM shops WHERE PosX=? AND (PosY=? AND PosZ=?)";
        try {
            PreparedStatement pstmt = Catalogue.connection.prepareStatement(query);
            for(int argument = 1; argument <= args.length; argument++) {
            	pstmt.setString(argument, args[argument - 1]);
            }
            pstmt.executeUpdate();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
	}
}
