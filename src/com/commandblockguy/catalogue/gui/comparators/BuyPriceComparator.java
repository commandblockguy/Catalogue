package com.commandblockguy.catalogue.gui.comparators;

import java.util.Comparator;

import com.commandblockguy.catalogue.gui.icons.ShopIcon;

public class BuyPriceComparator implements Comparator<ShopIcon>{

	@Override
	public int compare(ShopIcon shop1, ShopIcon shop2) {
		return (int) (shop1.getShop().getBuyPrice() - shop2.getShop().getBuyPrice());
	}

}
