package com.commandblockguy.catalogue.gui.filters;

import com.commandblockguy.catalogue.gui.icons.ShopIcon;

public class ItemFilter extends Filter {

	public ItemFilter(String value, FilterOperator operator) {
		super(value, operator);
		this.column = "ItemType";
	}

	@Override
	public String value(ShopIcon icon) {
		return icon.getShop().getItemName();
	}
}
