package com.commandblockguy.catalogue.gui.filters;

public class NoFilter extends Filter {

	public NoFilter() {
		super("1", FilterOperator.EQUALS);
		this.column = "1";
	}

}
