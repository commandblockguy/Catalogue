package com.commandblockguy.catalogue.gui.filters;

import com.commandblockguy.catalogue.gui.icons.ShopIcon;

public class NoFilter extends Filter {

	public NoFilter() {
		super("1", FilterOperator.EQUALS);
		this.column = "1";
	}

	@Override
	public String value(ShopIcon icon) {
		return "none";
	}
}
