package com.commandblockguy.catalogue.gui.filters;

public class ItemFilter extends Filter {

	public ItemFilter(String value, FilterOperator operator) {
		super(value, operator);
		this.column = "ItemType";
	}

}
