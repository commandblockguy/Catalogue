package com.commandblockguy.catalogue.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.commandblockguy.catalogue.Catalogue;

public class ChestDisplay implements Listener {
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!event.getInventory().equals(inv)) return;
		
		ItemStack item = event.getCurrentItem();
		
		if (icons != null)
			for (Icon i : icons) {
				if (i != null)
					if (i.getItem().equals(item))
						i.clickAction();
			}
		
		event.setCancelled(true);
		HandlerList.unregisterAll(this);
	}
	
	public Icon[] icons;
	public int ySize = 3;
	public Inventory inv;
	public String title;
	public Listener listener = this;
	
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
	public void create() {
		inv = Bukkit.createInventory(null, 9 * ySize, title);
		for (Icon i : icons) {
			inv.setItem(i.itemSlot(), i.getItem());
		}
		Catalogue.getPlugin().getServer().getPluginManager().registerEvents(listener, Catalogue.getPlugin());
	}
	public void display(Player player) {
		create();
		player.openInventory(inv);
	}
}