package com.commandblockguy.catalogue.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import com.commandblockguy.catalogue.Catalogue;

public class Icon{
	public Icon() {
		
	}
	public Icon(ItemStack item, ItemMeta meta, int xPos, int yPos) {
		this(item, xPos, yPos);
		this.meta = meta;
		item.setItemMeta(meta);
	}
	public Icon(ItemStack item, int xPos, int yPos) {
		this.item = item;
		this.xPos = xPos;
		this.yPos = yPos;
		meta = item.getItemMeta();
	}
	public Icon(ItemStack item, int slot) {
		this(item, Icon.slotX(slot), Icon.slotY(slot));
	}
	
	protected ItemStack item;
	protected ItemMeta meta;
	protected MaterialData data;
	public int xPos;
	public int yPos;
	
	public ItemStack getItem() {
		return item;
	}
	public ItemMeta getMeta() {
		return meta;
	}
	public void addLore(String string) {
		List<String> lore = new ArrayList<String>();
		if (meta.hasLore())
			 lore = meta.getLore();
		lore.add(string);
		meta.setLore(lore);
		item.setItemMeta(meta);
	}
	public void setItem(ItemStack itemStack) {
		item = itemStack;
	}
	public void setMeta(ItemMeta itemMeta) {
		meta = itemMeta;
		item.setItemMeta(meta);
	}
	public void setAmount(int amount) {
		if(amount != item.getAmount() && amount > 0)
			item.setAmount(amount);
	}
	public int itemSlot() {
		return Icon.itemSlot(xPos, yPos);
	}
	public void setSlot(int slot) {
		xPos = slotX(slot);
		yPos = slotY(slot);
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
	public void clickAction() {
		System.out.println(Catalogue.getPlugin().localization.getString("icon.error.invalidclickaction"));
	}
}
