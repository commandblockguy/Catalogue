package com.commandblockguy.catalogue.gui.icons;

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
		this.shop = shop;
	}
	public ShopIcon(Shop shop, int slot) {
		super(MaterialUtil.getItem(shop.getItemName()), Icon.slotX(slot), Icon.slotY(slot));
		this.shop = shop;
	}
	
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
