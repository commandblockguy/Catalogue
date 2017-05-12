package com.commandblockguy.catalogue.gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;

import com.commandblockguy.catalogue.Catalogue;
import com.commandblockguy.catalogue.gui.icons.BackIcon;
import com.commandblockguy.catalogue.gui.icons.DownIcon;
import com.commandblockguy.catalogue.gui.icons.HelpIcon;
import com.commandblockguy.catalogue.gui.icons.ShopIcon;
import com.commandblockguy.catalogue.gui.icons.UpIcon;


public class SortedDisplay extends ChestDisplay {
	
	private BackIcon backIcon;
	private HelpIcon helpIcon;
	private UpIcon upIcon;
	private DownIcon downIcon;
	private int scroll = 0;
	private ArrayList<ShopIcon> sortedIcons = new ArrayList<ShopIcon>();
	
	public SortedDisplay(int scroll, String windowTitle) {
		super(6, null, Catalogue.getPlugin().localization.getString("commands.catalogue.windowtitle"));
		listener = this;
		this.scroll = scroll;
		this.title = windowTitle;
		
		upIcon = new UpIcon(17, this);
		//sortIcon = new SortIcon(17);
		//filterIcon = new FilterIcon(26);
		backIcon = new BackIcon(8, this);
		helpIcon = new HelpIcon(26, this);
		downIcon = new DownIcon(53, this);
		
		icons = new ArrayList<Icon>();
		icons.add(upIcon);
		//icons[1] = sortIcon;
		//icons[2] = filterIcon;
		icons.add(helpIcon);
		icons.add(backIcon);
		icons.add(downIcon);
	}
	public int getScroll() {
		return scroll;
	}
	public String getTitle() {
		return title;
	}
	public ArrayList<ShopIcon> getIcons() {
		return sortedIcons;
	}
	public void addIcon(ShopIcon icon) {
		int slot = 0;
		if (!sortedIcons.isEmpty()) {
			slot = sortedIcons.get(sortedIcons.size() - 1).itemSlot() + 1;
			if (slot % 9 == 8)
				slot++;
		}
		icon.setSlot(slot);
		if(!sortedIcons.contains(icon))
			sortedIcons.add(icon);
	}
	public void addIcons(ArrayList<ShopIcon> icons) {
		if (icons != null) {
			for(ShopIcon i : icons) {
				this.addIcon(i);
			}
		}
	}
	@Override
	public void create() {
		inv = Bukkit.createInventory(null, 9 * ySize, title);
		for (ShopIcon i : sortedIcons) {
			if (9 * scroll <= i.itemSlot()) {
				i.setSlot(i.itemSlot() - (9 * scroll));
				i.setPlayer(player);
				if(i.itemSlot() < 54)
					icons.add(i);
			}
		}
		for (Icon i : icons) {
			inv.setItem(i.itemSlot(), i.getItem());
		}

		Catalogue.getPlugin().getServer().getPluginManager().registerEvents(listener, Catalogue.getPlugin());
	}
}
