package com.commandblockguy.catalogue.gui.icons;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.commandblockguy.catalogue.gui.Icon;

public class SortIcon extends Icon{
	public SortIcon(int slot) {
		item = new ItemStack(Material.BOOK_AND_QUILL);
		meta = item.getItemMeta();
		meta.setDisplayName("Sort");
		this.xPos = slotX(slot);
		this.yPos = slotY(slot);
		item.setItemMeta(meta);
	}

	@Override
	public void clickAction() {
		System.out.println("Sort icon was clicked!");
	}
}
