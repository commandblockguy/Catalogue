package com.commandblockguy.catalogue.gui.icons;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.Acrobot.Breeze.Utils.MaterialUtil;
import com.commandblockguy.catalogue.Shop;
import com.commandblockguy.catalogue.gui.Icon;

public class ShopIcon extends Icon {

	private Shop shop;
	private Player player;
	
	@Override
	public void clickAction() {
		if(shop != null) {
			changeCompass();
			return;
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
	public ShopIcon(Shop shop, int slot, Player player) {
		this(shop, slot);
		this.player = player;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public void changeCompass() {
		Location pos = shop.getLocation();
		if(player.getCompassTarget().equals(pos)) {
			player.sendMessage("Resetting compass...");
			player.setCompassTarget(player.getWorld().getSpawnLocation());
		} else {
			player.sendMessage("Showing location on compass...");
			player.setCompassTarget(pos);
		}
	}
}
