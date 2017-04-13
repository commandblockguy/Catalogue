package com.commandblockguy.catalogue.gui.icons;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.commandblockguy.catalogue.gui.Icon;

public class FilterIcon extends Icon {
	public FilterIcon(int slot) {
		ItemStack item = new ItemStack(Material.IRON_FENCE);
		item.getItemMeta().setDisplayName("Filter");
		this.xPos = slotX(slot);
		this.yPos = slotY(slot);
		this.item = item;
	}

	@Override
	public void clickAction() {
		Bukkit.broadcastMessage("Filter icon was clicked!");
	}
}
