package com.commandblockguy.catalogue.gui.filters;

import com.commandblockguy.catalogue.gui.icons.ShopIcon;

public class AmountFilter extends Filter {
	public AmountFilter(double value, FilterOperator operator) {
		super(String.valueOf(value), operator);
		this.column = "Amount";
	}

	@Override
	public String value(ShopIcon icon) {
		return String.valueOf(icon.getItem().getAmount());
	}
}
