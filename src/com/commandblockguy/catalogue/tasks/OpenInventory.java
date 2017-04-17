package com.commandblockguy.catalogue.tasks;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class OpenInventory implements Runnable {

	private Inventory inventory;
	private Player player;
	
	public OpenInventory(Inventory inventory, Player player) {
		this.inventory = inventory;
		this.player = player;
	}
	
	@Override
	public void run() {
		player.openInventory(inventory);
	}

}
