package com.commandblockguy.catalogue.gui.icons;

import org.bukkit.inventory.ItemStack;

import com.Acrobot.Breeze.Utils.MaterialUtil;
import com.commandblockguy.catalogue.Shop;
import com.commandblockguy.catalogue.gui.Icon;

public class ShopIcon extends Icon {

	private Shop shop;
	
	@Override
	public void clickAction() {
		if(shop != null) {
			
		}
	}

	public ShopIcon(Shop shop) {
		super(MaterialUtil.getItem(shop.getItemName()), -1);
	}
	public ShopIcon(Shop shop, int slot) {
		this(MaterialUtil.getItem(shop.getItemName()), slot);
		this.shop = shop;
	}
	public ShopIcon(ItemStack item, int slot) {
		super(item, Icon.slotX(slot), Icon.slotY(slot));
	}
	
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
