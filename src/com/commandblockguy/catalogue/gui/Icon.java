package com.commandblockguy.catalogue.gui;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Icon {
	public Icon() {
		
	}
	public Icon(ItemStack item, ItemMeta meta, int xPos, int yPos) {
		this(item, xPos, yPos);
		this.meta = meta;
	}
	public Icon(ItemStack item, int xPos, int yPos) {
		this.item = item;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	public Icon(ItemStack item, int slot) {
		this(item, Icon.slotX(slot), Icon.slotY(slot));
	}
	
	protected ItemStack item;
	protected ItemMeta meta;
	public int xPos;
	public int yPos;
	
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
	public int itemSlot() {
		return Icon.itemSlot(xPos, yPos);
	}
	public static int itemSlot(int x, int y) {
		//Where 0, 0 is the item in the top left (slot 0) 
		return x + (9 * y);
	}
	public static int slotX(int slotNumber) {
		return slotNumber % 9;
	}
	public static int slotY(int slotNumber) {
		return (slotNumber - slotX(slotNumber)) / 9;
	}
	public void clickAction()
	{
		//never interact with the inventory here without scheduling for the next tick
		
	}
}