package com.commandblockguy.catalogue.gui;

import com.commandblockguy.catalogue.gui.icons.BackIcon;
import com.commandblockguy.catalogue.gui.icons.FilterIcon;
import com.commandblockguy.catalogue.gui.icons.SortIcon;


public class SortedDisplay extends ChestDisplay {
	
	public SortIcon sortIcon;
	public FilterIcon filterIcon;
	public BackIcon backIcon;
	
	public SortedDisplay() {
		super(6, null, "Sorted Display");
		listener = this;
		
		sortIcon = new SortIcon(3);
		filterIcon = new FilterIcon(4);
		backIcon = new BackIcon(8);
		
		icons = new Icon[3];
		icons[0] = sortIcon;
		icons[1] = filterIcon;
		icons[2] = backIcon;
	}
}
