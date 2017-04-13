package com.commandblockguy.catalogue.gui.icons;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.commandblockguy.catalogue.gui.Icon;

public class BackIcon extends Icon{
	public BackIcon(int slot) {
		ItemStack item = new ItemStack(Material.BARRIER);
		item.getItemMeta().setDisplayName("Back");
		this.xPos = slotX(slot);
		this.yPos = slotY(slot);
		this.item = item;
	}

	@Override
	public void clickAction() {
		Bukkit.broadcastMessage("Back icon was clicked!");
	}
}
