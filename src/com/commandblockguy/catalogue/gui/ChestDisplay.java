package com.commandblockguy.catalogue.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.commandblockguy.catalogue.Catalogue;

public class ChestDisplay implements Listener {
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!event.getInventory().equals(inv)) return;
		
		Player player = (Player) event.getWhoClicked();
		
		Bukkit.broadcastMessage(player.getName() + " clicked something in /catalogue!");
		HandlerList.unregisterAll(this);
	}
	
	public Icon[] icons;
	public int ySize = 3;
	public Inventory inv;
	public String title;
	
	public ChestDisplay(int ySize, Icon[] icons, String title) {
		this.ySize = ySize;
		for (Icon i : icons) {
			inv = Bukkit.createInventory(null, 9 * ySize, title);
			inv.setItem(i.itemSlot(), i.getItem());
		}
		Catalogue.getPlugin().getServer().getPluginManager().registerEvents(this, Catalogue.getPlugin());
		this.title = title;
	}
	public ChestDisplay(int ySize, Icon[] icons) {
		this(ySize, icons, "Display");
	}
	public ChestDisplay(int ySize) {
		this(ySize, null);
	}
	public ChestDisplay() {
		this(6);
	}
	public void display(Player player) {
		player.openInventory(inv);
	}
}