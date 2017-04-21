package com.commandblockguy.catalogue.gui;

import java.util.ArrayList;

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
	private ArrayList<Icon> sortedIcons = new ArrayList<Icon>();
	
	public SortedDisplay(int scroll) {
		super(6, null, "Sorted Display");
		listener = this;
		this.scroll = scroll;
		
		upIcon = new UpIcon(8, this);
		sortIcon = new SortIcon(17);
		filterIcon = new FilterIcon(26);
		backIcon = new BackIcon(44, this);
		downIcon = new DownIcon(53, this);
		
		icons = new Icon[5];
		icons[0] = upIcon;
		icons[1] = sortIcon;
		icons[2] = filterIcon;
		icons[3] = backIcon;
		icons[4] = downIcon;
	}
	public int getScroll() {
		return scroll;
	}
	public void setScroll(int scroll) {
		this.scroll = scroll;
	}
	public ArrayList<Icon> getIcons() {
		return sortedIcons;
	}
	public void addIcon(Icon icon) {
		int slot = 0;
		if (!sortedIcons.isEmpty()) {
			slot = sortedIcons.get(sortedIcons.size() - 1).itemSlot() + 1;
			if (slot % 9 == 8)
				slot++;
		}
		icon.setSlot(slot);
		sortedIcons.add(icon);
	}
	public void addIcons(ArrayList<Icon> icons) {
		if (icons != null) {
			for(Icon i : icons) {
				this.addIcon(i);
			}
		}
	}
	@Override
	public void create() {
		inv = Bukkit.createInventory(null, 9 * ySize, title);
		for (Icon i : icons) {
			inv.setItem(i.itemSlot(), i.getItem());
		}
		for (Icon i : sortedIcons) {
			if (9 * scroll <= i.itemSlot()) {
				inv.setItem(i.itemSlot() - (9 * scroll), i.getItem());
			}
		}

		Catalogue.getPlugin().getServer().getPluginManager().registerEvents(listener, Catalogue.getPlugin());
	}
}
