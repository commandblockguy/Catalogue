package com.commandblockguy.catalogue.gui.comparators;

import java.util.Comparator;

import com.commandblockguy.catalogue.gui.icons.ShopIcon;

public class AmountComparator implements Comparator<ShopIcon> {

	@Override
	public int compare(ShopIcon shop1, ShopIcon shop2) {
		return shop1.getItem().getAmount() - shop2.getItem().getAmount();
	}

}
