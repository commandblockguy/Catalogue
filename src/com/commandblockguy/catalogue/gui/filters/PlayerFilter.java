package com.commandblockguy.catalogue.gui.filters;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class PlayerFilter extends Filter{

	public PlayerFilter(String playerName, FilterOperator operator) {
		super(null, operator);
		this.column = "PlayerName";
		@SuppressWarnings("deprecation")
		OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);
		this.filterValue = player.getUniqueId().toString();
	}

}
