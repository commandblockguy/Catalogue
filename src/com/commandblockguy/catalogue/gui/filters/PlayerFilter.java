package com.commandblockguy.catalogue.gui.filters;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import com.commandblockguy.catalogue.gui.icons.ShopIcon;

public class PlayerFilter extends Filter{

	public PlayerFilter(String playerName, FilterOperator operator) {
		super(null, operator);
		this.column = "PlayerName";
		@SuppressWarnings("deprecation")
		OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);
		this.filterValue = player.getUniqueId().toString();
	}
	
	@Override
	public String value(ShopIcon icon) {
		return icon.getShop().getPlayerUUID();
	}
}
