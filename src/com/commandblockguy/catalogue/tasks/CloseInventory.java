package com.commandblockguy.catalogue.tasks;

import com.commandblockguy.catalogue.gui.ChestDisplay;

public class CloseInventory implements Runnable {

	private ChestDisplay display;
	
	public CloseInventory(ChestDisplay display) {
		this.display = display;
	}
	
	@Override
	public void run() {
		display.getPlayer().closeInventory();

	}

}
