package com.commandblockguy.catalogue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import com.Acrobot.Breeze.Utils.PriceUtil;
import com.palmergames.bukkit.towny.object.TownyUniverse;

public class Shop {
	public Shop(Sign sign) {
		this(sign, sign.getLine(0));
	}
	public Shop(Sign sign, Player player) {
		this(sign, player.getName());
	}
	public Shop(Sign sign, String player) {
		this(sign.getLines()[3], Integer.valueOf(sign.getLines()[1]), new double[] {0.0, 0.0}, sign.getLocation(), sign.getLines()[0]);
		this.prices = checkPrices(sign.getLines());
	}
	@SuppressWarnings("deprecation")
	public Shop(String item, int amount, double price[], Location pos, String playerName) {
		this.item = item;
		this.prices = price;
		this.pos = pos;
		this.playerName = playerName;
		this.amount = amount;
		player = Bukkit.getOfflinePlayer(playerName);
		uuid = player.getUniqueId();
		townName = TownyUniverse.getTownName(pos);
		if (townName == null)
			townName = "Wilderness";
	}
	public Shop(String item, int amount, double buyPrice, double sellPrice, Location pos, UUID uuid) {
		this.item = item;
		this.prices[0] = buyPrice;
		this.prices[1] = sellPrice;
		this.pos = pos;
		this.uuid = uuid;
		this.amount = amount;
		player = Bukkit.getOfflinePlayer(uuid);
		townName = TownyUniverse.getTownName(pos);
		if (townName == null)
			townName = "Wilderness";
	}
	
	private String item;
	private double prices[] = new double[2];
	private Location pos;
	private String playerName;
	private String townName;
	private OfflinePlayer player;
	private UUID uuid;
	private int amount;
	
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
	public double getPerItemBuyPrice() {
		double value = prices[0] / amount;
		return value;
	}
	public double getPerItemSellPrice() {
		double value = prices[1] / amount;
		return value;
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
	public String getPlayerUUID() {
		return uuid.toString();
	}
	public String getTownName() {
		return townName;
	}
	
	public boolean register() {
		String[] args = {
					String.valueOf(pos.getBlockX()),
					String.valueOf(pos.getBlockY()),
					String.valueOf(pos.getBlockZ()),
					String.valueOf(prices[0]),
					String.valueOf(prices[1]),
					item,
					String.valueOf(amount),
					String.valueOf(uuid),
					townName
				};
		String query = "INSERT INTO shops (PosX, PosY, PosZ, BuyPrice, SellPrice, Itemtype, Amount, PlayerName, TownName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
        	Catalogue.connect();
            PreparedStatement pstmt = Catalogue.connection.prepareStatement(query);
            for(int argument = 1; argument <= args.length; argument++) {
            	pstmt.setString(argument, args[argument - 1]);
            }
            pstmt.executeUpdate();
            } catch (SQLException e) {
            	e.printStackTrace();
            	return false;
            }
		return false;
	}
	public void unregister() {
		String[] args = {
					String.valueOf(pos.getBlockX()),
					String.valueOf(pos.getBlockY()),
					String.valueOf(pos.getBlockZ())
				};
		String query = "DELETE FROM shops WHERE PosX=? AND (PosY=? AND PosZ=?)";
        try {
        	Catalogue.connect();
            PreparedStatement pstmt = Catalogue.connection.prepareStatement(query);
            for(int argument = 1; argument <= args.length; argument++) {
            	pstmt.setString(argument, args[argument - 1]);
            }
            pstmt.executeUpdate();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
	}
	public boolean isRegistered() {
		String[] args = {
				String.valueOf(pos.getBlockX()),
				String.valueOf(pos.getBlockY()),
				String.valueOf(pos.getBlockZ())
			};
	String query = "SELECT * FROM shops WHERE PosX=? AND (PosY=? AND PosZ=?)";
    try {
        PreparedStatement pstmt = Catalogue.connection.prepareStatement(query);
        for(int argument = 1; argument <= args.length; argument++) {
        	pstmt.setString(argument, args[argument - 1]);
        }
        ResultSet results = pstmt.executeQuery();
        if (results.next()) {
        	return true;
        }
    } catch (SQLException e) {
        	e.printStackTrace();
    }
    return false;
	}
}
