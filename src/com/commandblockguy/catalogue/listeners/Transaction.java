package com.commandblockguy.catalogue.listeners;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.Acrobot.ChestShop.Events.TransactionEvent;
import com.commandblockguy.catalogue.Catalogue;
import com.commandblockguy.catalogue.Shop;

public class Transaction implements Listener {
	@EventHandler
	public void onTransaction(TransactionEvent event) {
		Sign sign = event.getSign();
		Location pos = sign.getLocation();
		if (!isShopRegistered(Catalogue.getPlugin(), pos)) {
			Shop shop = new Shop(sign);
			shop.register();
		}
	}
	public boolean isShopRegistered(Catalogue plugin, Location pos) {
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
