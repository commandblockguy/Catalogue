package com.commandblockguy.catalogue.gui.comparators;

import java.util.Comparator;

import com.commandblockguy.catalogue.gui.icons.ShopIcon;

public class TownNameComparator implements Comparator<ShopIcon>{

	@Override
	public int compare(ShopIcon shop1, ShopIcon shop2) {
		return shop1.getShop().getTownName().compareTo(shop2.getShop().getTownName());
	}

}
