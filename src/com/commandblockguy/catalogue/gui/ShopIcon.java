package com.commandblockguy.catalogue.gui;

import org.bukkit.Bukkit;

public class ShopIcon extends Icon {

	@Override
	public void clickAction() {
		Bukkit.broadcastMessage("Shop Icon Clicked");
	}

}
