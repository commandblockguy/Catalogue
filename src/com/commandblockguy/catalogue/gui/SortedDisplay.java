package com.commandblockguy.catalogue.gui;

import org.bukkit.Bukkit;

import com.commandblockguy.catalogue.Catalogue;
import com.commandblockguy.catalogue.gui.icons.BackIcon;
import com.commandblockguy.catalogue.gui.icons.DownIcon;
import com.commandblockguy.catalogue.gui.icons.FilterIcon;
import com.commandblockguy.catalogue.gui.icons.SortIcon;
import com.commandblockguy.catalogue.gui.icons.UpIcon;


public class SortedDisplay extends ChestDisplay {
	
	private SortIcon sortIcon;
	private FilterIcon filterIcon;
	private BackIcon backIcon;
	private UpIcon upIcon;
	private DownIcon downIcon;
	private int scroll = 0;
	
	public SortedDisplay(int scroll) {
		super(6, null, "Sorted Display");
		listener = this;
		
		upIcon = new UpIcon(8, this);
		sortIcon = new SortIcon(17);
		filterIcon = new FilterIcon(26);
		backIcon = new BackIcon(44);
		downIcon = new DownIcon(53, this);
		
		icons = new Icon[5];
		icons[0] = upIcon;
		icons[1] = sortIcon;
		icons[2] = filterIcon;
		icons[3] = backIcon;
		icons[4] = downIcon;
		this.scroll = scroll;
		Bukkit.broadcastMessage(String.valueOf(scroll));
	}
	public int getScroll() {
		return scroll;
	}
	public void setScroll(int scroll) {
		this.scroll = scroll;
	}
	public Icon[] getIcons() {
		return icons;
	}
	public void setIcons(Icon[] icons) {
		this.icons = icons;
	}
	@Override
	public void create() {
		inv = Bukkit.createInventory(null, 9 * ySize, title);
		for (Icon i : icons) {
			if (i.xPos == 8) {
				inv.setItem(i.itemSlot(), i.getItem());
			}
			else if (9 * scroll < i.itemSlot()) {
				inv.setItem(i.itemSlot() - (9 * scroll), i.getItem());
			}

		}
		Catalogue.getPlugin().getServer().getPluginManager().registerEvents(listener, Catalogue.getPlugin());
	}
}
