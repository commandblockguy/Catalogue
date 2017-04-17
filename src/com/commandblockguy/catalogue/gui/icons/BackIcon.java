package com.commandblockguy.catalogue.gui.icons;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.commandblockguy.catalogue.gui.Icon;

public class BackIcon extends Icon{
	public BackIcon(int slot) {
		item = new ItemStack(Material.BARRIER);
		meta = item.getItemMeta();
		meta.setDisplayName("Back");
		this.xPos = slotX(slot);
		this.yPos = slotY(slot);
		item.setItemMeta(meta);
	}

	@Override
	public void clickAction() {
		Bukkit.broadcastMessage("Back icon was clicked!");
	}
}
