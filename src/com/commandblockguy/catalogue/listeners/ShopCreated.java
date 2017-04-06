package com.commandblockguy.catalogue.listeners;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.Acrobot.ChestShop.Events.ShopCreatedEvent;
import com.commandblockguy.catalogue.Shop;

public class ShopCreated implements Listener {
	@EventHandler
	public void onShopCreated(ShopCreatedEvent event) {
		String playerName = event.getPlayer().getName();
		Sign sign = event.getSign();
		String lines[] = event.getSignLines();
		for(int a = 0; a <= 3; a++)
			sign.setLine(a, lines[a]);
		Shop shop = new Shop(sign, playerName);
		shop.register();
	}
}