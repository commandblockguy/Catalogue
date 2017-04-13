package com.commandblockguy.catalogue.gui.icons;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import com.commandblockguy.catalogue.gui.Icon;

public class ShopIcon extends Icon {

	@Override
	public void clickAction() {
		Bukkit.broadcastMessage("Shop Icon Clicked");
	}

	public ShopIcon(ItemStack item, int slot) {
		super(item, Icon.slotX(slot), Icon.slotY(slot));
	}
}
