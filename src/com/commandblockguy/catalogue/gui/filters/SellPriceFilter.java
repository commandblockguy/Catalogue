package com.commandblockguy.catalogue.gui.filters;

import com.commandblockguy.catalogue.gui.icons.ShopIcon;

public class SellPriceFilter extends Filter {

	public SellPriceFilter(double value, FilterOperator operator) {
		super(String.valueOf(value), operator);
		this.column = "SellPrice";
	}

	@Override
	public String value(ShopIcon icon) {
		return String.valueOf(icon.getShop().getSellPrice());
	}
}
