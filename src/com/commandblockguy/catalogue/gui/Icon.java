package com.commandblockguy.catalogue.gui;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Icon {
	public Icon() {
		
	}
	
	private ItemStack item;
	private ItemMeta meta;
	public String words = "IT WORKED";
	
	public ItemStack getItem() {
		return item;
	}
	public ItemMeta getMeta() {
		return meta;
	}
	public void setItem(ItemStack itemStack) {
		item = itemStack;
	}
	public void setMeta(ItemMeta itemMeta) {
		meta = itemMeta;
	}
	public void clickAction()
	{
		
	}
}
