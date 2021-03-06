package com.commandblockguy.catalogue.gui.filters;

import com.commandblockguy.catalogue.gui.icons.ShopIcon;

public class BuyPriceFilter extends Filter {

	public BuyPriceFilter(double value, FilterOperator operator) {
		super(String.valueOf(value), operator);
		this.column = "BuyPrice";
		
	}
	@Override
	public String value(ShopIcon icon) {
		return String.valueOf(icon.getShop().getBuyPrice());
	}

}
