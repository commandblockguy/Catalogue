package com.commandblockguy.catalogue.gui.icons;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.commandblockguy.catalogue.Catalogue;
import com.commandblockguy.catalogue.gui.ChestDisplay;
import com.commandblockguy.catalogue.gui.Icon;
import com.commandblockguy.catalogue.tasks.CloseInventory;

public class BackIcon extends Icon{
	public BackIcon(int slot, ChestDisplay display) {
		item = new ItemStack(Material.BARRIER);
		meta = item.getItemMeta();
		meta.setDisplayName("Back");
		this.xPos = slotX(slot);
		this.yPos = slotY(slot);
		item.setItemMeta(meta);
		this.display = display;
	}
	
	ChestDisplay display;

	@Override
	public void clickAction() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Catalogue.getPlugin(), new CloseInventory(display));
	}
}
