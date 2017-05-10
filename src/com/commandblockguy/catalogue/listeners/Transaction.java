package com.commandblockguy.catalogue.listeners;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.Acrobot.ChestShop.Events.TransactionEvent;
import com.commandblockguy.catalogue.Shop;

public class Transaction implements Listener {
	@EventHandler
	public void onTransaction(TransactionEvent event) {
		Sign sign = event.getSign();
		Shop shop = new Shop(sign);
		if (!shop.isRegistered()) {
			shop.register();
		}
	}
}
