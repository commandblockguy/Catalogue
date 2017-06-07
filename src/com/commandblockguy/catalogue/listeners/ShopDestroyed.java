package com.commandblockguy.catalogue.listeners;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.Acrobot.ChestShop.Events.ShopDestroyedEvent;
import com.commandblockguy.catalogue.Catalogue;

public class ShopDestroyed  implements Listener {
	@EventHandler
	public void onShopDestroyed(ShopDestroyedEvent event){
		unregisterShop(Catalogue.getPlugin(), event.getSign().getLocation());
	}
	private void unregisterShop(Catalogue plugin, Location pos) {
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
}
