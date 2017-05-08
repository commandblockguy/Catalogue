package com.commandblockguy.catalogue.gui.filters;

import com.commandblockguy.catalogue.gui.icons.ShopIcon;

public class TownFilter extends Filter {

	public TownFilter(String value, FilterOperator operator) {
		super(value, operator);
		this.column = "TownName";
	}

	@Override
	public String value(ShopIcon icon) {
		return icon.getShop().getTownName();
	}
}
