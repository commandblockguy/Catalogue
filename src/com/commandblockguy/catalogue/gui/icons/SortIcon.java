package com.commandblockguy.catalogue.gui.icons;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.commandblockguy.catalogue.gui.Icon;

public class SortIcon extends Icon{
	public SortIcon(int slot) {
		ItemStack item = new ItemStack(Material.BOOK_AND_QUILL);
		item.getItemMeta().setDisplayName("Sort");
		this.xPos = slotX(slot);
		this.yPos = slotY(slot);
		this.item = item;
	}

	@Override
	public void clickAction() {
		Bukkit.broadcastMessage("Sort icon was clicked!");
	}
}
