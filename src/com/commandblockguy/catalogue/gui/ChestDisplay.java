package com.commandblockguy.catalogue.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ChestDisplay implements Listener{
	public Icon[][] icons;
	int ySize = 3;
	Inventory inv;
	String title;
	public ChestDisplay(int ySize) {
		icons = new Icon[ySize][9];
	}
	public ChestDisplay() {
		this(6);
	}
	public void display(Player player) {
		inv = Bukkit.createInventory(player, ySize, title);
	}
	public static int itemSlot(int x, int y) {
		//Where 0, 0 is the item in the top left (slot 0) 
		return x + (9 * y);
	}
	public static int slotX(int slotNumber) {
		return slotNumber % 9;
	}
	public static int slotY(int slotNumber) {
		return (slotNumber - slotX(slotNumber)) / 9;
	}
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getInventory() != inv) return;
		
		Player player = (Player) event.getWhoClicked();
		
		Bukkit.broadcastMessage(player.getName() + "clicked something!");
	}
}