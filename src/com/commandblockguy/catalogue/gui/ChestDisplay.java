package com.commandblockguy.catalogue.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import com.commandblockguy.catalogue.Catalogue;
import com.commandblockguy.catalogue.tasks.CloseInventory;
import com.commandblockguy.catalogue.tasks.OpenInventory;

public class ChestDisplay implements Listener {
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!event.getInventory().equals(inv)) return;
		
		event.setCancelled(true);
		HandlerList.unregisterAll(this);
		
		int slot = event.getRawSlot();
		
		if (icons != null)
			for (Icon i : icons) {
				if (i != null)
					if (i.itemSlot() == slot) {
						Bukkit.getScheduler().scheduleSyncDelayedTask(Catalogue.getPlugin(), new CloseInventory(this));
						i.clickAction();
					}
			}
	}
	public void onInventoryClose(InventoryCloseEvent event) {
		HandlerList.unregisterAll(this);
	}
	
	public Icon[] icons;
	public int ySize = 3;
	public Inventory inv;
	public String title;
	public Listener listener = this;
	protected Player player;
	
	public ChestDisplay(int ySize, Icon[] icons, String title) {
		this.ySize = ySize;
		this.icons = icons;
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
	public Player getPlayer() {
		return player;
	}
	public void create() {
		inv = Bukkit.createInventory(null, 9 * ySize, title);
		for (Icon i : icons) {
			inv.setItem(i.itemSlot(), i.getItem());
		}
		Catalogue.getPlugin().getServer().getPluginManager().registerEvents(listener, Catalogue.getPlugin());
	}
	public void display(Player player) {
		this.player = player;
		create();
		Bukkit.getScheduler().scheduleSyncDelayedTask(Catalogue.getPlugin(), new OpenInventory(inv, player));
		
	}
}